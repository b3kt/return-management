package com.example.returnkey.returnmanagement.service.impl;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.example.returnkey.returnmanagement.constant.Constant;
import com.example.returnkey.returnmanagement.entity.PendingReturn;
import com.example.returnkey.returnmanagement.entity.Return;
import com.example.returnkey.returnmanagement.entity.ReturnItem;
import com.example.returnkey.returnmanagement.service.BaseService;
import com.example.returnkey.returnmanagement.service.IReturnService;
import com.google.common.hash.Hashing;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ReturnService extends BaseService implements IReturnService{

    public String generateToken(String string) {
        // String random = RandomStringUtils.random(8);
        return Hashing.sha256()
            .hashString(string, StandardCharsets.UTF_8)
            .toString();
    }

    @Override
    public String findPendingReturn(String email, String orderId){
        String token = null;
        List<PendingReturn> pendingReturnList = pendingReturnRepository.findByEmailAndOrderId(email, orderId);
        if(CollectionUtils.isNotEmpty(pendingReturnList)){
            token = generateToken(orderId.concat(email));
            final String tmpToken = token;
            pendingReturnList.stream().forEach(x -> x.setToken(tmpToken));
            pendingReturnRepository.saveAll(pendingReturnList);
        }

        return token;
    }

    @Transactional
    @Override
    public Return createReturn(String token){
        Return result = new Return();
        List<PendingReturn> pendingReturnList = pendingReturnRepository.findByToken(token);
        if(CollectionUtils.isNotEmpty(pendingReturnList)){
            BigDecimal totalAmount = BigDecimal.ZERO;

            for(PendingReturn pr: pendingReturnList){
                
                totalAmount = totalAmount.add(pr.getPrice().multiply(new BigDecimal(pr.getQuantity())));

                if(result.getOrderId() == null){
                    BeanUtils.copyProperties(pr, result);
                    result = returnRepository.save(result);
                }

                ReturnItem ri = new ReturnItem();
                BeanUtils.copyProperties(pr, ri); 
                ri.setReturns(result);
                ri.setStatus(Constant.AWAITING_APPROVAL);
                returnItemRepository.save(ri);
            }


            if(result.getId() != null){
                result.setRefundAmount(totalAmount);
                result.setStatus(Constant.AWAITING_APPROVAL);
                result = returnRepository.save(result);
                logger.info("return created. deleting from pendingReturn..");
                pendingReturnRepository.deleteAll(pendingReturnList);
            }
        }

        return result;
    }

    @Override
    public Return findReturn(Long id){
        Return result = returnRepository.findById(id).orElse(null);
        return result;
    }

    @Override
    public List<ReturnItem> findReturnItemsByReturnId(Long id){
        return returnItemRepository.findByReturnId(id);
    }

    @Override
    public void setQCStatus(Long id, Long itemId, final String status){

        ReturnItem returnItem = returnItemRepository.findById(itemId).orElse(null);
        if(returnItem != null){
            returnItem.setStatus(status);
            returnItemRepository.save(returnItem);

            logger.info("Item {} : {}", itemId, status);
        }

        List<ReturnItem> returnItems = returnItemRepository.findByReturnId(id);
        if(CollectionUtils.isNotEmpty(returnItems)){
            // checking pending approval
            Return returns = returnRepository.findById(id).orElse(null);
            if(returns != null){

                // recalculate total amount;
                BigDecimal totalAmount = recalculateTotalAmount(returns.getReturnItemList());
                logger.info("totalAmount : {} ", totalAmount);
                returns.setRefundAmount(totalAmount);

                if(!returnItems.stream().anyMatch(x -> Constant.AWAITING_APPROVAL.equals(x.getStatus()))){
                    returns.setStatus(Constant.COMPLETE);
                }
                
                returnRepository.save(returns);
            }
        }
    }

    @Override
    public BigDecimal recalculateTotalAmount(List<ReturnItem> returnItemList) {
        BigDecimal totalAmount = BigDecimal.ZERO;
        if(CollectionUtils.isNotEmpty(returnItemList)){
            totalAmount = returnItemList.stream()
                    .filter(x -> !Constant.REJECTED.equals(x.getStatus()))
                    .map(y -> {
                        return y.getPrice().multiply(new BigDecimal(y.getQuantity()));
                    })
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
        return totalAmount;
    }
}

package com.example.returnkey.returnmanagement.service.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.returnkey.returnmanagement.entity.PendingReturn;
import com.example.returnkey.returnmanagement.service.BaseService;
import com.example.returnkey.returnmanagement.service.IUploadService;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class UploadService extends BaseService implements IUploadService {

    /* the header used to validate the csv file */
    private static final List<String> HEADER_NAMES = Arrays.asList("orderId","emailAddress","sku","quantity","price","itemName");
    private static final String HEADERS = "orderId,emailAddress,sku,quantity,price,itemName";

    private PendingReturn parseOrdersLine(String lineContent) throws Exception{

        PendingReturn result = null;
        if(StringUtils.isNotBlank(lineContent)){
            List<String> contentList = Arrays.asList(lineContent.split(","));

            result = new PendingReturn();
            result.setOrderId(contentList.get(HEADER_NAMES.indexOf("orderId")));
            result.setEmailAddress(contentList.get(HEADER_NAMES.indexOf("emailAddress")));
            result.setSku(contentList.get(HEADER_NAMES.indexOf("sku")));

            final String qtyStr = contentList.get(HEADER_NAMES.indexOf("quantity"));
            final Long qty = Long.parseLong(qtyStr);
            if(qty > 10 || qty < 0){
                throw new Exception("invalid quantity");
            }
            result.setQuantity(qty);

            final String price = contentList.get(HEADER_NAMES.indexOf("price"));
            result.setPrice(new BigDecimal(price));

            result.setItemName(contentList.get(HEADER_NAMES.indexOf("itemName")));
        }

        return result;
    }

    @Override
    public void handleFileUpload(MultipartFile file) throws Exception {
        if(file !=null && !file.isEmpty()){
            String content = new String(file.getBytes());

            Scanner scanner = new Scanner(content);
            int idx=0;
            while (scanner.hasNextLine()) {

                // List<String> lineContent = Arrays.asList(scanner.nextLine().split(","));
                String lineContent = scanner.nextLine();
                
                if(idx == 0){
                    // first index used to validation
                    if(!HEADERS.equals(lineContent)){
                        // throw new Exception("Invalid csv file");
                    }else{
                        logger.info("file valid");
                    }
                }else{
                    PendingReturn obj = parseOrdersLine(lineContent);

                    // skip empty lines
                    if(obj != null){
                        obj = pendingReturnRepository.save(parseOrdersLine(lineContent));
                        logger.info((idx) + "saved: {}", obj);
                    }

                }
                idx++;
            }
            scanner.close();

            
        }
    }

}

package com.example.returnkey.returnmanagement.service;

import java.math.BigDecimal;
import java.util.List;

import com.example.returnkey.returnmanagement.entity.Return;
import com.example.returnkey.returnmanagement.entity.ReturnItem;

public interface IReturnService {
    
    String findPendingReturn(String email, String orderId);

    Return createReturn(String token);

    Return findReturn(Long id);

    List<ReturnItem> findReturnItemsByReturnId(Long id);

    void setQCStatus(Long id, Long itemId, String status);

    BigDecimal recalculateTotalAmount(List<ReturnItem> returnItemList);
}
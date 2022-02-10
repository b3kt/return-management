package com.example.returnkey.returnmanagement.service;

import javax.persistence.MappedSuperclass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.returnkey.returnmanagement.repository.PendingReturnRepository;
import com.example.returnkey.returnmanagement.repository.ReturnItemRepository;
import com.example.returnkey.returnmanagement.repository.ReturnRepository;

@MappedSuperclass
public abstract class BaseService {

    protected Logger logger = LoggerFactory.getLogger(BaseService.class);

    @Autowired
    protected PendingReturnRepository pendingReturnRepository; 
    
    @Autowired
    protected ReturnRepository returnRepository;

    @Autowired
    protected ReturnItemRepository returnItemRepository;
}
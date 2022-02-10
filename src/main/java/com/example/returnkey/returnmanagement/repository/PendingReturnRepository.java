package com.example.returnkey.returnmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.returnkey.returnmanagement.entity.PendingReturn;

@Repository
public interface PendingReturnRepository extends CrudRepository<PendingReturn, Long> {

    @Query("SELECT pr FROM PendingReturn pr WHERE pr.emailAddress = :email AND pr.orderId = :orderId ")
    List<PendingReturn> findByEmailAndOrderId(@Param("email") String email, @Param("orderId") String orderId);
    
    @Query("SELECT pr FROM PendingReturn pr WHERE pr.token IS NOT NULL AND pr.token = :token ")
    List<PendingReturn> findByToken(@Param("token") String token);
    

}
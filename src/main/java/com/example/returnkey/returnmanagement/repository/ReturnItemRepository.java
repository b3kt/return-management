package com.example.returnkey.returnmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.returnkey.returnmanagement.entity.ReturnItem;

@Repository
public interface ReturnItemRepository extends CrudRepository<ReturnItem, Long>{

    @Query("SELECT ri FROM ReturnItem ri JOIN ri.returns r WHERE r.id = :returnId AND ri.status != 'REJECTED' ")
    List<ReturnItem> findByReturnId(@Param("returnId") Long returnId);
}
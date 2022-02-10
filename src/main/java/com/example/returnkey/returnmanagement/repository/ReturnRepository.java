package com.example.returnkey.returnmanagement.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.returnkey.returnmanagement.entity.Return;

@Repository
public interface ReturnRepository extends CrudRepository<Return, Long>{

}
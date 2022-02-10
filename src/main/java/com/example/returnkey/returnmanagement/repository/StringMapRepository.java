package com.example.returnkey.returnmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.returnkey.returnmanagement.entity.StringMap;

@Repository
public interface StringMapRepository extends JpaRepository<StringMap, Long> {
    
}
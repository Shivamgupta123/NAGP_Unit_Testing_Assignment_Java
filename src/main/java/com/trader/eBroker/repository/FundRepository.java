package com.trader.eBroker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trader.eBroker.entity.Fund;



@Repository
public interface FundRepository extends JpaRepository<Fund, Integer>{

}
package com.trader.eBroker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trader.eBroker.entity.Equity;


@Repository
public interface EquityRepository extends JpaRepository<Equity, Integer>{

	public Equity findByStockName(String stockName);

}

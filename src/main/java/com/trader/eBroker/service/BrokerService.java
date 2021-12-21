package com.trader.eBroker.service;

import java.util.List;
import com.trader.eBroker.dao.EquityDTO;
import com.trader.eBroker.entity.Equity;
import com.trader.eBroker.entity.Fund;

public interface BrokerService{

	public Fund addFunds(double fund);
	public String trade(EquityDTO entityDTO);
	public Fund getFunds();
	public List<Equity> getEquity();
}

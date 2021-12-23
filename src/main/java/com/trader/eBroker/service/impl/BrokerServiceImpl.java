package com.trader.eBroker.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trader.eBroker.dao.EquityDTO;
import com.trader.eBroker.entity.Equity;
import com.trader.eBroker.entity.Fund;
import com.trader.eBroker.repository.EquityRepository;
import com.trader.eBroker.repository.FundRepository;
import com.trader.eBroker.service.BrokerService;
import com.trader.eBroker.util.DateUtil;
import com.trader.eBroker.util.OrderType;

@Service
public class BrokerServiceImpl implements BrokerService{
	
	@Autowired
	FundRepository fundsRepo;

	@Autowired
	EquityRepository equityRepo;
	
	@Override
	public Fund getFunds() {
		return fundsRepo.getById(1);	
	}
	
	@Override
	public Fund addFunds(double fundAmount) {
		Fund existing = fundsRepo.getById(1);
		existing.setFund(existing.getFund() + fundAmount);
		return fundsRepo.save(existing);
	}

	@Override
	public String trade(EquityDTO equityDTO) {
		try {
			boolean isWorkingHour = DateUtil.checkForWorkingHours();
			if(!isWorkingHour) {
				return "Trades can be executed from Monday to Friday and between 9AM to 5PM only.";
			}
			OrderType type = OrderType.valueOf(equityDTO.getOrderType().toUpperCase());
			switch(type) {
				case SELL:
					return sellEquity(equityDTO);
				default:
					return buyEquity(equityDTO);
			}
		}catch(Exception e) {
			//TODO
			//e.printStackTrace();
			return "Something went wrong. Please check your request and try again.";
		}
	}

	
	public String buyEquity(EquityDTO equityDTO) {
		Fund availableFund = fundsRepo.getById(1);
		double totalOrderAmount = equityDTO.getQuantity() * equityDTO.getPerStockPrice();
		if(availableFund.getFund()< totalOrderAmount) {
			return "You don't have sufficient funds to buy equity.";
		}
		Equity equity = equityRepo.findByStockName(equityDTO.getName().toLowerCase());
		if(equity != null) {
			equity.setStockQuantity(equity.getStockQuantity() + equityDTO.getQuantity());
		}else {
			equity = new Equity();
			equity.setStockName(equityDTO.getName().toLowerCase());
			equity.setStockQuantity(equityDTO.getQuantity());
		}
		availableFund.setFund(availableFund.getFund() - totalOrderAmount);
		equityRepo.save(equity);
		fundsRepo.save(availableFund);
		return "Equity bought successfully.";	
	}

	public String sellEquity(EquityDTO equityDTO) {
		Equity existing = equityRepo.findByStockName(equityDTO.getName().toLowerCase());
		if(existing == null || equityDTO.getQuantity() > existing.getStockQuantity()) {
			return "You don't have sufficient equity to sell";
		}
		Fund availableFund = fundsRepo.getById(1);
		double totalOrderAmount = equityDTO.getQuantity() * equityDTO.getPerStockPrice();
		existing.setStockQuantity(existing.getStockQuantity() - equityDTO.getQuantity());
		availableFund.setFund(availableFund.getFund() + totalOrderAmount);
		equityRepo.save(existing);
		fundsRepo.save(availableFund);
		return "Equity sold successfully.";
	}

	@Override
	public List<Equity> getEquity() {
		return equityRepo.findAll();
	}
	
}

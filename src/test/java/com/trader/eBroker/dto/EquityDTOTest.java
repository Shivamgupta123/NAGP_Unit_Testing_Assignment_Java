package com.trader.eBroker.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.trader.eBroker.dao.EquityDTO;



public class EquityDTOTest {
	
	@Test
	public void shouldSetName() {
		String actualName = "hdfc";
		EquityDTO equityDTO = new EquityDTO();
		equityDTO.setName(actualName);
		Assertions.assertEquals(equityDTO.getName(), actualName);
	}
	
	@Test
	public void shouldSetPerStockPrice() {
		double actualPrice = 2500;
		EquityDTO equityDTO = new EquityDTO();
		equityDTO.setPerStockPrice(actualPrice);;
		Assertions.assertEquals(equityDTO.getPerStockPrice(), actualPrice);
	}
	
	@Test
	public void shouldSetOrderType() {
		String actualType= "buy";
		EquityDTO equityDTO = new EquityDTO();
		equityDTO.setOrderType(actualType);
		Assertions.assertEquals(equityDTO.getOrderType(), actualType);
	}
	
	@Test
	public void shouldSetQuantity() {
		int actualQuantity = 10;
		EquityDTO equityDTO = new EquityDTO();
		equityDTO.setQuantity(actualQuantity);
		Assertions.assertEquals(equityDTO.getQuantity(), actualQuantity);
	}
	
}

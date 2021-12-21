package com.trader.eBroker.controller;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trader.eBroker.dao.EquityDTO;
import com.trader.eBroker.entity.Equity;
import com.trader.eBroker.entity.Fund;
import com.trader.eBroker.service.BrokerService;



@ExtendWith(SpringExtension.class)
@WebMvcTest(BrokerController.class)
public class BrokerControllerTest {
	
	@MockBean
	private BrokerService tradeService;
	
	@Autowired
	private MockMvc mockMvc;	
	
	@Test
	public void shouldTestHomePage() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.is("Home Page.")));
	}
	
	@Test
	public void shouldTestGetFunds() throws Exception {
		Fund fund = new Fund(1000);
		Mockito.when(tradeService.getFunds()).thenReturn(fund);
		mockMvc.perform(MockMvcRequestBuilders.get("/fund"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.is("Available fund = 1000.0")));
	}
	
	@Test
	public void shouldTestGetEquity() throws Exception{
		Equity hdfc = new Equity("hdfc", 10);
		List<Equity> list = new ArrayList<>();
		list.add(hdfc);
		Mockito.when(tradeService.getEquity()).thenReturn(list);
		mockMvc.perform(MockMvcRequestBuilders.get("/equity"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));
	}
	
	@Test
	public void shouldTestAddFundSuccessCase() throws Exception{
		double amount = 1000;
		Mockito.when(tradeService.addFunds(amount)).thenReturn(new Fund(amount));
		mockMvc.perform(MockMvcRequestBuilders.post("/fund?amount="+amount))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.is("Funds Added Sucessfully")));
	}
	
	@Test
	public void shouldTestAddFundFailCase() throws Exception{
		double amount = 1000;
		Mockito.when(tradeService.addFunds(amount)).thenReturn(null);
		mockMvc.perform(MockMvcRequestBuilders.post("/fund?amount="+amount))
		.andExpect(MockMvcResultMatchers.status().isInternalServerError())
		.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.is("Something went wrong.")));
	}
	
	@Test
	public void shouldTestTrade() throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		EquityDTO equityDto = new EquityDTO("hdfc", 2500, 2, "buy");
		mockMvc.perform(MockMvcRequestBuilders.post("/trade").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(equityDto)))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
}

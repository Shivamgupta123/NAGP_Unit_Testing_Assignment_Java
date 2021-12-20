package com.trader.eBroker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BrokerController {
	
	@GetMapping("/")
	public ResponseEntity<String> homePage(){
		return new ResponseEntity<String>("Home Page.", HttpStatus.OK);
	}
	
	
}

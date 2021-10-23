package com.hackerrank.stocktrade.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.stocktrade.model.Trade;

@RestController
@RequestMapping(value = "/trades")
public class TradesController
{
   private  List<Trade> trades = new ArrayList<Trade>();
   
	@RequestMapping(value = "/trades", consumes = "application/json" ,method = RequestMethod.POST)	
	public ResponseEntity createTrade(@RequestBody Trade trade) 
	{
		System.out.println("here");
		HttpStatus response = HttpStatus.CREATED;
		for (Trade existingtrade: trades) 
		{
			if (existingtrade.getId() == trade.getId())
			{
				response = HttpStatus.BAD_REQUEST;
			}
		}
		if(response == HttpStatus.CREATED)
		{
			trades.add(trade);
		}		
      
      return new ResponseEntity<>(trade, response);
	  }
	
}

package com.hackerrank.stocktrade.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackerrank.stocktrade.model.Trade;

@RestController
@RequestMapping(value = "/trades")
public class TradesController
{
   public static List<Trade> trades = new ArrayList<Trade>();
   
   
	@RequestMapping( consumes = "text/plain" ,method = RequestMethod.POST)	
	public ResponseEntity createTrade(@RequestBody String tradeStr) throws JsonParseException, JsonMappingException, IOException 
	{
		System.out.println(tradeStr);
//		ObjectMapper mapper = new ObjectMapper();
//		Trade trade = mapper.readValue(tradeStr, Trade.class);
//		
		HttpStatus response = HttpStatus.CREATED;
//		for (Trade existingtrade: trades) 
//		{
//			if (existingtrade.getId() == trade.getId())
//			{
//				response = HttpStatus.BAD_REQUEST;
//			}
//		}
//		if(response == HttpStatus.CREATED)
//		{
//			trades.add(trade);
//		}		
//      
      return new ResponseEntity<>(response);
	  }
	
	@RequestMapping(method = RequestMethod.GET)	
	public ResponseEntity getAllTrades(@RequestBody String tradeStr) throws JsonParseException, JsonMappingException, IOException 
	{
		  Collections.sort(trades);

		 return new ResponseEntity<>(trades, HttpStatus.OK);
	}
	
}

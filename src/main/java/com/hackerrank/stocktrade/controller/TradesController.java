package com.hackerrank.stocktrade.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
   
   
	@RequestMapping( consumes = "application/json" ,method = RequestMethod.POST)	
	public ResponseEntity createTrade(@RequestBody String tradeStr) throws JsonParseException, JsonMappingException, IOException 
	{
		System.out.println(tradeStr);
		ObjectMapper mapper = new ObjectMapper();
		Trade trade = mapper.readValue(tradeStr, Trade.class);
//		
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
      
      return new ResponseEntity<>(response);
	  }
	
	@RequestMapping(value = "/{id}" ,method = RequestMethod.GET)	
	public ResponseEntity getTrade(@PathVariable int id) throws JsonParseException, JsonMappingException, IOException 
	{
		for (Trade existingtrade: trades)
		{
			if (existingtrade.getId() == id)
			{
				return new ResponseEntity<>(existingtrade, HttpStatus.OK);
			}
		}

		return new ResponseEntity<>( HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(method = RequestMethod.GET)	
	public ResponseEntity getAllTrades() throws JsonParseException, JsonMappingException, IOException 
	{
		  Collections.sort(trades);

		 return new ResponseEntity<>(trades, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value = "/users/{userID}" ,method = RequestMethod.GET)	
	public ResponseEntity getTrades(@PathVariable int userID) throws JsonParseException, JsonMappingException, IOException 
	{
		boolean found = false;
		List<Trade> rettrades = new ArrayList<Trade>();
		for (Trade existingtrade: trades)
		{
			if (existingtrade.getUser().getId() == userID)
			{
				rettrades.add(existingtrade);
				found = true;
			}
		}
		if (found)
		{
			  Collections.sort(rettrades);
			return new ResponseEntity<>(rettrades, HttpStatus.OK);
		}
	
		return new ResponseEntity<>( HttpStatus.NOT_FOUND);
	}
	
	
}

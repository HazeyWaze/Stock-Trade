package com.hackerrank.stocktrade.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.stocktrade.model.Trade;

@RestController
@RequestMapping(value = "/erase")
public class ResourcesController {
    
	@RequestMapping(method = RequestMethod.DELETE)	
	public ResponseEntity deleteTrades() 
	{
	TradesController.trades= new ArrayList<Trade>();
      
      return new ResponseEntity<>( HttpStatus.OK);
	  }
	
	
}

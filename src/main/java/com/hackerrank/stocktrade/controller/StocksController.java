package com.hackerrank.stocktrade.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.hackerrank.stocktrade.model.Trade;

@RestController
@RequestMapping(value = "/stocks")
public class StocksController {
	
	@RequestMapping(value = "/{stockSymbol}/price?start={startDate}&end={endDate}" ,method = RequestMethod.GET)	
	public ResponseEntity getTradesFiltered(@PathVariable String stockSymbol, @PathVariable Timestamp start, Timestamp end) throws JsonParseException, JsonMappingException, IOException 
	{
		boolean found = false;
		Float priceH =0f;
		Float priceL = Float.MAX_VALUE;
		
		List<Trade> rettrades = new ArrayList<Trade>();
		for (Trade existingtrade: TradesController.trades)
		{
			if (existingtrade.getSymbol().equals(stockSymbol))
			{
				
				if ( isDateInRangeInc(existingtrade.getTimestamp(), start, end))
				{
					found = true;
					if (priceH < existingtrade.getPrice())
					{
						priceH = existingtrade.getPrice();
					}
					if (priceL > existingtrade.getPrice())
					{
						priceL = existingtrade.getPrice();
					}
				}
			}
		}
		if (found)
		{
	        String res = "{\"symbol\": \""+stockSymbol+"\", \"highest\": "+priceH+",  \"lowest\": "+priceL+"}";
return new ResponseEntity<>(res, HttpStatus.OK);
		}
        String res = "{\"message\": \"There are no trades in the given date range\"}";

		return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
	}
	
	private boolean isDateInRangeInc(Timestamp dateToCheck, Timestamp startDate, Timestamp endDate)
	{
		 return dateToCheck.compareTo(startDate) >= 0 && dateToCheck.compareTo(endDate) <=0;
	}
    
}

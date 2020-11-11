package com.jaehyun.stockRESTful.stock.controller;




import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jaehyun.stockRESTful.stock.Service.StockService;
import com.jaehyun.stockRESTful.stock.VO.MaxProfitVO;

@RestController
@RequestMapping("/stocks")
public class StockController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="stockService")
	StockService stockService;
	
	@GetMapping("/maximumprofit/{symbol}")
	public ResponseEntity maximumprofit(@PathVariable String symbol) {
		HttpHeaders headers=new HttpHeaders();
		headers.add("Access-Control-Allow-Origin","*");
		try {
			MaxProfitVO result=stockService.selectMaxProfit(symbol);
			return new ResponseEntity<>(result,headers,HttpStatus.OK);
		} catch (Exception e) {
			logger.info("stock service 예외발생");
			e.printStackTrace();
			return new ResponseEntity<>("Stock Symbol을 정확히 입력해주세요",headers,HttpStatus.BAD_REQUEST);
		}
	}
}

package com.jaehyun.stockRESTful.stock.Service;

import com.jaehyun.stockRESTful.stock.VO.MaxProfitVO;

/**
* @packageName  : com.jaehyun.stockRESTful.stock.Service
* @fileName     : StockService.java
* @JavaVersion  : 11
* @author       : jaehyun Park
* @description  : 비즈니스 로직 계층을 담당하는 서비스레이어 인터페이스
* @history      : 
*/
public interface StockService {
	
	/**
	* @methodName  : selectMaxProfit
	* @author      : jaehyun Park
	* @JavaVersion : 11
	* @param symbol
	* @return
	* @throws Exception
	* @description : 최대이윤 매도/매수일을 구하기위한 비즈니스 로직이 정의된 메서드 선언
	*/
	public MaxProfitVO selectMaxProfit(String symbol) throws Exception;
}

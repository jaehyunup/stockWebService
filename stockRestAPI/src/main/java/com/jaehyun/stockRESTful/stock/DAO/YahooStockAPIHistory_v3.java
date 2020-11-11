package com.jaehyun.stockRESTful.stock.DAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Request;

/**
* @packageName  : com.jaehyun.stockRESTful.stock.DAO
* @fileName     : YahooStockAPIHistory_v3.java
* @JavaVersion  : 11
* @author       : jaehyun Park
* @description  : third party API(YahooStockAPI)v3 를 이용할때 API Call Logic이 정의된 클래스 
* @history      : 
*/
@Repository("api")
public class YahooStockAPIHistory_v3 extends YahooStockAPIHistory{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	* @methodName  : createRequestURL
	* @author      : jaehyun Park
	* @JavaVersion : 11
	* @param symbol
	* @return
	* @throws NullPointerException
	* @description : Request URL 생성 메서드  
	*/
	@Override
	protected String createRequestURL(String symbol) throws NullPointerException {
		StringBuilder sb = new StringBuilder();
		sb.append("https://apidojo-yahoo-finance-v1.p.rapidapi.com/stock/v3/get-historical-data?"
				+ "region=US&symbol=").append(symbol);
		return sb.toString();
	}
	
	
	/**
	* @methodName  : getCallInstance
	* @author      : jaehyun Park
	* @JavaVersion : 11
	* @param symbol
	* @return
	* @throws Exception
	* @description : Request build 및 OkHttp call Instance 생성해서 반환 
	*/
	@Override
	protected Call getCallInstance(String symbol) throws Exception{
		Request request=null;
		try {
		request = new Request.Builder()
				.url(this.createRequestURL(symbol))
				.get()
				.addHeader("x-rapidapi-host", "apidojo-yahoo-finance-v1.p.rapidapi.com")
				.addHeader("x-rapidapi-key", "my-rapidapi-key")
				.build();
		}catch(NullPointerException e) {
			logger.info("API 요청을 위한 매개변수가 부족합니다.");
		}
		return client.newCall(request);
	}
}

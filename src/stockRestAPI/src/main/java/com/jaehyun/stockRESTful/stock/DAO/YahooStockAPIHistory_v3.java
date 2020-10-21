package com.jaehyun.stockRESTful.stock.DAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Request;

@Repository("api")
public class YahooStockAPIHistory_v3 extends YahooStockAPIHistory{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	protected String createRequestURL(String symbol) throws NullPointerException {
		StringBuilder sb = new StringBuilder();
		sb.append("https://apidojo-yahoo-finance-v1.p.rapidapi.com/stock/v3/get-historical-data?"
				+ "region=US&symbol=").append(symbol);
		return sb.toString();
	}
	@Override
	protected Call getCallInstance(String symbol) throws Exception{
		Request request=null;
		try {
		request = new Request.Builder()
				.url(this.createRequestURL(symbol))
				.get()
				.addHeader("x-rapidapi-host", "my-api-host")
				.addHeader("x-rapidapi-key", "my-api-key")
				.build();
		}catch(NullPointerException e) {
			logger.info("API 요청을 위한 매개변수가 부족합니다.");
		}
		return client.newCall(request);
	}
}

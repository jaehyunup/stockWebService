package com.jaehyun.stockRESTful.stock.DAO;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

/**
* @packageName  : com.jaehyun.stockRESTful.stock.DAO
* @fileName     : YahooStockAPIHistory.java
* @JavaVersion  : 11
* @author       : jaehyun Park
* @description  : 야후 API를 사용하기 위한 흐름을 정의한 getData()만을 인터페이스에서 implements 하고 
				  그에 필요한 createRequestURL(), getCallInstance() 메소드는 추상메소드로 두어
				  API 버전이나, requestURL에 대한 의존도를 낮추려고 하였습니다.
				  서비스에서 stockAPI 인터페이스의 getData()를 호출할때 그 안의 구성요소에 대해
				  알 필요도 없고, 알아서도 안된다고 생각했습니다.
* @history      : 
*/
public abstract class YahooStockAPIHistory implements stockAPI{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	protected abstract String createRequestURL(String symbol) throws Exception;
	protected abstract Call getCallInstance(String symbol) throws Exception;
	protected OkHttpClient client;
	
	YahooStockAPIHistory(){
		client = new OkHttpClient();
	}
	
	/**
	* @methodName  : getHistoryData
	* @author      : jaehyun Park
	* @JavaVersion : 11
	* @param symbol
	* @return
	* @throws Exception
	* @description : API 버전에 맞는 CallInstance를 생성하고 excute를 통해 API를 Call 하고 result(json body)를 반환함. 
	*/
	@Override
	public String getHistoryData(String symbol) throws Exception {
		Response res=null;
		try {
			res = getCallInstance(symbol).execute();
			if(res.isSuccessful()) {
				return res.body().string();
			}else {
				logger.info("Yahoo API status가 정상이 아닙니다.");
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}

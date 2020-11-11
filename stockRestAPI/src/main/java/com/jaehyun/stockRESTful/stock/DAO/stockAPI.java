package com.jaehyun.stockRESTful.stock.DAO;

/**
* @packageName  : com.jaehyun.stockRESTful.stock.DAO
* @fileName     : stockAPI.java
* @JavaVersion  : 11
* @author       : jaehyun Park
* @description  : API 
* @history      : API의 형태와, 그 형때에 따른 로직이 달라질 가능성도 있기 때문에 인터페이스로 추상화
*/
public interface stockAPI {
	/**
	* @methodName  : getHistoryData
	* @author      : jaehyun Park
	* @JavaVersion : 11
	* @param symbol
	* @return
	* @throws Exception
	* @description : 주식 정보 데이터 요청 메서드
	*/
	public String getHistoryData(String symbol) throws Exception;
}

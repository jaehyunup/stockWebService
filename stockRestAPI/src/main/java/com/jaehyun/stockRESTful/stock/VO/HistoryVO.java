package com.jaehyun.stockRESTful.stock.VO;

import java.util.Arrays;

/**
* @packageName  : com.jaehyun.stockRESTful.stock.VO
* @fileName     : HistoryVO.java
* @JavaVersion  : 11
* @author       : jaehyun Park
* @description  : 주식데이터가 저장될 VO
* @history      : 
*/
public class HistoryVO {
	private priceVO[] prices;

	public priceVO[] getPrices() {
		return prices;
	}

	public void setPrices(priceVO[] prices) {
		this.prices = prices;
	}

	@Override
	public String toString() {
		return "HistoryVO [prices=" + Arrays.toString(prices) + "]";
	}
	
}

package com.jaehyun.stockRESTful.stock.Service;

import com.jaehyun.stockRESTful.stock.VO.MaxProfitVO;

public interface StockService {
	/* 최대 이득 매도,매수일 알고리즘을 구현한다 */
	public MaxProfitVO selectMaxProfit(String symbol) throws Exception;
}

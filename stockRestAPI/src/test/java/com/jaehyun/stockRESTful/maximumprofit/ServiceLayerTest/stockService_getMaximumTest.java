package com.jaehyun.stockRESTful.maximumprofit.ServiceLayerTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.jaehyun.stockRESTful.stock.VO.HistoryVO;
import com.jaehyun.stockRESTful.stock.VO.MaxProfitVO;
import com.jaehyun.stockRESTful.stock.VO.priceVO;

class stockService_getMaximumTest {
	@Test
	@DisplayName("서비스에서 최대이윤을 남길 수 있는 매수/매도일을 MaxProfix에 잘 반환하는지")
	void MaximumStockService_getMaximumTest() {
		/* EVEN - 가짜 Json 을 주입하고, Gson을 통해 json Parsing 하여 HistoryVO 에 json을 맵핑한다 */
		/* 현재 가짜 JSON은 9월24일 기준 GOOG symbol으로 검색된 데이터입니다.*/
		String jsonBody = new FakeHistoryJson().getJson();
		Gson gson = new Gson();
		HistoryVO history = gson.fromJson(jsonBody, HistoryVO.class);
		
		/* WHEN - 매핑된 HistoryVO는 각 일자별 price를 가지고있으며, 이는 PriceVO로 표현하고있다. 
		 *  그것을 최대이윤 매수/매도일 반환 메서드(getMaximumProfix) 실행에 이용한다 */
		priceVO[] prices = history.getPrices();
		MaxProfitVO result = getMaximumProfit(prices);
		
		/* THEN - GOOG은 Mar 3월 23일 최저가이후로 현재 가격이 최고점이다. 이를 반환하는지 확인 
		 */
		String minDate="2020-03-23 13:30";
	    String maxDate="2020-09-02 13:30";
		assertTrue(result.getMinDate().equals(minDate));
		assertTrue(result.getMaxDate().equals(maxDate));
	}
	MaxProfitVO getMaximumProfit(priceVO[] priceVOList) {
		/* 주식데이터는 최근값이 가장 마지막으로 온다. */
		reverse(priceVOList);
		int minIdx = 0, maxIdx = 0;
		double sell, buy, min, profit = 0;
		for (int i = 0; i < priceVOList.length; i++) {
			// f
			if (priceVOList[i].getClose() < priceVOList[minIdx].getClose()) // 최소 갱신
				minIdx = i;
			else if (priceVOList[i].getClose() - priceVOList[minIdx].getClose() > profit) {
				buy = priceVOList[minIdx].getClose();
				sell = priceVOList[i].getClose();
				profit = priceVOList[i].getClose() - priceVOList[minIdx].getClose();
				maxIdx = i;
			}
		}
		Date minDate = new Date(priceVOList[minIdx].getDate() * 1000L);
		Date maxDate = new Date(priceVOList[maxIdx].getDate() * 1000L);
		
		/*Date 를 format에 맞게 변경.*/
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        String minDateText = sdf.format(minDate);
        String maxDateText = sdf.format(maxDate);
		return new MaxProfitVO(minDateText,maxDateText,profit);
	}
	/*reverse에 필요한 swap*/
	void swap(priceVO[] pricevo, int idx1, int idx2) {
		priceVO t = pricevo[idx1];
		pricevo[idx1] =  pricevo[idx2];
		pricevo[idx2] = t;
	}
	/* 배열 reverse */
	void reverse(priceVO[] pricevo) {
		for (int i = 0; i < pricevo.length / 2; i++) {
			swap(pricevo, i, pricevo.length - i - 1);
		}
	}
	
}

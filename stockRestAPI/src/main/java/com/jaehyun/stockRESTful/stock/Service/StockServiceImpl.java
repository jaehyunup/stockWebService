package com.jaehyun.stockRESTful.stock.Service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.jaehyun.stockRESTful.stock.DAO.stockAPI;
import com.jaehyun.stockRESTful.stock.VO.HistoryVO;
import com.jaehyun.stockRESTful.stock.VO.MaxProfitVO;
import com.jaehyun.stockRESTful.stock.VO.priceVO;


/**
* @packageName  : com.jaehyun.stockRESTful.stock.Service
* @fileName     : StockServiceImpl.java
* @JavaVersion  : 11
* @author       : jaehyun Park
* @description  : 최대이윤 매수/매도일 로직 구현클래스
* @history      : 
*/
@Service("stockService")
public class StockServiceImpl implements StockService {
	@Autowired
	private stockAPI api;

	/**
	* @methodName  : selectMaxProfit
	* @author      : jaehyun Park
	* @JavaVersion : 11
	* @param symbol (조회할 주식의 Symbol)
	* @return MaxProfitVO (해당 주식에 대해 180일간의 데이터에 대해 최대이윤 매수/매도일 및 수익이 담겨짐)
	* @throws Exception
	* @description : 
	*/
	@Override
	public MaxProfitVO selectMaxProfit(String symbol) throws Exception {
		String resultJsonString=null;
		priceVO[] priceList=null;
		try {
			resultJsonString=api.getHistoryData(symbol);
			Gson gson=new Gson();
			HistoryVO history = gson.fromJson(resultJsonString, HistoryVO.class);
			priceList=history.getPrices();
		}catch(NullPointerException e) {
			e.printStackTrace();
		}
		return getMaximumProfit(priceList,180);
	}
	
	/**
	* @methodName  : getMaximumProfit
	* @author      : jaehyun Park
	* @JavaVersion : 11
	* @param priceVOList - 주식시장 가격정보 리스트
	* @param dayLength - 몇일 전까지 계산할 것인지 ex)180이라면 180일 전까지의 데이터 분석
	* @return
	* @throws Exception
	* @description : 
	*/
	private MaxProfitVO getMaximumProfit(priceVO[] priceVOList,int dayLength) throws Exception{
		if(priceVOList.length < dayLength) {
			/*180 영업일만큼의 데이터가 없다면*/
			throw new Exception("Data가 dayLength보다 작습니다.");
		}
		// 180 영업일 기준으로 자르기
		priceVOList=Arrays.copyOfRange(priceVOList,0,dayLength);
		// 연산을위한 reverse
		reverse(priceVOList);
		int minIdx = 0, maxIdx = 0;
		double sell, buy, min, profit = 0;
		for (int i = 0; i < dayLength; i++) {
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
		
		/*Date를 format에 맞게 변경*/	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        String minDateText = sdf.format(minDate);
        String maxDateText = sdf.format(maxDate);
		return new MaxProfitVO(minDateText,maxDateText,profit);
	}
	
	/**
	* @methodName  : swap
	* @author      : jaehyun Park
	* @JavaVersion : 11
	* @param pricevo
	* @param idx1
	* @param idx2
	* @description : value swap method 
	*/
	private void swap(priceVO[] pricevo, int idx1, int idx2) {
		priceVO t = pricevo[idx1];
		pricevo[idx1] =  pricevo[idx2];
		pricevo[idx2] = t;
	}
	
	/**
	* @methodName  : reverse
	* @author      : jaehyun Park
	* @JavaVersion : 11
	* @param pricevo
	* @description : 주식데이터 순서를 바꾸기위한 method (주식 데이터가 반대로 제공되기 때문) 
	*/
	private void reverse(priceVO[] pricevo) {
		for (int i = 0; i < pricevo.length / 2; i++) {
			swap(pricevo, i, pricevo.length - i - 1);
		}
	}
}

package com.jaehyun.stockRESTful.stock.Service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.jaehyun.stockRESTful.stock.DAO.YahooStockAPIHistory_v3;
import com.jaehyun.stockRESTful.stock.DAO.stockAPI;
import com.jaehyun.stockRESTful.stock.VO.HistoryVO;
import com.jaehyun.stockRESTful.stock.VO.MaxProfitVO;
import com.jaehyun.stockRESTful.stock.VO.priceVO;


@Service("stockService")
public class StockServiceImpl implements StockService {
	/*비즈니스을 정의한다, 즉 서비스 레이어의 책임은 모델 레이어의 데이터를 프레젠테이션 계층에
	  적절하게 보여주기 위한 일련의 로직들의 구현에 관한 책임을 가진다. */
	@Autowired
	private stockAPI api;
	
	
	/* @Params String symbol - 조회할 주식의 Symbol
	 * @Return MaxProfitVO - 해당 주식에 대해 180일간의 데이터에 대해 최대이윤 매수/매도일 및 수익을 return 한다.  
	 * 
	 * */
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
	
	/*
	 * @Params priceVO[] priceVOList - 주식시장 가격정보 리스트
	 * 		   int dayLength - 해당 일까지의 데이터만 연산하기 위함.
	 * */
	private MaxProfitVO getMaximumProfit(priceVO[] priceVOList,int dayLength) throws Exception{
		/* 야후 주식데이터는 최근값이 가장 앞에 오게 반환되어지기 때문에 반대로 변환한다. */
		
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
	
	/*reverse에 필요한 swap*/
	private void swap(priceVO[] pricevo, int idx1, int idx2) {
		priceVO t = pricevo[idx1];
		pricevo[idx1] =  pricevo[idx2];
		pricevo[idx2] = t;
	}
	/* 배열 reverse */
	private void reverse(priceVO[] pricevo) {
		for (int i = 0; i < pricevo.length / 2; i++) {
			swap(pricevo, i, pricevo.length - i - 1);
		}
	}
}

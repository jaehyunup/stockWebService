package com.jaehyun.stockRESTful.stock.DAO;

import com.squareup.okhttp.Call;

public interface stockAPI {
	/* api를 이용하여 Json 형태의 주식 데이터를 받아오는 행위 자체는 변함이 없습니다.
	 * 하지만 이 행위에 이용되는 API의 형태와, 그 형때에 따른 로직이 달라질 가능성도 있다고 생각했습니다.
	 * 따라서 인터페이스로 추상화하였습니다.
	 * */
	public String getHistoryData(String symbol) throws Exception;
}

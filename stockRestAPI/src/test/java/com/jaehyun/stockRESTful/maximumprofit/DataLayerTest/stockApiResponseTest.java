package com.jaehyun.stockRESTful.maximumprofit.DataLayerTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.jaehyun.stockRESTful.stock.DAO.YahooStockAPIHistory_v3;
import com.jaehyun.stockRESTful.stock.DAO.stockAPI;


/**
* @packageName  : com.jaehyun.stockRESTful.maximumprofit.DataLayerTest
* @fileName     : stockApiResponseTest.java
* @JavaVersion  : 11
* @author       : jaehyun Park
* @description  : 주식 데이터 API 콜 확인 
* @history      : 
*/
class stockApiResponseTest {
	@Test
	@DisplayName("API 콜이 정상적으로 되는지 확인")
	void API_Service_Unit_Test_By_yahooAPI_history_v3() throws Exception{
		/*  EVEN 
 		 *  만약 stockAPI가 변경된다면 API 호출 객체만 바꿔 끼워주고 이 아래부터의 코드는 변경할 필요가 없다.*/
		stockAPI stockapi=new MockYahooStockApiHistory_v3();
		/*  WHEN  */
		String msg=stockapi.getHistoryData("GOOG");
		/*  THEN  */
		try {
			assertNotNull(msg);
		}catch(AssertionError e) {
			fail("status code is not succesfuly");
			return;
		}
	}
	static class MockYahooStockApiHistory_v3 extends YahooStockAPIHistory_v3{
	}
}

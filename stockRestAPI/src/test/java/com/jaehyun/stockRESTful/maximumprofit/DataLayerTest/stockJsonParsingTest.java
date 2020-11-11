package com.jaehyun.stockRESTful.maximumprofit.DataLayerTest;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.jaehyun.stockRESTful.maximumprofit.ServiceLayerTest.FakeHistoryJson;
import com.jaehyun.stockRESTful.stock.VO.HistoryVO;
import com.jaehyun.stockRESTful.stock.VO.priceVO;

class stockJsonParsingTest {
	@Test
	@DisplayName("Json을 HistoryVO에 맵핑하여 priceVO 배열에 반환 되는지 확인")
	void Json_To_Object_Array_MappingBy_Gson_isCollect_Test() {
		/* EVEN -테스트시 Json 오브젝트는 FakeHistoryJson Class를 통해 주입할 수 도있고
		         API 호출을 통해 실제 값을 넣어 테스트를 할 수도 있다. */
		String jsonBody=new FakeHistoryJson().getJson();
		
		/* WHEN - GSON을 통한 Json to Object Mapping	*/
		Gson gson=new Gson();
		HistoryVO history = gson.fromJson(jsonBody, HistoryVO.class);
		priceVO[] prices=history.getPrices();
		
		/* THEN - Data Length가 0이라면 */
		assertFalse(prices.length ==0);
	}
}

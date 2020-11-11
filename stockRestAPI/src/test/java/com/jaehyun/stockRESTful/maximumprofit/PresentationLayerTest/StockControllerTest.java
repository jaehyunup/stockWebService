package com.jaehyun.stockRESTful.maximumprofit.PresentationLayerTest;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.jaehyun.stockRESTful.stock.Service.StockService;
import com.jaehyun.stockRESTful.stock.VO.MaxProfitVO;
import com.jaehyun.stockRESTful.stock.controller.StockController;

/**
* @packageName  : com.jaehyun.stockRESTful.maximumprofit.PresentationLayerTest
* @fileName     : StockControllerTest.java
* @JavaVersion  : 11
* @author       : jaehyun Park
* @description  : MockMVC를 통한 서비스 테스트
* @history      : 
*/
@WebMvcTest
public class StockControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean(name="stockService")
	private StockService stockService;
	
	@InjectMocks
	private StockController stockController;
	
    @Test
    @DisplayName("StockController가 Request에 적절한 Json을 Response 하는지")
    public void StockController_Unit_Test() throws Exception {
        String minDate="2020-03-23 13:30";
        String maxDate="2020-09-02 13:30";
        
        MaxProfitVO testVO=new MaxProfitVO(minDate,maxDate,671.6600341796875);
        when(stockService.selectMaxProfit("GOOG")).thenReturn(testVO);
        /*Mock Service인 stockService는 selectMaxProfit("GOOG")메소드에 대한 요청에 testVO를 리턴한다.*/
        mockMvc.perform(get("/stocks/maximumprofit/{symbol}","GOOG"))
       		   .andExpect(status().isOk())
       		   .andDo(print())
        	   .andExpect(jsonPath("minDate").value(minDate))
        	   .andExpect(jsonPath("maxDate").value(maxDate))
        	   .andExpect(jsonPath("profit").value(671.6600341796875));
    }
}

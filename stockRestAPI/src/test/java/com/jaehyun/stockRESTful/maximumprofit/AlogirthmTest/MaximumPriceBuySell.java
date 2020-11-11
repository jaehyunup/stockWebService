package com.jaehyun.stockRESTful.maximumprofit.AlogirthmTest;


import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
* @packageName  : com.jaehyun.stockRESTful.maximumprofit.AlogirthmTest
* @fileName     : MaximumPriceBuySell.java
* @JavaVersion  : 11
* @author       : jaehyun Park
* @description  : 최대이윤 매수/매도일 알고리즘 테스트 
* @history      : 
*/
class MaximumPriceBuySell {
	
	@Test
	@DisplayName("알고리즘이 결과적으로 최소값과 최대값을 반환하는지 확인한다")
	void test() {
		assertTrue(checkTestCase1(new String[] {"2","2"},getMaximumPriceBuySell(new int[] {2,2,2,2,2,2})));
	}
	boolean checkTestCase1(String[] testAnswer,String[] result) {
		for (int i = 0; i < testAnswer.length; i++) {
			if(!result[i].equals(testAnswer[i])) {
				return false;
			}
		}
		return true;
	}
	String[] getMaximumPriceBuySell(int arr[]) {
	    int i = 0, buy = 0, sell = 0, min = 0, profit = 0;
	    int callCount=0;
	    for (i = 0; i < arr.length; i++) {
	    	callCount++;
	        if (arr[i] < arr[min])
	            min = i;
	        else if (arr[i] - arr[min] > profit) {
	            buy = min; 
	            sell = i;
	            profit = arr[i] - arr[min];
	        }
	    }
//	    System.out.println("최적 매수가는 : " + arr[buy] + "에 사서  " + arr[sell] + 
//	            "에 파는것이고 이때의 이익은 " + profit +"입니다, 함수호출 횟수는"+callCount+"회 입니다.");
	    return new String[] {Integer.toString(arr[buy]),Integer.toString(arr[sell])};
	}
}

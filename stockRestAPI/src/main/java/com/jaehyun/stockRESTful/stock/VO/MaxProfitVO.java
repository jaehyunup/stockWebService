package com.jaehyun.stockRESTful.stock.VO;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MaxProfitVO {
	String minDate;
	String maxDate;
	double profit;
	
	public String getMinDate() {
		return minDate;
	}
	public void setMinDate(String minDate) {
		this.minDate = minDate;
	}
	public String getMaxDate() {
		return maxDate;
	}
	public void setMaxDate(String maxDate) {
		this.maxDate = maxDate;
	}
	public double getProfit() {
		return profit;
	}
	public void setProfit(double profit) {
		this.profit = profit;
	}
	@Override
	public String toString() {
		return "MaxProfitVO [minDate=" + minDate + ", maxDate=" + maxDate + ", profit=" + profit + "]";
	}
	public MaxProfitVO(String minDate, String maxDate, double profit) {
		this.minDate = minDate;
		this.maxDate = maxDate;
		this.profit = profit;
	}
	public MaxProfitVO() {
		
	}
}

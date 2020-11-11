package com.jaehyun.stockRESTful.stock.VO;

/**
* @packageName  : com.jaehyun.stockRESTful.stock.VO
* @fileName     : priceVO.java
* @JavaVersion  : 11
* @author       : jaehyun Park
* @description  : 주식시장 json 데이터 파싱 이후 담겨질 VO 
* @history      : 
*/
public class priceVO {
	
	private long date;
	private double open;
	private double high;
	private double low;
	private double close;
	private long volume;
	private double adjclose;
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
	public double getOpen() {
		return open;
	}
	public void setOpen(double open) {
		this.open = open;
	}
	public double getHigh() {
		return high;
	}
	public void setHigh(double high) {
		this.high = high;
	}
	public double getLow() {
		return low;
	}
	public void setLow(double low) {
		this.low = low;
	}
	public double getClose() {
		return close;
	}
	public void setClose(double close) {
		this.close = close;
	}
	public long getVolume() {
		return volume;
	}
	public void setVolume(long volume) {
		this.volume = volume;
	}
	public double getAdjclose() {
		return adjclose;
	}
	public void setAdjclose(double adjclose) {
		this.adjclose = adjclose;
	}
	@Override
	public String toString() {
		return "priceVO [date=" + date + ", open=" + open + ", high=" + high + ", low=" + low + ", close=" + close
				+ ", volume=" + volume + ", adjclose=" + adjclose + "]";
	}
}

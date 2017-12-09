package com.binance.dto;

public class Order {
	double coinAmount;
	double salePrice;
	public double getCoinAmount() {
		return coinAmount;
	}
	public void setCoinAmount(double coinAmount) {
		this.coinAmount = coinAmount;
	}
	public double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}
}

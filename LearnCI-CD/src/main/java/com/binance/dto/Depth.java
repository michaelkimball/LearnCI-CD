package com.binance.dto;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Depth {
	private int lastUpdateId;
	private ArrayList<Double> bidX;
	private ArrayList<Double> bidY;
	private ArrayList<Double> askX;
	private ArrayList<Double> askY;
	private double highestCoinAmount;
	private double highestSalePrice;
	private double lowestSalePrice;
	private double combinedCoins;
	
	public Depth() {
		highestCoinAmount = 0;
		highestSalePrice = 0;
		lowestSalePrice = Double.MAX_VALUE;
	}
	
	public int getLastUpdateId() {
		return lastUpdateId;
	}
	public void setLastUpdateId(int lastUpdateId) {
		this.lastUpdateId = lastUpdateId;
	}
	
	public ArrayList<Double> getBidX(){
		return bidX;
	}
	
	public ArrayList<Double> getBidY(){
		return bidY;
	}
	
	public ArrayList<Double> getAskX(){
		return askX;
	}
	
	public ArrayList<Double> getAskY(){
		return askY;
	}
	
	private void updateLowestSalePrice(double salePrice){
		if (salePrice < this.lowestSalePrice)
			this.lowestSalePrice = salePrice;
	}
	
	private void updateHighestSalePrice(double salePrice){
		if (salePrice > this.highestSalePrice)
			this.highestSalePrice = salePrice;
	}
	
	private void updateHighestCoinAmount(){
		if (this.combinedCoins > this.highestCoinAmount)
			this.highestCoinAmount = this.combinedCoins;
	}
	
	private void updateCombinedCoins(double coinAmount){
		this.combinedCoins += coinAmount;
	}
	
	private void resetCombinedCoins(){
		this.combinedCoins = 0;
	}

	public void setBids(ArrayList<ArrayList<Object>> bids) {
		ArrayList<Order> orderBids = convertToOrder(bids);
		bidX = getOrderX(orderBids);
		bidY = getOrderY(orderBids);
	}

	public void setAsks(ArrayList<ArrayList<Object>> asks) {
		ArrayList<Order> orderAsks = convertToOrder(asks);
		askX = getOrderX(orderAsks);
		askY = getOrderY(orderAsks);
	}
	
	private double getCombinedCoins(){
		return this.combinedCoins;
	}
	
	private ArrayList<Order> convertToOrder(ArrayList<ArrayList<Object>> raw){
		ArrayList<Order> orders = new ArrayList<Order>();
		raw.forEach(orderRawData -> {
			Order order = new Order();
			order.setSalePrice(Double.valueOf(String.valueOf(orderRawData.get(0))));
			order.setCoinAmount(Double.valueOf(String.valueOf(orderRawData.get(1))));
			orders.add(order);
		});
		return orders;
	}
	
	private ArrayList<Double> getOrderX(ArrayList<Order> orderCoordinateList){
		ArrayList<Double> orderSingleAxisCoordinates = new ArrayList<>();
		orderCoordinateList.forEach((coordinate) -> {
			orderSingleAxisCoordinates.add(coordinate.getSalePrice());
			updateLowestSalePrice(coordinate.getSalePrice());
			updateHighestSalePrice(coordinate.getSalePrice());
		});
		return orderSingleAxisCoordinates;
	}
	
	private ArrayList<Double> getOrderY(ArrayList<Order> orderCoordinateList){
		ArrayList<Double> orderSingleAxisCoordinates = new ArrayList<>();
		resetCombinedCoins();
		orderCoordinateList.forEach((coordinate) -> {
			updateCombinedCoins(coordinate.getCoinAmount());
			orderSingleAxisCoordinates.add(getCombinedCoins());
		});
		updateHighestCoinAmount();	
		return orderSingleAxisCoordinates;
	}
	
	public double getHighestCoinAmount() {
		return highestCoinAmount;
	}

	public double getHighestSalePrice() {
		return highestSalePrice;
	}

	public double getLowestSalePrice() {
		return lowestSalePrice;
	}
	
}

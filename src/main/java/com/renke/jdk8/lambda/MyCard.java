package com.renke.jdk8.lambda;

public class MyCard {
	public String cardId = "";
	public String cardName = "";
	public MyCard(String cardId,String cardName){
		this.cardId = cardId;
		this.cardName = cardName;
	}
	@Override
	public String toString() {
		return "MyCard [cardId=" + cardId + ", cardName=" + cardName + "]";
	}
}

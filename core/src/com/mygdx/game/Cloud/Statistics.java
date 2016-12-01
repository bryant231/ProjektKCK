package com.mygdx.game.Cloud;

public class Statistics extends cloud{
	private int statistic;

	public Statistics(int WIDTH, float X, float Y) {
		super(WIDTH, X, Y);
		init();
		// TODO Auto-generated constructor stub
	}
	
	public void init() {
		statistic = 3;
	}

	public void change(int wartosc) {
		statistic = statistic + wartosc;   //do statystyk odejmujemy badz dodajemy statystyki. np jesli ktos nas zraznil to trzeba wpisac -1 co w rzeczywistosci odejmie od obecnego hp 1
	}
	
	public String getStatistic() {
		String returned;
		returned = ""+ statistic;
		return returned;
	}
}

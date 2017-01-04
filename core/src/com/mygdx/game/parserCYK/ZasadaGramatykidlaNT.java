package com.mygdx.game.parserCYK;

public class ZasadaGramatykidlaNT {
	String LS="",PS1="",PS2="";
	
	//Ustaw zawartosc pol w zasadzie nieterminalnej
	
	public void UstawLS(String lewo){
		LS=lewo;
	}
	public void UstawPS1(String ps1){
		PS1=ps1;
	}
	public void UstawPS2(String ps2){
		PS2=ps2;
	}
	
	//Podaj zawartosc pol w zasadzie nieterminalnej
	
	public String PodajLS(){
		return LS;
	}
	public String PodajPS1(){
		return PS1;
	}
	public String PodajPS2(){
		return PS2;
	}
}

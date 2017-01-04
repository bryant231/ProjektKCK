package com.mygdx.game.parserCYK;

public class ZasadaGramatyki {
	String LS="",PS="";
	
	//Ustaw zawartosc pol w zasadzie terminalnej
	
	public void UstawLSPS(String lewo, String prawo){
		LS=lewo;
		PS=prawo;
	}
	
	//Podaj zawartosc pol w zasadzie terminalnej
	
	public String PodajLS(){
		return LS;
	}
	public String PodajPS(){
		return PS;
	}
}

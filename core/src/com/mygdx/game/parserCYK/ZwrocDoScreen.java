package com.mygdx.game.parserCYK;

import java.util.ArrayList;
import java.util.List;

public class ZwrocDoScreen {
	//Tu chyba mozna zamienic liste na tablice
	List<String> lista_co_zwracam = new ArrayList<String>();
	int liczba_kratek=0;
	Boolean czy_liczba_kratek;
	//Zrobic liste intow
	Boolean czy_liczba=false;
	int liczba=0;
	
	//Klasa, kt�rej element zwracam do *Screen z Parsera
	
	public ZwrocDoScreen(){
		this.czy_liczba_kratek = false;
		lista_co_zwracam.add(0, "N");
		lista_co_zwracam.add(1, "N");
		lista_co_zwracam.add(2, "N");
	}
	
	public void DodajElementLista_co_zwracam(int indx,String ad) {
		this.lista_co_zwracam.add(indx,ad);
	}
	
	public void UstawLiczba_kratek(int liczba_kratek) {
		this.liczba_kratek = liczba_kratek;
	}
	
	public void UstawCzy_liczba_kratek(Boolean a){
		this.czy_liczba_kratek = a;
	}
	
	public String PodajElementLista_co_zwracam(int indeks) {
		return lista_co_zwracam.get(indeks);
	}
	
	public int PodajRozmiarLista_co_zwracam(){
		return lista_co_zwracam.size();
	}

	public int PodajLiczba_kratek() {
		return liczba_kratek;
	}
	public Boolean PodajCzy_liczba_kratek(){
		return czy_liczba_kratek;
	}
	public int PodajLiczba(){
		return liczba;
	}
	public void UstawLiczba(int liczba2){
		liczba = liczba2;
	}
	public Boolean PodajCzyLiczba(){
		return czy_liczba;
	}
	public void UstawCzyLiczba(Boolean czy){
		czy_liczba = czy;
	}
	
}

package com.mygdx.game.actors;

import java.io.IOException;


import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.parserCYK.Parserv3;
import com.mygdx.ingameConsole.Console;

public class NPC extends Entity{


	public NPC(String Type, int X, int Y, String sciezka, Stage stage, Console console, Parserv3 parser, int a, int b,
			int c, int d) throws IOException {
		super(Type, X, Y, sciezka, stage, console, parser, a, b, c, d);
		// TODO Auto-generated constructor stub
	}
	

	
	public void sayHello(){
		
		Boolean mcSaidHello=false;
		
		int licznik=0;
	
		
		if(MainCharacertInside == true){
			if(console.phraseEntereddlaNPC == true){
				console.phraseEntereddlaNPC = false;
				for(int i=0;i<Parserv3.ttab.length;i++){
					if(Parserv3.ttab[i].PodajLS().equals("przywitanie")){
						if(console.LastSentenceInConsole.contains(Parserv3.ttab[i].PodajPS())){
							mcSaidHello=true;
							break;
						}
					}
				}
				int l=0;
				if(mcSaidHello == true){
					mcSaidHello = false;
					//Licze ile jest przywitan
					for(int g=0;g<Parserv3.ttab.length;g++){
						if(Parserv3.ttab[g].PodajLS().equals("przywitanie")){
							licznik++;
						}
					}
					//Wpisuje przywitania do tablicy
					String[] temptab = new String [licznik];
					for(int h=0;h<Parserv3.ttab.length;h++){
						if(Parserv3.ttab[h].PodajLS().equals("przywitanie")){
							temptab[l]=Parserv3.ttab[h].PodajPS();
							l++;
						}
					}
					int wyl=0;
					//Losuje liczbe
					wyl = generator.nextInt(licznik);
					
					this.Speak(temptab[wyl]);
					
				}
			}
		}

	}
	
	
	public void testPlayer(){

		this.Speak("Oblicz 2+2!");
		if(console.phraseEntereddlaNPC == true){
			console.phraseEntereddlaNPC = false;
			if(console.LastSentenceInConsole.contains("4")){
				this.Speak("Dobrze!");
				
			}
		}
	}
	



}

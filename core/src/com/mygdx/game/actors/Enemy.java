package com.mygdx.game.actors;

import java.io.IOException;
import java.util.*;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.parserCYK.Parserv3;
import com.mygdx.ingameConsole.Console;

public class Enemy extends Entity {

	public Enemy(String Type, int X, int Y, String sciezka, Stage stage, Console console, Parserv3 parser, int a, int b,
			int c, int d) throws IOException {
		super(Type, X, Y, sciezka, stage, console, parser, a, b, c, d);
	}
	
	protected Vector<String> list_of_words = new Vector<String>(); //Vector uzytych slow 
	protected String LastWordUsed;
	protected float MaxHP = 100;
	protected float CurrentHP = 100;
	protected int NumberOfRepetitions = 0;

	public void IsHitbyMC(){
		if(MainCharacterInside == true){
				for (int i = 0; i < list_of_words.size(); i++)	
				{
					NumberOfRepetitions++;
					//TU JESZCZE TRZEBA ROZBUDOWAC
					if (LastWordUsed == list_of_words.get(i))
					{
						NumberOfRepetitions++;
					}
				}
				DealBasicDamage(NumberOfRepetitions);
				list_of_words.add(LastWordUsed);
				NumberOfRepetitions = 0;
			}
	}
	
	public void SetUsedWord(String slowo){
		LastWordUsed = slowo;
	}
	
	String GetUzyteSlowo()
	{
		return LastWordUsed;
	}
	
	void DealBasicDamage(int RepeatedWords)	{
		float damage;
		damage = 10;
		for (int i = 0; i < RepeatedWords; i++)
		{
			damage = (float) (damage*0.8);
		}
		setCurrentHP(damage);
		System.out.println(CurrentHP);
	}
	
	private void setCurrentHP(float damage) {
		CurrentHP = CurrentHP - damage;
	}

	public void main()
	{
		IsHitbyMC();
	}
}
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
	
	protected Vector<String> list_of_words = new Vector<String>(); //Vector uzytycvh slow 
	protected String LastWordUsed;
	protected float MaxHP = 100;
	protected float CurrentHP = 100;
	protected int NumberOfRepetitions = 0;
	protected int NumberOfAttacks = 0;
	public boolean Defeated = false;
	
	public void IsHitbyMC()
	{	
		if (Defeated == false) {
		if(MainCharacterInside == true)
		{
			NumberOfAttacks++;
			list_of_words.add(LastWordUsed);
				for (int i = 0; i < list_of_words.size()-1; i++)	
				{
					if (list_of_words.get(i).equals(list_of_words.lastElement()))
					{
						NumberOfRepetitions++;
					}
				}
			DealBasicDamage(NumberOfRepetitions);
			NumberOfRepetitions = 0;
		}
		displayDamage();
		if (CurrentHP < 0)
		{
			defeat();
		}
		}
	}
	
	public void defeat()
	{
			this.image.remove();
			Defeated = true;
			
	}
	
	public void SetUsedWord(String slowo){
		LastWordUsed = slowo;
	}
	
	String GetUzyteSlowo()
	{
		return LastWordUsed;
	}
	
	void ClearUzyteSlowo()
	{
		LastWordUsed = "";
	}

	public void displayDamage()
	{
		
        String temp1 = String.valueOf(CurrentHP);
        if (temp1.length() > 4)
        {
            temp1 = temp1.substring(0, 4);
        }
        String temp2 = String.valueOf(MaxHP);
        this.Speak(temp1 + "/" + temp2,(float) 0.1);
	
	}
	
	void DealBasicDamage(int RepeatedWords)	{
		float damage;
		damage = 10;
		for (int i = 0; i < RepeatedWords; i++)
		{
			damage = (float) (damage*0.8);
		}
		setCurrentHP(damage);
	}
	
	private void setCurrentHP(float damage) {
		CurrentHP = CurrentHP - damage;
	}

}
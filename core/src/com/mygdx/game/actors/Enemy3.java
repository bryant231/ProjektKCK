package com.mygdx.game.actors;

import java.io.IOException;
import java.util.*;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.parserCYK.Parserv3;
import com.mygdx.ingameConsole.Console;

public class Enemy3 extends Entity {

	public Enemy3(String Type, int X, int Y, String sciezka, Stage stage, Console console, Parserv3 parser, int a, int b,
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
	protected int DlugoscSlowa = 0;
	protected int DlugoscMax = 0;
	
	
	public void IsHitbyMC(MainCharacter mainCharacter)
	{	
		if (Defeated == false) {
			if(MainCharacterInside == true)
			{
				DlugoscSlowa = LastWordUsed.length();
				if (DlugoscSlowa <= DlugoscMax)
				{
					NumberOfRepetitions++;
					DealBasicDamage(NumberOfRepetitions);
				}
				
				else
				{
					NumberOfAttacks++;
					NumberOfRepetitions = 0;
					DealBasicDamage(NumberOfRepetitions);
					DlugoscMax = DlugoscSlowa;
				}
				
				
				if (NumberOfAttacks > 10)
				{
					CurrentHP = 100;
					NumberOfAttacks = 0;
					this.Speak("Tak mnie nie pokonasz!", (float) 0.1);
				}
				
			}
			
			displayDamage();
			if (CurrentHP < 0)
			{
				defeat();
				mainCharacter.statistics[0].change(1);
				mainCharacter.statistics[1].change(1);
				mainCharacter.statistics[2].change(1);
				mainCharacter.statistics[3].change(1);
				mainCharacter.statistics[0].textField.setMessageText(mainCharacter.statistics[0].getStatistic());
				mainCharacter.statistics[1].textField.setMessageText(mainCharacter.statistics[1].getStatistic());
				mainCharacter.statistics[2].textField.setMessageText(mainCharacter.statistics[2].getStatistic());
				mainCharacter.statistics[3].textField.setMessageText(mainCharacter.statistics[3].getStatistic());
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
		this.Speak(temp1 + "/" + temp2,(float) 0.001);
	
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
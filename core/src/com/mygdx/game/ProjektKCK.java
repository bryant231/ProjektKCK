package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.screens.MenuScreen;

public class ProjektKCK extends Game {
	
	public static int WIDTH = 1024;   // ustawiamy statyczne zmienne ktore odpowiadaja
	public static int HEIGHT = 768;   // za rozdzielczosc gry
	
	public static String GameName = "ProjektKCK"; //nazwa gry
	
	@Override
	public void create() {
		this.setScreen(new MenuScreen(this));   //prze³¹czamy ekran na menu screen
	}


}

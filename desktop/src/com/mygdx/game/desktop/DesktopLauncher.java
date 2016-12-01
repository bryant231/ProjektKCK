package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.ProjektKCK;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.height = ProjektKCK.HEIGHT;
		config.width = ProjektKCK.WIDTH;
		config.resizable = true;
		config.title = ProjektKCK.GameName;
		new LwjglApplication(new ProjektKCK(), config);
	}
}

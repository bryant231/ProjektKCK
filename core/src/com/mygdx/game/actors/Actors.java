package com.mygdx.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.math.Rectangle;

public class Actors extends Actor {
	public Image image;
	public Texture texture;
	public TextureRegion region;
	public Rectangle bounds;
	public Actors(int X, int Y, String sciezka) {
		bounds = new Rectangle();   //potrzebne by wykrywac kolizje
		texture = new Texture(Gdx.files.internal(sciezka));
		region = new TextureRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
		image = new Image(region);
		image.setBounds(X, Y, texture.getWidth(), texture.getHeight());
	}

}
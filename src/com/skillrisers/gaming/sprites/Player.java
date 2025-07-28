package com.skillrisers.gaming.sprites;

import javax.swing.ImageIcon;

public class Player extends Sprite {
	
	public Player() {
		w = 300;
		h = 350;
		x = 10;
		y = 310;
		image = new ImageIcon(Player.class.getResource("player.gif"));
	}
	public void move() {
		x = x + speed;
	}
	public boolean outOfScreen() {
		return x>=1400;
	}
}

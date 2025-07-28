package com.skillrisers.gaming;

import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.skillrisers.gaming.sprites.Enemy;
import com.skillrisers.gaming.sprites.Player;

public class Board extends JPanel{
	Timer timer;
	BufferedImage backgroundImage;
	Player player;
	Enemy enemies[] = new Enemy[3];
	public Board() {
		setSize(1500,820);
		loadBackgroundImage();
		player = new Player();
		
		setFocusable(true);
		bindEvents();
		loadEnemies();
		gameLoop();
	}
	
	private void gameOver(Graphics pen) {
		if(player.outOfScreen()) {
			pen.setFont(new Font("times", Font.BOLD, 90));
			pen.setColor(Color.RED);
			pen.drawString("GAME WIN", 1500/3, 820/2); //center display of message
			timer.stop();
			return ;
		}
		for(Enemy enemy : enemies) {
			if(isCollide(enemy)) {
				pen.setFont(new Font("times", Font.BOLD, 90));
				pen.setColor(Color.RED);
				pen.drawString("GAME OVER", 1500/3, 820/2); //center display of message
				timer.stop();
			}
		}
	}
	private boolean isCollide(Enemy enemy) {
		int xDistance = Math.abs(player.x - enemy.x);
		int yDistance = Math.abs(player.y - enemy.y);
		int maxH = Math.max(player.h,  enemy.h);
		int maxW = Math.max(player.w,  enemy.w);
		return xDistance <= maxW-150 && yDistance <= maxH-300;
	}
	
	private void bindEvents() {
		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_RIGHT) {   // VK-Virtual Key (player will move by clicking right key only) 
					player.speed = 10;
				}
				else if(e.getKeyCode()==KeyEvent.VK_LEFT) {
					player.speed = -10; //to go back press left key 
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				player.speed = 0;
			}
			
		});
	}

	private void loadEnemies() {
		int x = 250;
		int gap = 450;
		int speed = 5;
		for(int i=0; i<enemies.length; i++) {
			enemies[i] = new Enemy(x, speed);
			x = x + gap;
			speed = speed + 5;
		}
	}
	
	private void gameLoop() {
		timer = new Timer(50, (e)->repaint());
		timer.start();
	}
	
	private void loadBackgroundImage() {
		try {
			backgroundImage = ImageIO.read(Board.class.getResource("game_background.jpg"));
		} catch (IOException e) {
			System.out.println("Background image not found...");
			System.exit(1);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void printEnemies(Graphics pen) {
		for(Enemy enemy : enemies) {
			enemy.draw(pen);
			enemy.move();
		}
	}
	@Override
	public void paintComponent(Graphics pen) {
		super.paintComponent(pen); //clean up
		// all printing logic will be here
		pen.drawImage(backgroundImage,0,0,1500,850,null);
		player.draw(pen);
		player.move();
		printEnemies(pen);
		gameOver(pen);
	}
}

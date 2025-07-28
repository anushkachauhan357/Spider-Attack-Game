package com.skillrisers.gaming;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
	
	public GameFrame() {
		Board board = new Board();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("SPIDER ATTACK - JAVA GAME");
		setSize(1500,820);
		setResizable(false); //maximize option disabled
		setLocationRelativeTo(null); //to center the image
		add(board);
		setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GameFrame(); 
	}
}

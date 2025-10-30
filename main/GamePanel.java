package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints.Key;
import java.awt.desktop.ScreenSleepEvent;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;



public class GamePanel extends JPanel implements Runnable{
	
	// SCREEN SETTINGS
	final int originalTileSize = 16; // 16x16 tile
	final int scale = 6;
	
	public int tileSize = originalTileSize * scale; //48x48 tile
	public int maxScreenCol = 16;
	public int maxSreenRow = 12;
	public int screenWIdth = tileSize * maxScreenCol; // 768px
	public int screenHeight = tileSize * maxSreenRow; // 576px
	
	//WORLD SETTINGS 
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxSreenRow;
	
	//FPS
	int FPS  = 60;
	
	
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler(this);
	Thread gameThread;
	public CollisionChecker cChecker = new CollisionChecker(this);
	public Player player = new Player(this, keyH);
	
	public  GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWIdth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	public void zoomInOut(int i) {
		
		int oldWorldWidth = tileSize * maxWorldCol;
		tileSize += i;
		int newWorldWidth = tileSize * maxWorldCol;
		
		player.speed = (double)newWorldWidth/600;
		
		double multiplier = (double)newWorldWidth/oldWorldWidth;
		
		System.out.println(tileSize);
		System.out.println(newWorldWidth);
		System.out.println(player.worldX);
		System.out.println("speed" + player.speed);
		
		double newPlayerWorldX = player.worldX * multiplier;
		double newPlayerWorldY = player.worldY * multiplier;
		
		player.worldX = newPlayerWorldX;
		player.worldY = newPlayerWorldY;
	}
	
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
	}
	@Override
	
/*
	public void run() {
		
		double drawInterval = 1000000000 / FPS;
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		while (gameThread != null) {
			
			long currentTime = System.nanoTime();
			System.out.println("current time : " + currentTime);
			
			
			//1 UPDATE: met a jour les informations comme la position du joueur
			update();
			
			
			//2 DRAW: affiche l'écran mis a jour
			repaint();
			
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime / 1000000;
				
				if (remainingTime < 0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long) remainingTime);
				
				nextDrawTime += drawInterval;
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
*/
	
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		while ( gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if (delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			if (timer >= 1000000000){
				System.out.println("FPS:" + drawCount);
				drawCount = 0;
				timer = 0; 
			}
			
			
		}
	}
	
	public void update() {
		
		player.update();
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		tileM.draw(g2);
		
		player.draw(g2);
		
		g2.dispose();
	}
}

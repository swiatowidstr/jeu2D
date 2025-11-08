package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import object.OBJ_key;

public class UI {

	GamePanel gp;
	Graphics2D g2;
	
	//FONTS
	Font JBMono_30;
	Font JBMono_40;
	Font JBMono_80;
	
//	BufferedImage keyImage;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	public String currentDialogue = "";
	
	//BUTTON RECTANGLES FOR MOUSE CLICKING
	int buttonX, buttonY, buttonWidth, buttonHeight;
	int buttonSpacing;
	Rectangle resumeButton, settingsButton, quitButton;
	
	
	
//	double playTime;
//	DecimalFormat dFormat = new DecimalFormat("#0.00");

	public UI(GamePanel gp) {
		this.gp = gp;

		JBMono_30 = new Font("JetBrainMono", Font.PLAIN, 30);
		JBMono_40 = new Font("JetBrainMono", Font.PLAIN, 40);
		JBMono_80 = new Font("JetBrainMono", Font.PLAIN, 80);
//		OBJ_key key = new OBJ_key(gp);
//		keyImage = key.image;
	}

	public void showMessage(String text) {

		message = text;
		messageOn = true;
	}

	public void draw(Graphics2D g2) {

		this.g2 = g2;
		
		g2.setFont(JBMono_40);
		g2.setColor(Color.white);
		
		//PLAY STATE
		if (gp.gameState == gp.playState) {
			
		}
		//PAUSE STATE
		if (gp.gameState == gp.pauseState) {
			
			drawPauseScreen();
		}
		
		//DIALOGUE STATE
		if (gp.gameState == gp.dialogueState) {
			drawDialogueScreen();
		}
	}
	//DRAW PAUSE SCREEN
	public void drawPauseScreen() {
		
		//WINDOW
		int x = 0;
		int y = 0;
		int windowWidth = gp.screenWIdth;
		int windowHeight = gp.screenHeight;
		drawSubwindow(x, y, windowWidth, windowHeight);
		
		//TEXT
		String pause = "PAUSE";
		g2.setFont(JBMono_80);
		g2.setColor(Color.white);
		x = getXForcenterText(pause);
		y = gp.screenHeight / 6;
		g2.drawString(pause, x, y);
		
		//BUTTONS DIMENSIONS
		buttonWidth = 400;
		buttonHeight = 100;
		buttonX = gp.screenWIdth / 2  - buttonWidth / 2;
		buttonY = gp.screenHeight / 4;
		buttonSpacing = 120;
		
		//UPDATE RECTANGLES
		resumeButton = new Rectangle(buttonX, buttonY, buttonWidth, buttonHeight);
		settingsButton = new Rectangle(buttonX, buttonY + buttonSpacing, buttonWidth, buttonHeight);
		quitButton = new Rectangle(buttonX, buttonY + buttonSpacing * 2, buttonWidth, buttonHeight);
		
		//DRAW BUTTONS
		drawButton("Resume" , buttonX, buttonY, buttonWidth, buttonHeight);
		drawButton("Settings" , buttonX, buttonY + buttonSpacing, buttonWidth, buttonHeight);
		drawButton("Quit" , buttonX, buttonY + buttonSpacing * 2, buttonWidth, buttonHeight);
		
	}
	
	public void drawDialogueScreen() {
		
		//WINDOW
		int x = gp.tileSize*1;
		int y = gp.tileSize*1;
		int width = gp.screenWIdth - (gp.tileSize*7);
		int height = gp.tileSize*4;
		
		drawSubwindow(x, y, width, height);
		
		x += gp.tileSize;
		y += gp.tileSize;
		
		for (String line : currentDialogue.split("\n")) {
			g2.drawString(line, x, y);
			y += 40;
		}
		
	}
	
	public void drawSubwindow(int x, int y, int width, int height) {
		
		Color c = new Color(15, 0, 10, 180);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color(142, 120, 230 , 210);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(2));
		g2.drawRoundRect(x+10, y+10, width-20, height-20, 25, 25);
		
	}
	public void drawButton(String text, int x, int y, int width, int height) {
		
		//BUTTON BACKGROUND
		g2.setColor(Color.black);
		g2.fillRoundRect(x, y, width, height, 50, 50);
		
		//TEXT
		g2.setFont(JBMono_40);
		g2.setColor(Color.white);
	    int textX = x + width / 2 - g2.getFontMetrics().stringWidth(text) / 2;
	    int textY = y + height / 2 + g2.getFontMetrics().getAscent() / 2 - 4;
		g2.drawString(text, textX, textY);
		
	}
	
	public int getXForcenterText(String text) {
		
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWIdth/2 - length/2;
		return x;
	}
	
	public void handleMouseClick(int mouseX, int mouseY) {
		
		if (gp.gameState == gp.pauseState) {
			if (resumeButton.contains(mouseX, mouseY)) {
				gp.gameState = gp.playState;
			}
			if (settingsButton.contains(mouseX, mouseY)) {
				System.out.println("Settings clicked");
			}
			if (quitButton.contains(mouseX, mouseY)) {
				System.out.println("Bye !");
				System.exit(mouseY);
			}
		}
	}
	
}









//if (gameFinished == true) {
//
//	g2.setFont(JBMono_40);
//	g2.setColor(Color.white);
//
//	String text;
//	int textLength;
//	int x;
//	int y;
//
//	text = "You won the treasure !!";
//	textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//	x = gp.screenWIdth / 2 - textLength / 2;
//	y = gp.screenHeight / 2 - gp.tileSize * 2;
//	g2.drawString(text, x, y);
//
//	g2.setFont(JBMono_80);
//	g2.setColor(Color.yellow);
//	text = "Congratulations !!";
//	textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//	x = gp.screenWIdth / 2 - textLength / 2;
//	y = gp.screenHeight / 2 + gp.tileSize * 2;
//	g2.drawString(text, x, y);
//	gp.gameThread = null;
//
//} else {
//
//	g2.setFont(JBMono_40);
//	g2.setColor(Color.white);
////	g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
////	g2.drawString("x " + gp.player.hasKey, 150, 100);
////	g2.setFont(JBMono_30);
////	g2.drawString("FPS : " + gp.FPS, gp.tileSize * 27 / 2, gp.tileSize / 2);
//
//	// TIME
//	playTime += (double) 1 / gp.FPS;
////	g2.drawString("Time : " + dFormat.format(playTime) + " sec", gp.tileSize * 27 / 2, gp.tileSize);
//
//	if (messageOn) {
//
//		g2.setFont(JBMono_40);
//		g2.drawString(message, gp.tileSize * 7, gp.tileSize * 7);
//
//		messageCounter++;
//
//		if (messageCounter > 90) {
//			messageCounter = 0;
//			messageOn = false;
//		}
//
//	}
//}
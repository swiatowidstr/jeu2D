package entity;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity {

	KeyHandler keyH;

	public final int screenX;
	public final int screenY;
//	public int hasKey = 0;

	public Player(GamePanel gp, KeyHandler keyH) {
		
		super(gp);
		this.keyH = keyH;

		screenX = gp.screenWIdth / 2 - (gp.tileSize / 2);
		screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

		solidArea = new Rectangle();
		solidArea.x = 16;
		solidArea.y = 32;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 50;
		solidArea.height = 40;

		setDefaultValues();
		getPlayerImage();
	}

	public void setDefaultValues() {

		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		speed = 5;

		direction = "down";
	}

	public void getPlayerImage() {

		up1 = setup("/player/duck_up1");
		up2 = setup("/player/duck_up2");
		down1 = setup("/player/duck_down1");
		down2 = setup("/player/duck_down2");
		left1 = setup("/player/duck_left1");
		left2 = setup("/player/duck_left2");
		right1 = setup("/player/duck_right1");
		right2 = setup("/player/duck_right2");
	}
	
	
	public void update() {

		
		if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true
				|| keyH.rightPressed == true) {
			if (keyH.upPressed == true) {
				direction = "up";
	
			} else if (keyH.leftPressed == true) {
				direction = "left";
	
			} else if (keyH.downPressed == true) {
				direction = "down";
	
			} else if (keyH.rightPressed == true) {
				direction = "right";
	
			}

		// CHECK THE COLLISION
		collisionOn = false;
		gp.cChecker.checkTile(this);

		// CHECK OBJECT COLLISION
		int objectIndex = gp.cChecker.ckeckObject(this, true);
		pickUpObject(objectIndex);
		
		//CHECK NPC COLLISION
		int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
		interactNPC(npcIndex);
		
		// IF COLLISION IS FALSE, PLAYER CANT MOVE
		if (!collisionOn) {

			switch (direction) {
			case "up": {
				worldY -= speed;
				break;
			}
			case "down": {
				worldY += speed;
				break;
			}
			case "left": {
				worldX -= speed;
				break;
			}
			case "right": {
				worldX += speed;
				break;
			}
			}
		}

		spriteCounter++;
		if (spriteCounter > 10) {
			if (spriteNum == 1) {
				spriteNum = 2;
			} else if (spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
			}
		}

	}

	public void pickUpObject(int i) {

		if (i != 999) {
			
			
		}
	}
	
	public void interactNPC(int i) {
		
		if (i != 999) {
			
			if (gp.keyH.ePressed) {
				gp.gameState = gp.dialogueState;
				gp.npc[i].speak();
			}
		}
		gp.keyH.ePressed = false;
	}

	public void draw(Graphics2D g2) {

		BufferedImage image = null;

		switch (direction) {
		case "up": {
			if (spriteNum == 1) {
				image = up1;
			}
			if (spriteNum == 2) {
				image = up2;
			}
			break;
		}
		case "down": {
			if (spriteNum == 1) {
				image = down1;
			}
			if (spriteNum == 2) {
				image = down2;
			}
			break;
		}
		case "left": {
			if (spriteNum == 1) {
				image = left1;
			}
			if (spriteNum == 2) {
				image = left2;
			}
			break;
		}
		case "right": {
			if (spriteNum == 1) {
				image = right1;
			}
			if (spriteNum == 2) {
				image = right2;
			}
			break;
		}
		}
		g2.drawImage(image, screenX, screenY,  null);
		// SHOW HITBOX
		if (keyH.showHitbox == true) {
			g2.setColor(Color.red);
			g2.setStroke(new BasicStroke(4));
			g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width,
			solidArea.height);
		}

	}
}




















//public void pickUpObject(int i) {
//
//	if (i != 999) {
//
//		String objectName = gp.obj[i].name;
//
//		switch (objectName) {
//		case "Key": {
//			hasKey++;
//			gp.obj[i] = null;
//			gp.ui.showMessage("You got a key !");
//			break;
//		}
//		case "Door": {
//			if (hasKey > 0) {
//				gp.obj[i] = null;
//				hasKey--;
//				gp.ui.showMessage("You opened the door !");
//			} else {
//				gp.ui.showMessage("You dont have a Key looser ! HAHAHA");
//			}
//			break;
//		}
//		case "Boots": {
//			speed += 2;
//			gp.obj[i] = null;
//			gp.ui.showMessage("Speedy boy !");
//			break;
//		}
//		case "Chest": {
//			gp.ui.gameFinished = true;
//			gp.stopMusic();
//			break;
//		}
//		}
//	}
//}
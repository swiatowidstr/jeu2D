package entity;

import java.awt.Rectangle;
import java.util.Random;

import main.GamePanel;

public class NPC_Wizard extends Entity{

	public NPC_Wizard(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed  = 1;
		solidArea = new Rectangle(0, 0, 80, 80);
		
		getImage();
		setDialogue();
	}
	
	public void getImage() {

		up1 = setup("/npc/sorcerer1_up1");
		up2 = setup("/npc/sorcerer1_up2");
		down1 = setup("/npc/sorcerer1_down1");
		down2 = setup("/npc/sorcerer1_down2");
		left1 = setup("/npc/sorcerer1_left1");
		left2 = setup("/npc/sorcerer1_left2");
		right1 = setup("/npc/sorcerer1_right1");
		right2 = setup("/npc/sorcerer1_right2");
	}
	
	public void setDialogue() {
		
		dialogues[0] = "Hello, little man...";
		dialogues[1] = "So... what are you doing here ?";
		dialogues[2] = "You know what happened to pdeople here ?...";
		dialogues[3] = "follow me ";
	}
	
	public void setAction() {
		
		actionLockCounter++;
		
		if (actionLockCounter == 120) {
			
			Random random = new Random();
			int i = random.nextInt(100)+1; //pick up a number from 1 to 100
			
			if (i <= 25) {
				direction = "up";
			}
			if (i > 25 && i <= 50) {
				direction = "down";
			}
			if (i > 50 && i <= 75) {
				direction = "left";
			}
			if (i > 75 && i <= 100) {
				direction = "right";
			}
			
			actionLockCounter = 0;
		}

	}
	
	@Override
	public void speak() {
		
		if (dialogues[dialogueIndex] == null) {
			dialogueIndex = 0;
		}
		gp.ui.currentDialogue = dialogues[dialogueIndex];
		dialogueIndex++;
		
		switch (gp.player.direction) {
		case "up": {
			
			direction = "down";
			break;
		}
		case "down": {
			
			direction = "up";
			break;
		}
		case "left": {
			
			direction = "right";
			break;
		}
		case "right": {
			
			direction = "leftsq";
			break;
		}
		}
	}

}

package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import entity.NPC_Wizard;

public class KeyHandler implements KeyListener {

    // === COMMANDES CLAVIER ===
    private static final int UP_KEY = KeyEvent.VK_Z;
    private static final int DOWN_KEY = KeyEvent.VK_S;
    private static final int LEFT_KEY = KeyEvent.VK_Q;
    private static final int RIGHT_KEY = KeyEvent.VK_D;
    private static final int ACTION_KEY = KeyEvent.VK_E;
    private static final int ESC_KEY = KeyEvent.VK_ESCAPE;
    private static final int DEBUG_KEY = KeyEvent.VK_T;
    private static final int HITBOX_KEY = KeyEvent.VK_H;
    private static final int ENTER_KEY = KeyEvent.VK_ENTER;

    public boolean upPressed, downPressed, leftPressed, rightPressed, ePressed, enterPressed;
    GamePanel gp;
    // DEBUG
    boolean checkDrawTime = false;
    public int showHitbox = 0;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // PLAY STATE
        if (gp.gameState == gp.playState) {

            if (code == UP_KEY) upPressed = true;
            if (code == LEFT_KEY) leftPressed = true;
            if (code == DOWN_KEY) downPressed = true;
            if (code == RIGHT_KEY) rightPressed = true;
            if (code == ACTION_KEY) ePressed = true;
            if (code == ENTER_KEY) enterPressed = true;

            if (code == ESC_KEY) gp.gameState = gp.pauseState;
            if (code == DEBUG_KEY) checkDrawTime = !checkDrawTime;
            if (code == HITBOX_KEY) {
            	showHitbox++;
            	if (showHitbox > 2) {
            		showHitbox = 0;
            	}
            }
        }

        // PAUSE STATE
        else if (gp.gameState == gp.pauseState) {
            if (code == ESC_KEY) {
            	gp.gameState = gp.playState;
            }
        }
        // DIALOGUE STATE
        else if (gp.gameState == gp.dialogueState) {
            if (code == ESC_KEY) {
            	
            	gp.gameState = gp.playState;
            }
        }
        else if (gp.gameState == gp.dialogueState) {
        	if (code == ACTION_KEY) {
        		ePressed = true;
        	}
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == UP_KEY) upPressed = false;
        if (code == LEFT_KEY) leftPressed = false;
        if (code == DOWN_KEY) downPressed = false;
        if (code == RIGHT_KEY) rightPressed = false;
    }
}

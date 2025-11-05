package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

	public boolean upPressed, downPressed, leftPressed, rightPressed;
	GamePanel gp;
	// DEBUG
	boolean checkDrawTime = false;
	public boolean showHitbox = false;

	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

		int code = e.getKeyCode();

		if (code == KeyEvent.VK_Z) {
			upPressed = true;
		}
		if (code == KeyEvent.VK_Q) {
			leftPressed = true;
		}
		if (code == KeyEvent.VK_S) {
			downPressed = true;
		}
		if (code == KeyEvent.VK_D) {
			rightPressed = true;
		}

		// DEBUG
		if (code == KeyEvent.VK_T) {
			if (checkDrawTime == false) {
				checkDrawTime = true;
			} else if (checkDrawTime == true) {
				checkDrawTime = false;
			}
		}
		//SHOW/HIDE HITBOX
		if (code == KeyEvent.VK_H) {
			if (showHitbox == false) {
				showHitbox = true;
			}
			else if (showHitbox == true) {
				showHitbox = false;
			}
		}

	}


	@Override
	public void keyReleased(KeyEvent e) {

		int code = e.getKeyCode();

		if (code == KeyEvent.VK_Z) {
			upPressed = false;
		}
		if (code == KeyEvent.VK_Q) {
			leftPressed = false;
		}
		if (code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if (code == KeyEvent.VK_D) {
			rightPressed = false;
		}
	}

}

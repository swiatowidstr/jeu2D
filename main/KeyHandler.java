package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    // === COMMANDES CLAVIER ===
    private static final int UP_KEY = KeyEvent.VK_Z;
    private static final int DOWN_KEY = KeyEvent.VK_S;
    private static final int LEFT_KEY = KeyEvent.VK_Q;
    private static final int RIGHT_KEY = KeyEvent.VK_D;
    private static final int ACTION_KEY = KeyEvent.VK_E;
    private static final int PAUSE_KEY = KeyEvent.VK_ESCAPE;
    private static final int DEBUG_KEY = KeyEvent.VK_T;
    private static final int HITBOX_KEY = KeyEvent.VK_H;

    public boolean upPressed, downPressed, leftPressed, rightPressed, ePressed;
    GamePanel gp;
    // DEBUG
    boolean checkDrawTime = false;
    public boolean showHitbox = false;

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

            if (code == PAUSE_KEY) gp.gameState = gp.pauseState;
            if (code == DEBUG_KEY) checkDrawTime = !checkDrawTime;
            if (code == HITBOX_KEY) showHitbox = !showHitbox;
        }

        // PAUSE STATE
        else if (gp.gameState == gp.pauseState) {
            if (code == PAUSE_KEY) gp.gameState = gp.playState;
        }

        // DIALOGUE STATE
        else if (gp.gameState == gp.dialogueState) {
            if (code == ACTION_KEY) gp.gameState = gp.playState;
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

package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import object.OBJ_key;

public class UI {

	GamePanel gp;
	Font JBMono_30;
	Font JBMono_40;
	Font JBMono_80;
	BufferedImage keyImage;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;

	double playTime;
	int min;
	DecimalFormat dFormat = new DecimalFormat("#0.00");

	public UI(GamePanel gp) {
		this.gp = gp;

		JBMono_30 = new Font("JetBrainMono", Font.PLAIN, 30);
		JBMono_40 = new Font("JetBrainMono", Font.PLAIN, 40);
		JBMono_80 = new Font("JetBrainMono", Font.PLAIN, 80);
		OBJ_key key = new OBJ_key(gp);
		keyImage = key.image;
	}

	public void showMessage(String text) {

		message = text;
		messageOn = true;
	}

	public void draw(Graphics2D g2) {

		if (gameFinished == true) {

			g2.setFont(JBMono_40);
			g2.setColor(Color.white);

			String text;
			int textLength;
			int x;
			int y;

			text = "You won the treasure !!";
			textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenWIdth / 2 - textLength / 2;
			y = gp.screenHeight / 2 - gp.tileSize * 2;
			g2.drawString(text, x, y);

			g2.setFont(JBMono_80);
			g2.setColor(Color.yellow);
			text = "Congratulations !!";
			textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenWIdth / 2 - textLength / 2;
			y = gp.screenHeight / 2 + gp.tileSize * 2;
			g2.drawString(text, x, y);
			gp.gameThread = null;

		} else {

			g2.setFont(JBMono_40);
			g2.setColor(Color.white);
			g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
			g2.drawString("x " + gp.player.hasKey, 150, 100);
			g2.setFont(JBMono_30);
			g2.drawString("FPS : " + gp.FPS, gp.tileSize * 27 / 2, gp.tileSize / 2);

			// TIME
			playTime += (double) 1 / gp.FPS;
			g2.drawString("Time : " + dFormat.format(playTime) + " sec", gp.tileSize * 27 / 2, gp.tileSize);

			if (messageOn == true) {

				g2.setFont(JBMono_40);
				g2.drawString(message, gp.tileSize * 7, gp.tileSize * 7);

				messageCounter++;

				if (messageCounter > 90) {
					messageCounter = 0;
					messageOn = false;
				}

			}
		}
	}
}

package tile;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {

	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	private String defaultTexture = "grass00";

	public TileManager(GamePanel gp) {

		this.gp = gp;

		tile = new Tile[99];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

		getTileImage();
		loadMap("/maps/worldV2.txt");
	}

	public void getTileImage() {
		
		// PLACEHOLDER
		for (int i = 0; i < 11; i++) {
			setup(i, defaultTexture, false);
		}
		//PLACEHOLDER
		
		
		setup(10, "grass00", false);
		setup(11, "grass00", false);
		setup(12, "water00", true);
		setup(13, "water01", true);
		setup(14, "water_grass_TL", true);
		setup(15, "water_grass_TM", true);
		setup(16, "water_grass_TR", true);
		setup(17, "water_grass_LM", true);
		setup(18, "water_grass_RM", true);
		setup(19, "water_grass_BL", true);
		setup(20, "water_grass_BM", true);
		setup(21, "water_grass_BR", true);
		setup(22, "water_grass_ile_TL", true);
		setup(23, "water_grass_ile_TR", true);
		setup(24, "water_grass_ile_BL", true);
		setup(25, "water_grass_ile_BR", true);
		setup(26, "sand00", false);
		setup(27, "sand_grass_TL", false);
		setup(28, "sand_grass_TM", false);
		setup(29, "sand_grass_TR", false);
		setup(30, "sand_grass_LM", false);
		setup(31, "sand_grass_RM", false);
		setup(32, "sand_grass_BL", false);
		setup(33, "sand_grass_BM", false);
		setup(34, "sand_grass_BR", false);
		setup(35, "sand_grass_ile_TL", false);
		setup(36, "sand_grass_ile_TR", false);
		setup(37, "sand_grass_ile_BL", false);
		setup(38, "sand_grass_ile_BR", false);
		setup(39, "dirt00", false);
		setup(40, "wall00", true);
		setup(41, "tree00", true);
		//ponton
		setup(42, "ponton00", false);
		setup(43, "ponton01", true);
		setup(44, "ponton02", true);
		setup(45, "ponton03", true);
		setup(46, "ponton04", true);
		setup(47, "ponton05", true);
		setup(48, "ponton06", true);
		setup(49, "ponton07", true);
		setup(50, "ponton08", true);
		setup(51, "ponton00", false);
		setup(52, "ponton10", true);
		setup(53, "ponton11", true);
		setup(54, "ponton12", true);
		setup(55, "ponton13", true);
		setup(56, "ponton_herbe00", true);
		setup(57, "ponton_herbe02", true);
		setup(58, "ponton_herbe08", true);
		setup(59, "ponton_liaison00", true);
		setup(60, "ponton_liaison01", true);
		setup(61, "ponton_liaison02", true);
		setup(62, "ponton_liaison03", true);
		setup(63, "ponton_liaison04", false);
		setup(64, "ponton_liaison05", false);

		
	}
	
	public void setup(int index, String imageName, boolean collision) {
		
		UtilityTool uTool = new UtilityTool();
		
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap(String filePath) {

		try {

			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			int col = 0;
			int row = 0;

			while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

				String line = br.readLine();

				while (col < gp.maxWorldCol) {

					String numbers[] = line.split(" ");

					int num = Integer.parseInt(numbers[col]);

					mapTileNum[col][row] = num;
					col++;
				}
				if (col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();

		} catch (Exception e) {

		}

	}

	public void draw(Graphics2D g2) {

		int worldCol = 0;
		int worldRow = 0;

		while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

			int tileNum = mapTileNum[worldCol][worldRow];

			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			double screenX = worldX - gp.player.worldX + gp.player.screenX;
			double screenY = worldY - gp.player.worldY + gp.player.screenY;

			if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
					&& worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
					&& worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
					&& worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

				g2.drawImage(tile[tileNum].image, (int) screenX, (int) screenY, null);
				
				//SHOW TILES HITBOX
				if (gp.keyH.showHitbox == 2 && tile[tileNum].collision) {
				    g2.setColor(Color.red);
				    g2.setStroke(new BasicStroke(2));
				    g2.drawRect((int) screenX, (int) screenY, gp.tileSize, gp.tileSize);
				}
			}

			worldCol++;

			if (worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
	}

}

package object;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_key extends SuperObject {

	GamePanel gp;	
	
	public OBJ_key(GamePanel gp) {

		name = "Key";
		try {
			image = ImageIO.read(getClass().getResource("/objects/key.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

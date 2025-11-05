package object;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_door extends SuperObject{

	GamePanel gp;	
	
	public OBJ_door(GamePanel gp) {
		
		name = "Door";
		try {
			image = ImageIO.read(getClass().getResource("/objects/porte_1_1.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		collision = true;
	}
}

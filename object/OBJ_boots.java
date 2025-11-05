package object;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_boots extends SuperObject{
	
	GamePanel gp;
	
	public OBJ_boots(GamePanel gp) {
		
		name = "Boots";
		try {
			image = ImageIO.read(getClass().getResource("/objects/boots1.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

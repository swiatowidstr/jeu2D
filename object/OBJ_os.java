package object;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_os extends SuperObject{
	
	GamePanel gp;	
	
	public OBJ_os(GamePanel gp) {
		
		name = "Os";
		try {
			image = ImageIO.read(getClass().getResource("/objects/os_1.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

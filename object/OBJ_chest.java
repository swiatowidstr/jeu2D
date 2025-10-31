package object;

import javax.imageio.ImageIO;

public class OBJ_chest extends SuperObject{

	public OBJ_chest() {
		
		name = "Chest";
		try {
			image = ImageIO.read(getClass().getResource("/objects/coffre_1.png"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

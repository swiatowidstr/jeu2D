package object;

import javax.imageio.ImageIO;

public class OBJ_door extends SuperObject{

	
	public OBJ_door() {
		
		name = "Door";
		try {
			image = ImageIO.read(getClass().getResource("/objects/porte_1_1.png"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

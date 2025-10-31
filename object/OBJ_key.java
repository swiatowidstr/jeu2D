package object;

import javax.imageio.ImageIO;

public class OBJ_key extends SuperObject{
	
	public OBJ_key() {
		
		name = "Key";
		try {
			image = ImageIO.read(getClass().getResource("/objects/cle_porte_1.png"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

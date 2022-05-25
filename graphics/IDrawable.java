package graphics;

import java.awt.Graphics;

public interface IDrawable {
	
	public final static String PICTURE_PATH ="C:\\Users\\Administrator\\Desktop\\Java Projects\\Zoo 2\\assignment2_pictures\\";	
	public void loadImages(String nm);
	public void drawObject (Graphics g);
	public String getColor();


}

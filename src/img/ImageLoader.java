package img;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;

public class ImageLoader {
	
	//singleton
	private static ImageLoader instance;
	public static ImageLoader getInstance()
	{
		if(instance == null)
		{
			instance = new ImageLoader();
		}
		return instance;
	}
	
	private Map<String, Image> imageMap = new HashMap<String, Image>(100);
	public Image getImage(String filepath)
	{
		if(!imageMap.containsKey(filepath))
			imageMap.put(filepath, new Image(ClassLoader.getSystemResource(filepath).toString()));
		return imageMap.get(filepath);
	}
}

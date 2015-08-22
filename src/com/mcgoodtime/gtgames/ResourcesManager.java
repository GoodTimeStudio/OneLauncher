package com.mcgoodtime.gtgames;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ResourcesManager {

	public final static String TEXTURE = "texture/";
	public final static String BUTTON = "button/";

	public static Image btn_Close;
	public static Image btn_Close1;
	public static Image btn_Cancel;
	public static Image btn_Next;
	public static Image btn_Save;
	public static Image btn_Setting;

	public static Image background;
	public static Image loading;
	public static Image lock;
	public static Image mainBackground;
	public static Image steve;
	public static Image user;

	private static URL getTexture(String textureName) {
		return ResourcesManager.class.getResource("/" + TEXTURE + textureName);
	}

	private static InputStream getTextureAsStream(String textureName) {
		return ResourcesManager.class.getResourceAsStream("/" + TEXTURE + textureName);
	}

	public static void loadTexture() {
		try {
			btn_Close = ImageIO.read(getTexture(BUTTON + "close.png"));
			btn_Close1 = ImageIO.read(getTexture(BUTTON + "close1.png"));
			btn_Cancel = ImageIO.read(getTexture(BUTTON + "cancel.png"));
			btn_Next = ImageIO.read(getTexture(BUTTON + "next.png"));
			btn_Save = ImageIO.read(getTexture(BUTTON + "save.png"));
			btn_Setting = ImageIO.read(getTexture(BUTTON + "setting.png"));

			background = ImageIO.read(getTexture("background.png"));
			loading = ImageIO.read(getTexture("loading.gif"));
			lock = ImageIO.read(getTexture("lock.png"));
			mainBackground = ImageIO.read(getTexture("mainBackground.png"));
			steve = ImageIO.read(getTexture("steve.png"));
			user = ImageIO.read(getTexture("user.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Image getImageFormURL(String url) {
		try {
			URL url2 = url2 = new URL(url);
			HttpURLConnection connection2 = connection2 = (HttpURLConnection) url2.openConnection();
			connection2.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.1; Windows NT; DigExt)");
			return ImageIO.read(connection2.getInputStream());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}

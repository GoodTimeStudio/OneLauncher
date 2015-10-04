package com.mcgoodtime.mgl;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ResourcesManager implements Runnable {

	public final static String TEXTURE = "texture/";
	public final static String SOUND = "sound/";
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

	@Override
	public void run() {
		try {
			btn_Close = ImageIO.read(getTextureURL(BUTTON + "close.png"));
			btn_Close1 = ImageIO.read(getTextureURL(BUTTON + "close1.png"));
			btn_Cancel = ImageIO.read(getTextureURL(BUTTON + "cancel.png"));
			btn_Next = ImageIO.read(getTextureURL(BUTTON + "next.png"));
			btn_Save = ImageIO.read(getTextureURL(BUTTON + "save.png"));
			btn_Setting = ImageIO.read(getTextureURL(BUTTON + "setting.png"));

			background = ImageIO.read(getTextureURL("background.png"));
			loading = ImageIO.read(getTextureURL("loading.gif"));
			lock = ImageIO.read(getTextureURL("lock.png"));
			mainBackground = ImageIO.read(getTextureURL("mainBackground.png"));
			steve = ImageIO.read(getTextureURL("steve.png"));
			user = ImageIO.read(getTextureURL("user.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static InputStream getMusicSteam(String name) {
		return ResourcesManager.class.getResourceAsStream("/" + SOUND + name);
	}

	public static Image getImageFormURL(String url) throws IOException {
		URL url2 = new URL(url);
		HttpURLConnection connection2 = (HttpURLConnection) url2.openConnection();
		connection2.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)");
		return ImageIO.read(connection2.getInputStream());
	}

	private static URL getTextureURL(String textureName) {
		return ResourcesManager.class.getResource("/" + TEXTURE + textureName);
	}

	@Deprecated
	private static InputStream getTextureStream(String textureName) {
		return ResourcesManager.class.getResourceAsStream("/" + TEXTURE + textureName);
	}
}

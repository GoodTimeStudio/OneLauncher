package com.mcgoodtime.gtgames.client;

import java.io.InputStream;
import java.net.URL;

public class ResourcesManager {

	public final static String TEXTURE = "texture/";
	public final static String BUTTON = "button/";

	public final static String BTN_CLOSE = BUTTON + "close.png";
	public final static String BTN_CLOSE1 = BUTTON + "close1.png";
	public final static String BTN_CANCEL = BUTTON + "cancel.png";
	public final static String BTN_NEXT = BUTTON + "next.png";
	public final static String BTN_SAVE = BUTTON + "save.png";
	public final static String BTN_SETTING = BUTTON + "setting.png";

	public static URL getTexture(String textureName) {
		return ResourcesManager.class.getResource("/" + TEXTURE + textureName);
	}

	public static InputStream getTextureAsStream(String textureName) {
		return ResourcesManager.class.getResourceAsStream("/" + TEXTURE + textureName);
	}
}

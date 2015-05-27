package com.mcgoodtime.gtgames.resources;

import java.io.InputStream;
import java.net.URL;

public class ResourcesManager {

	public final static String TEXTURE = "texture/";

	public static URL getTexture(String textureName) {
		return ResourcesManager.class.getResource(TEXTURE + textureName);
	}

	public static InputStream getTextureAsStream(String textureName) {
		return ResourcesManager.class.getResourceAsStream(TEXTURE + textureName);
	}
}

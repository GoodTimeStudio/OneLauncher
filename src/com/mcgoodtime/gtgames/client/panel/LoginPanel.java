package com.mcgoodtime.gtgames.client.panel;

import com.mcgoodtime.gtgames.client.gui.MainFrame;
import com.mcgoodtime.gtgames.resources.ResourcesManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class LoginPanel extends JPanel {

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		/* draw image */
		InputStream inBackground = ResourcesManager.getTextureAsStream("background.png");
		BufferedImage imageBackground = null;
		try {
			imageBackground = ImageIO.read(inBackground);
		} catch (IOException e) {
			e.printStackTrace();
		}
		g2d.drawImage(imageBackground, 0, 0, getWidth(), getHeight(), null);

		InputStream inUser = ResourcesManager.getTextureAsStream("user.png");
		BufferedImage imageUser = null;
		try {
			imageUser = ImageIO.read(inUser);
		} catch (IOException e) {
			e.printStackTrace();
		}
		g2d.drawImage(imageUser, MainFrame.containerLogin.getWidth() / 2 - 150, MainFrame.containerLogin.getHeight() / 2 - 90, null);

		InputStream inPassWord = ResourcesManager.getTextureAsStream("Lock.png");
		BufferedImage imagePassword = null;
		try {
			imagePassword = ImageIO.read(inPassWord);
		} catch (IOException e) {
			e.printStackTrace();
		}
		g2d.drawImage(imagePassword, MainFrame.containerLogin.getWidth() / 2 - 150, MainFrame.containerLogin.getHeight() / 2 - 50, null);


		/* draw String */
		g2d.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		g2d.drawString("登陆", 50, 50);
		
		g2d.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		g2d.drawString("您需要登陆后才能进行游戏", 50, 70);
	}

	@Override
	protected void paintChildren(Graphics g) {
		super.paintChildren(g);
	}

	@Override
	protected void paintBorder(Graphics g) {
		super.paintBorder(g);
	}
	
	

}

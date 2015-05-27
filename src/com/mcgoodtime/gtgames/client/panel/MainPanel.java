package com.mcgoodtime.gtgames.client.panel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.image.ImageObserver;

import javax.swing.JPanel;

public class MainPanel extends JPanel implements ImageObserver {

	@Override
	public void print(Graphics arg0) {
		super.print(arg0);
	}

	@Override
	protected void paintBorder(Graphics arg0) {
		Graphics2D g2d = (Graphics2D) arg0;
		g2d.setColor(Color.black);
		Stroke s = new BasicStroke(3);
		g2d.setStroke(s);
		g2d.drawRect(0, 0, getWidth()-1, getHeight()-1);
	}

	@Override
	protected void paintChildren(Graphics arg0) {
		super.paintChildren(arg0);
	}

	@Override
	protected void paintComponent(Graphics arg0) {
		Graphics2D g2d = (Graphics2D) arg0;//Graphics2D
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2d.setColor(Color.black);//draw bar
		g2d.setClip(0, 0, getWidth(), 30);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		g2d.setClip(null);
		
		g2d.setColor(Color.WHITE);//set color
		g2d.setFont(new Font("微软雅黑", Font.PLAIN, 15));//set font
		g2d.drawString("GoodTime游戏平台", 20, 20);//draw title


	}
	
}

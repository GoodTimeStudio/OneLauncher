package com.mcgoodtime.gtgames.client.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MainPanel extends JPanel implements ImageObserver {

	@Override
	public void print(Graphics arg0) {
		super.print(arg0);
	}

	@Override
	protected void paintBorder(Graphics arg0) {
		super.paintBorder(arg0);
	}

	@Override
	protected void paintChildren(Graphics arg0) {
		super.paintChildren(arg0);
	}

	@Override
	protected void paintComponent(Graphics arg0) {
		Graphics2D g2d = (Graphics2D) arg0;//ǿ�ư�Graphicsת��
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2d.setColor(new Color(0, 0, 0));//������ɫ
		g2d.setClip(0, 0, getWidth(), 30);
		g2d.fillRect(0, 0, getWidth(), getHeight());//���
		g2d.setClip(null);
		
		g2d.setColor(Color.WHITE);//������ɫ
		g2d.setFont(new Font("΢���ź�", Font.PLAIN, 15));//��������
		g2d.drawString("GoodTime��Ϸƽ̨", 20, 20);//�����ַ���


	}
	
}

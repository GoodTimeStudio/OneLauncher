package com.mcgoodtime.gtgames.client.panel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by suhao on 2015-5-28-0028.
 */
public class SettingPanel extends JPanel {
    @Override
    protected void paintBorder(Graphics arg0) {
        Graphics2D g2d = (Graphics2D) arg0;
        g2d.setColor(Color.black);
        Stroke s = new BasicStroke(3);
        g2d.setStroke(s);
        g2d.drawRect(0, 0, getWidth()-1, getHeight()-1);
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
        g2d.drawString("GoodTime游戏平台设置", 20, 20);//draw title


    }
}

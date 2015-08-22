package com.mcgoodtime.gtgames.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;

/**
 * Created by suhao on 2015.8.22.0022.
 *
 * @author suhao
 */
public class ServerTile extends JPanel {

    private String name;
    private Image image;

    public ServerTile(String name, Image image) {
        super();
        this.name = name;
        this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(this.image, 0, 0, this.getWidth() - 1, this.getHeight() - 1, null);

        g2d.setColor(new Color(0, 0, 0, 100));
        g2d.fillRect(0, this.getHeight() - 40, this.getWidth(), 40);

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        int x = (this.getWidth() - g2d.getFontMetrics().stringWidth(this.name)) / 2;
        g2d.drawString(this.name, x, this.getHeight() - 15);
    }

    @Override
    protected void paintChildren(Graphics g) {}

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.drawRect(-1, -1, getWidth() + 1, getHeight() + 1);
    }
}

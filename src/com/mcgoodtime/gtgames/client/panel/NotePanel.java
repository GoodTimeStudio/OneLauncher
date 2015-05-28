package com.mcgoodtime.gtgames.client.panel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by suhao on 2015-5-28-0028.
 */
public class NotePanel extends JEditorPane {
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(new Color(255, 255, 255, 150));
        g2d.fillRect(1, 1, getWidth() - 2, getHeight() - 2);

        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.cyan);
        g2d.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
    }

}

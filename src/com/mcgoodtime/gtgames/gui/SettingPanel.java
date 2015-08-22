package com.mcgoodtime.gtgames.gui;

import com.mcgoodtime.gtgames.ResourcesManager;
import sun.applet.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by suhao on 2015.8.22.0022.
 *
 * @author suhao
 */
public class SettingPanel {

    public SettingPanel() {
        final JPanel containerSetting = new JPanel() {
            @Override
            protected void paintComponent(Graphics arg0) {
                super.paintComponent(arg0);
                Graphics2D g2d = (Graphics2D) arg0;//Graphics2D
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                	/* draw String */
                g2d.setFont(new Font("微软雅黑", Font.PLAIN, 30));
                g2d.drawString("设置", 50, 50);

                g2d.setFont(new Font("微软雅黑", Font.PLAIN, 15));
                g2d.drawString("更改启动设置和平台设置（高级功能，如非必要，请勿修改）", 50, 70);
            }
        };
        containerSetting.setBounds(2, 30, 696, 418);
        containerSetting.setLayout(null);
        MainWindow.mainPanel.add(containerSetting);

			/* button */
        //save option
        ImageIcon iconSave = new ImageIcon(ResourcesManager.btn_Save);
        JLabel labSave = new JLabel(iconSave);
        labSave.setBounds(containerSetting.getWidth() - 60, containerSetting.getHeight() - 60, 50, 50);
        labSave.setToolTipText("保存设置");

        //cancel button
        ImageIcon iconCancel = new ImageIcon(ResourcesManager.btn_Cancel);
        JLabel labCancel = new JLabel(iconCancel);
        labCancel.setBounds(containerSetting.getWidth() - 120, containerSetting.getHeight() - 60, 50, 50);
        labCancel.setToolTipText("取消并返回");
        labCancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                containerSetting.setVisible(false);
                MainPanel.container.setVisible(true);
                MainWindow.mainPanel.remove(containerSetting);
            }
        });

        //add item to container Setting
        containerSetting.add(labSave);
        containerSetting.add(labCancel);
    }
}

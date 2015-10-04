package com.mcgoodtime.mgl.gui;

import com.mcgoodtime.mgl.ResourcesManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by suhao on 2015.8.22.0022.
 *
 * @author suhao
 */
public class SettingPanel extends JPanel {

    private JPanel settingPanel = this;

    private JLabel labelMemory = new JLabel();
    private JLabel labelJavaPath = new JLabel();
    private JLabel labelJavaArgs = new JLabel();

    public SettingPanel() {
        this.setBounds(2, 30, 696, 418);
        this.setLayout(null);
        MainWindow.mainPanel.add(this);

        /* button */
        //save option
        ImageIcon iconSave = new ImageIcon(ResourcesManager.btn_Save);
        JLabel labSave = new JLabel(iconSave);
        labSave.setBounds(this.getWidth() - 60, this.getHeight() - 60, 50, 50);
        labSave.setToolTipText("保存设置");

        //cancel button
        ImageIcon iconCancel = new ImageIcon(ResourcesManager.btn_Cancel);
        JLabel labCancel = new JLabel(iconCancel);
        labCancel.setBounds(this.getWidth() - 120, this.getHeight() - 60, 50, 50);
        labCancel.setToolTipText("取消并返回");
        labCancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                settingPanel.setVisible(false);
                MainPanel.container.setVisible(true);
                MainWindow.mainPanel.remove(settingPanel);
            }
        });

        labelMemory.setBounds(10, 70, 135, 20);
        labelMemory.setText("最大内存");
        labelMemory.setFont(new Font("微软雅黑", Font.PLAIN, 12));

        labelJavaPath.setBounds(10, 160, 135, 20);
        labelJavaPath.setText("Java路径");
        labelJavaPath.setFont(new Font("微软雅黑", Font.PLAIN, 12));

        labelJavaArgs.setBounds(10, 190, 135, 20);
        labelJavaArgs.setText("Java额外参数");
        labelJavaArgs.setFont(new Font("微软雅黑", Font.PLAIN, 12));

        //add item to container Setting
        this.add(labSave);
        this.add(labCancel);
        this.add(labelMemory);
        this.add(labelJavaPath);
        this.add(labelJavaArgs);
    }

    @Override
    protected void printComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;//Graphics2D
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        /* draw String */
        g2d.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        g2d.drawString("设置", 50, 50);

        g2d.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        g2d.drawString("更改启动设置和平台设置（高级功能，如非必要，请勿修改）", 50, 70);
    }
}

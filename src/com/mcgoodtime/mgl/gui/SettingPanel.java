package com.mcgoodtime.mgl.gui;

import com.mcgoodtime.mgl.ResourcesManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by suhao on 2015.8.22.0022.
 *
 * @author suhao
 */
public class SettingPanel {

    protected static JPanel containerSetting;

    public SettingPanel() {
        containerSetting = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;//Graphics2D
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

        JLabel labMemory = new JLabel();
        labMemory.setBounds(10, 100, 135, 20);
        labMemory.setText("最大内存(MB)");
        labMemory.setFont(new Font("微软雅黑", Font.PLAIN, 12));

        JLabel labJavaPath = new JLabel();
        labJavaPath.setBounds(10, 130, 135, 20);
        labJavaPath.setText("Java路径");
        labJavaPath.setFont(new Font("微软雅黑", Font.PLAIN, 12));

        JLabel labJavaArgs = new JLabel();
        labJavaArgs.setBounds(10, 160, 135, 20);
        labJavaArgs.setText("JVM额外参数");
        labJavaArgs.setFont(new Font("微软雅黑", Font.PLAIN, 12));

        JTextField textMemory = new JTextField();
        //限制只能输入数字
        textMemory.addKeyListener(new KeyAdapter(){
            public void keyTyped(KeyEvent e) {
                int keyChar = e.getKeyChar();
                if(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){

                }else{
                    e.consume(); //关键，屏蔽掉非法输入
                }
            }
        });
        textMemory.setBounds(135, 100, 135, 20);
        textMemory.setFont(new Font("微软雅黑", Font.PLAIN, 12));

        JTextField textJavaPath = new JTextField();
        textJavaPath.setBounds(135, 130, 235, 20);
        textJavaPath.setFont(new Font("微软雅黑", Font.PLAIN, 12));

        JTextField textJavaArgs = new JTextField();
        textJavaArgs.setBounds(135, 160, 235, 20);
        textJavaArgs.setFont(new Font("微软雅黑", Font.PLAIN, 12));

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
        containerSetting.add(labMemory);
        containerSetting.add(labJavaPath);
        containerSetting.add(labJavaArgs);
        containerSetting.add(textMemory);
        containerSetting.add(textJavaPath);
        containerSetting.add(textJavaArgs);
    }
}
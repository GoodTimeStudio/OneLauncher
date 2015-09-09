package com.mcgoodtime.gtgames.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by suhao on 2015.9.5.0005.
 *
 * @author suhao
 */
public class DownloadPanel {

    public DownloadPanel() {
        JPanel containerDownload = new JPanel() {
            @Override
            protected void paintComponent(Graphics arg0) {
                super.paintComponent(arg0);
                Graphics2D g2d = (Graphics2D) arg0;//Graphics2D
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                	/* draw String */
                g2d.setFont(new Font("微软雅黑", Font.PLAIN, 30));
                g2d.drawString("MechGear正在更新...", 50, 50);

                g2d.setFont(new Font("微软雅黑", Font.PLAIN, 15));
                g2d.drawString("正在下载必要组件，这不需要很长时间。", 50, 70);
            }
        };
        containerDownload.setBounds(2, 30, 696, 418);
        containerDownload.setLayout(null);

        JProgressBar progressBar = new JProgressBar();
        progressBar.setBounds(30, containerDownload.getHeight() - 60, containerDownload.getWidth() - 60, 30);

        JLabel progressInfo = new JLabel();
        progressInfo.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        progressInfo.setBounds(((containerDownload.getWidth() - 20) / 2) - 20, containerDownload.getHeight() - 60, 30 + 5, 20);

        containerDownload.add(progressInfo);
        containerDownload.add(progressBar);
        MainWindow.mainPanel.add(containerDownload);
    }
}

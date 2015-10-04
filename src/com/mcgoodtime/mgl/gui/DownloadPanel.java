package com.mcgoodtime.mgl.gui;

import com.mcgoodtime.mgl.network.Download;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by suhao on 2015.9.5.0005.
 *
 * @author suhao
 */
public class DownloadPanel {

    public JLabel progressInfo;
    public JProgressBar progressBar;

    private String fileName;
    private long fileLength = 0;

    private int value;

    private Download downloader = null;
    private Download.OSSDownload ossDownloader = null;

    public DownloadPanel(String file) {
        ossDownloader = new Download.OSSDownload(file);
        this.fileName = file;
        this.fileLength = ossDownloader.getFileLength();
        System.out.println("FileLength: " + fileLength);
        initGui();
    }

    public DownloadPanel(String downloadURL, String savePath) throws IOException {
        downloader = new Download(downloadURL);
        fileLength = downloader.getFileLength();
        this.fileName = downloadURL.substring(downloadURL.lastIndexOf("/") + 1);
        System.out.println("FileLength: " + fileLength);
        initGui();
    }

    public void initGui() {
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

                g2d.drawString("文件名: " + fileName, 50, 120);
                g2d.drawString("文件大小: " + (float) fileLength / 1024 / 1024 + " MB", 50, 150);
            }
        };
        containerDownload.setBounds(2, 30, 696, 418);
        containerDownload.setLayout(null);

        progressBar = new JProgressBar();
        progressBar.setBounds(30, containerDownload.getHeight() - 60, containerDownload.getWidth() - 60, 30);
        progressBar.setMaximum(1000);

        progressInfo = new JLabel();
        progressInfo.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        progressInfo.setBounds(((containerDownload.getWidth() - 20) / 2) - 20, containerDownload.getHeight() - 60, 30 + 5, 20);

        containerDownload.add(progressInfo);
        containerDownload.add(progressBar);
        MainWindow.mainPanel.add(containerDownload);
    }

    public void downloadFile(String savePath) {
        new Thread() {
            @Override
            public void run() {
                try {
                    downloader.downloadFile(savePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        System.out.println(downloader.getFileName());
        updateProgress();
    }

    public void downloadFileFormOSS(String savePath) {
        new Thread() {
            @Override
            public void run() {
                try {
                    ossDownloader.downloadFormOSS(savePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        updateProgress();
    }

    private void updateProgress() {
        File file = new File(downloader.getFileName());
        Timer timer = new Timer(300, e -> {
            long localSize =  file.length();
            value = (int) ((Download.getPercentageFormLong(localSize, fileLength, 1)) * 10);
            System.out.println("Progress: " + (value / 10) + "%");
            progressInfo.setText((value / 10) + "%");
            progressBar.setValue(value);
        });
        timer.addActionListener(e -> {
            if (value == 1000) {
                timer.stop();
            }
        });
        timer.start();
    }
}

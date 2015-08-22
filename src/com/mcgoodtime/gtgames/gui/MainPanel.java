package com.mcgoodtime.gtgames.gui;

import com.mcgoodtime.gjmlc.core.Launcher;
import com.mcgoodtime.gjmlc.core.LibrariesManager;
import com.mcgoodtime.gtgames.ResourcesManager;
import com.mcgoodtime.gtgames.core.Auth;
import com.mcgoodtime.gtgames.core.GT_Games;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by suhao on 2015.8.22.0022.
 *
 * @author suhao
 */
public class MainPanel {

    protected static JPanel container;

    public MainPanel() {

        container = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.drawImage(ResourcesManager.mainBackground, 0, 0, container.getWidth(), container.getHeight(), null);
            }
        };
        container.setBounds(2, 30, 696, 418);
        container.setLayout(null);
        container.setVisible(true);
        MainWindow.mainPanel.add(container);

        /* Info Panel */
        JPanel infoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                g2d.setColor(new Color(64, 64, 64, 150));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        infoPanel.setBounds(0, container.getHeight() - 80, container.getWidth(), 80);
        infoPanel.setOpaque(false);
        infoPanel.setLayout(null);

        /* label */

        //user photo
        JLabel labUserPhoto = new JLabel();
        labUserPhoto.setBounds(0, 0, 80, 80);
        //change icon size.
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Image image = ResourcesManager.getImageFormURL("http://mcuuid.net/face/{player}.png".replace("{player}"
                        , Auth.getUsername())).getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                labUserPhoto.setIcon(new ImageIcon(image));
            }
        });

        //user info
        JLabel labUserInfo = new JLabel();
        labUserInfo.setText(GT_Games.getHelloWord() + "," + "尊敬的" + Auth.getUsername());
        labUserInfo.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        labUserInfo.setBounds(90, 0, 150, 30);

        /* button */
        //launch button
        ImageIcon iconLaunch = new ImageIcon(ResourcesManager.btn_Next);
        JLabel labLaunch = new JLabel(iconLaunch);
        labLaunch.setToolTipText("启动客户端");
        labLaunch.setBounds(infoPanel.getWidth() - 60, infoPanel.getHeight() - 65, 50, 50);
        labLaunch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LibrariesManager.check("1.7.10-Forge10.13.4.1448-1.7.10");
                LibrariesManager.check("1.7.10-Forge10.13.4.1448-1.7.10");
                Launcher.launch("1.7.10-Forge10.13.4.1448-1.7.10", "BestOwl", 1024, null);
            }
        });

        //setting button
        ImageIcon iconSetting = new ImageIcon(ResourcesManager.btn_Setting);
        JLabel labSetting = new JLabel(iconSetting);
        labSetting.setToolTipText("设置");
        labSetting.setBounds(infoPanel.getWidth() - 120, infoPanel.getHeight() - 65, 50, 50);
        labSetting.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                container.setVisible(false);
                new SettingPanel();
            }
        });

        //add item to info panel
        infoPanel.add(labUserPhoto);
        infoPanel.add(labUserInfo);

        infoPanel.add(labLaunch);
        infoPanel.add(labSetting);

        /* ====info panel===== */

        /* Server List */
        JScrollPane serverListPanel = new JScrollPane();
        serverListPanel.setBounds(container.getWidth() - 220, 0, 220, container.getHeight() - 80);


        JPanel panel = new JPanel();
        panel.setLayout(null);
        serverListPanel.setViewportView(panel);

        ServerTile mg = new ServerTile("MechGear群组服务器", ResourcesManager.getImageFormURL("http://ww4.sinaimg.cn/mw690/005uLTuygw1etcup9vg4vj30nq0dcwib.jpg"));
        mg.setBounds(10, 10, 200, 100);

        ServerTile ic = new ServerTile("工业魔法", ResourcesManager.getImageFormURL("http://ww3.sinaimg.cn/mw690/005uLTuygw1euvlzjrp9dj30nq0dcjt9.jpg"));
        ic.setBounds(10, 120, 200, 100);

        ServerTile gt = new ServerTile("格雷科技", ResourcesManager.getImageFormURL("http://ww4.sinaimg.cn/mw690/005uLTuygw1evboszt8nij311y0jm491.jpg"));
        gt.setBounds(10, 230, 200, 100);

        panel.add(mg);
        panel.add(ic);
        panel.add(gt);

        /* ====Server List==== */

        //add item to container
        container.add(infoPanel);
        container.add(serverListPanel);
    }
}

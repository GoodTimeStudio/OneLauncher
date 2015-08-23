package com.mcgoodtime.gtgames.gui;

import com.mcgoodtime.gjmlc.core.Launcher;
import com.mcgoodtime.gjmlc.core.LibrariesManager;
import com.mcgoodtime.gtgames.ResourcesManager;
import com.mcgoodtime.gtgames.core.Auth;
import com.mcgoodtime.gtgames.core.GT_Games;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by suhao on 2015.8.22.0022.
 *
 * @author suhao
 */
public class MainPanel {

    protected static JPanel container;

    private static JPanel serverListPanel;

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
        JLabel labUserFace = new JLabel();
        labUserFace.setBounds(0, 0, 80, 80);
        //change icon size.
        //labUserFace.setIcon(new ImageIcon(ResourcesManager.steve));

        Thread getUserFaceThread = new Thread() {
            @Override
            public void run() {
                Image image = ResourcesManager.getImageFormURL("http://mcuuid.net/face/{player}.png".replace("{player}"
                        , Auth.getUsername())).getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                labUserFace.setIcon(new ImageIcon(image));
            }
        };
        getUserFaceThread.start();

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
        infoPanel.add(labUserFace);
        infoPanel.add(labUserInfo);

        infoPanel.add(labLaunch);
        infoPanel.add(labSetting);

        /* ====info panel===== */

        /* Server List */
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(container.getWidth() - 220, 0, 220, container.getHeight() - 80);

        serverListPanel = new JPanel();
        serverListPanel.setLayout(new FlowLayout());
        scrollPane.setViewportView(serverListPanel);

        Thread getServerListThread = new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://mcgoodtime.com/mgl/server-list.json");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    InputStream in = connection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(in, "UTF-8");
                    BufferedReader br = new BufferedReader(isr);

                    String line;
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        stringBuilder.append(line);
                    }

                    JSONObject object = new JSONObject(stringBuilder.toString());
                    JSONArray array = (JSONArray) object.get("server-list");
                    int servers = 0;
                    for (; servers < array.length(); servers++) {
                        JSONObject serverObj = (JSONObject) array.get(servers);
                        String name = serverObj.getString("name");
                        String imageURL = serverObj.getString("imageURL");

                        ServerTile serverTile = new ServerTile(name, ResourcesManager.getImageFormURL(imageURL));
                        serverListPanel.add(serverTile);
                    }

                    System.out.println((100 + 5) * servers);
                    serverListPanel.setPreferredSize(new Dimension(210, (100 + 5) * servers));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        getServerListThread.start();

        //serverListPanel.setPreferredSize(new Dimension(210, 1000));

        /* ====Server List==== */

        //add item to container
        container.add(infoPanel);
        container.add(scrollPane);
    }
}

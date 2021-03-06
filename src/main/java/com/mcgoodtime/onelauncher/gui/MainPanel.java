package com.mcgoodtime.onelauncher.gui;

import com.mcgoodtime.onelauncher.ResourcesManager;
import com.mcgoodtime.onelauncher.network.ServerStatus;
import com.mcgoodtime.onelauncher.core.Auth;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;

import static com.mcgoodtime.onelauncher.ResourcesManager.*;

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
                g2d.drawImage(mainBackground, 0, 0, container.getWidth(), container.getHeight(), null);
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
        SwingUtilities.invokeLater(() -> {
            Image image = null;
            try {
                image = getImageFormURL("http://mcuuid.net/face/{player}.png".replace("{player}"
                        , Auth.getUsername())).getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            } catch (IOException e) {
                e.printStackTrace();
            }
            labUserFace.setIcon(new ImageIcon(image));
        });

        //username info
        JLabel labUserName = new JLabel();
        labUserName.setText("游戏名字");
        labUserName.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        labUserName.setBounds(90, 0, 150, 30);

        JTextField textUserName = new JTextField();
        textUserName.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        textUserName.setBounds(90, 30, 150, 25);
        textUserName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                Auth.setUsername(textUserName.getText() + e.getKeyChar());
                System.out.println(Auth.getUsername());
            }
        });

        /* button */
        //launch button
        ImageIcon iconLaunch = new ImageIcon(btn_Next);
        JLabel labLaunch = new JLabel(iconLaunch);
        labLaunch.setToolTipText("启动客户端");
        labLaunch.setBounds(infoPanel.getWidth() - 60, infoPanel.getHeight() - 65, 50, 50);
        labLaunch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });

        //setting button
        ImageIcon iconSetting = new ImageIcon(btn_Setting);
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
        infoPanel.add(labUserName);
        infoPanel.add(textUserName);

        infoPanel.add(labLaunch);
        infoPanel.add(labSetting);
        /* ====info panel===== */

        /* Server Panel */
        JPanel sererPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(new Color(255, 255, 255, 100));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        sererPanel.setBounds(container.getWidth() - 220, 0, 220, container.getHeight() - 80);
        sererPanel.setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane() {
            @Override
            protected void paintBorder(Graphics g) {}
        };
        serverListPanel = new JPanel();
        serverListPanel.setLayout(new FlowLayout());
        scrollPane.setViewportView(serverListPanel);

        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        serverListPanel.setOpaque(false);
        sererPanel.setOpaque(false);

        SwingUtilities.invokeLater(() -> {
            try {
                URL url = new URL("http://mcgoodtime.com/mgl/server-list.json");
                URL url2 = new URL("https://minecraft-goodtime.github.io/mgl/server-list.json");

                InputStream in = null;
                try {
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(1500);
                    in = connection.getInputStream();
                } catch (Exception e) {
                    System.out.println("Get server list failed, try again...");
                    HttpURLConnection connection2 = (HttpURLConnection) url2.openConnection();
                    connection2.setConnectTimeout(1500);
                    in = connection2.getInputStream();
                }

                InputStreamReader isr = new InputStreamReader(in, "UTF-8");
                BufferedReader br = new BufferedReader(isr);

                String line;
                StringBuilder stringBuilder = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    stringBuilder.append(line);
                }

                JSONObject object = new JSONObject(stringBuilder.toString());
                JSONArray array = (JSONArray) object.get("server-list");
                serverListPanel.setPreferredSize(new Dimension(210, (100 + 5) * array.length()));

                for (int i = 0; i < array.length(); i++) {
                    JSONObject serverObj = (JSONObject) array.get(i);
                    String name = serverObj.getString("name");
                    String imageURL = serverObj.getString("imageURL");

                    ServerTile serverTile = new ServerTile(name, getImageFormURL(imageURL));
                    serverListPanel.add(serverTile);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        JLabel labServerOnline = new JLabel();
        labServerOnline.setText("在线人数:");
        labServerOnline.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        labServerOnline.setForeground(new Color(46, 204, 204));
        labServerOnline.setIcon(new ImageIcon(ResourcesManager.steve.getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
        SwingUtilities.invokeLater(() -> {
            ServerStatus status = new ServerStatus();
            status.setAddress(new InetSocketAddress("play.mcgoodtime.com", 25565));
            try {
                labServerOnline.setText("在线人数:" + status.fetchData().getPlayers().getOnline());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        sererPanel.add("South", labServerOnline);
        sererPanel.add(scrollPane);
        /* ====Server Panel==== */

        //add item to container
        container.add(infoPanel);
        container.add(sererPanel);
    }

}

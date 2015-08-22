package com.mcgoodtime.gtgames.gui;

import com.mcgoodtime.gtgames.ResourcesManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame {

	protected static JPanel mainPanel;
	
	int mx, my, fx, fy;

	/**
	 * Launch the application.
	 */
	public static void initGui() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
					new MainPanel();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*
	 * Create the frame.
	 */
	public MainWindow() {
		setUndecorated(true);
		setTitle("MechGear Games");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		mainPanel = new JPanel() {
			@Override
			protected void paintBorder(Graphics g) {
				Graphics2D g2d = (Graphics2D) g;
				g2d.setColor(Color.black);
				Stroke s = new BasicStroke(3);
				g2d.setStroke(s);
				g2d.drawRect(0, 0, getWidth()-1, getHeight()-1);
			}
		};
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPanel);
		mainPanel.setLayout(null);
		
		//change Look And Feel
		String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
		try {
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException | IllegalAccessException e) {
			e.printStackTrace();
		}

		JPanel title = new JPanel() {
			@Override
			protected void paintComponent(Graphics arg0) {
				super.paintComponent(arg0);
				Graphics2D g2d = (Graphics2D) arg0;//Graphics2D
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				g2d.setColor(new Color(0, 205, 205));//draw bar
				g2d.setClip(0, 0, getWidth(), 30);
				g2d.fillRect(0, 0, getWidth(), getHeight());
				g2d.setClip(null);

				g2d.setColor(Color.WHITE);//set color
				g2d.setFont(new Font("微软雅黑", Font.PLAIN, 15));//set font
				g2d.drawString("MechGear游戏平台", 20, 20);//draw title
			}
		};
		title.setBounds(0, 0, getWidth(), 30);

		title.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				setLocation(fx + (arg0.getXOnScreen() - mx), fy + (arg0.getYOnScreen() - my));//鼠标拖拽
			}
		});
		//鼠标拖住监听
		title.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				mx = arg0.getXOnScreen();
				my = arg0.getYOnScreen();
				fx = getX();
				fy = getY();
			}
		});
		
		/*  All Items */
		
		// close button
		ImageIcon iconClose = new ImageIcon(ResourcesManager.btn_Close); //get icon
		JLabel labClose = new JLabel(iconClose);
		labClose.setBounds(670, 0, 30, 30);
		//单机事件
		labClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
				
		//add item to main pane
		mainPanel.add(labClose);
		mainPanel.add(title);
	}
}

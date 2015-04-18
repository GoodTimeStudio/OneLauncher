package com.mcgoodtime.gtgames.client.gui;

import java.awt.EventQueue;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.JLabel;

public class MainFrame extends JFrame {

	private MainPanel mainPane;
	private JPanel container;
	
	private Icon iconClose;
	
	int mx, my, fx, fy;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		/* ***********������************ */
		setUndecorated(true);
		setTitle("GoodTime Games");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		mainPane = new MainPanel();
		mainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPane);
		mainPane.setLayout(null);
		
		mainPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				setLocation(fx + (arg0.getXOnScreen() - mx), fy + (arg0.getYOnScreen() - my));//����λ�ã��ƶ���
			}
		});
		//�������������ȡ����ƶ�����
		mainPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				mx = arg0.getXOnScreen();
				my = arg0.getYOnScreen();
				fx = getX();
				fy = getY();
			}
		});
		
		String closetp = this.getClass().getResource("img/close.jpg").getPath();//��ȡicon·��
		ImageIcon imaClose = new ImageIcon(closetp);
		JLabel labClose = new JLabel(imaClose);
		labClose.setBounds(681, 0, imaClose.getIconWidth(), imaClose.getIconHeight());
		//���������¼�
		labClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		
		/* *************************** */
		
		/* **********container******* */
		container = new JPanel();
		container.setBounds(0, 30, 700, 420);
		container.setLayout(null);
		/* ************************** */
		
		//�����������
		mainPane.add(labClose);
		mainPane.add(container);
		
		
		
	}
	

}

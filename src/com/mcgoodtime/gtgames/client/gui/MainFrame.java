package com.mcgoodtime.gtgames.client.gui;

import com.mcgoodtime.gtgames.core.Auth;
import com.mcgoodtime.gtgames.resources.ResourcesManager;

import java.awt.*;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;

import com.mcgoodtime.gtgames.client.panel.LoginPanel;
import com.mcgoodtime.gtgames.client.panel.MainPanel;

public class MainFrame extends JFrame {

	private static MainPanel mainPanel;
	private static JPanel container;
	public static JPanel containerLogin;

	private static JLabel labLogin;
	private static JLabel labLoginState;
	
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
		/* ***********main Window************ */
		setUndecorated(true);
		setTitle("GoodTime Games");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		mainPanel = new MainPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPanel);
		mainPanel.setLayout(null);
		
		//change Look And Feel
		String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
		try {
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		//title panel
		JPanel titlePanel = new JPanel();

		
		mainPanel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				setLocation(fx + (arg0.getXOnScreen() - mx), fy + (arg0.getYOnScreen() - my));//鼠标拖拽
			}
		});
		//鼠标拖住监听
		mainPanel.addMouseListener(new MouseAdapter() {
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
		ImageIcon iconClose = new ImageIcon(ResourcesManager.getTexture("close.png")); //get icon
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

		LoginPage lp = new LoginPage();
		
	}
	
	static class LoginPage {
		public LoginPage() {
			/* Auth Container */
			containerLogin = new LoginPanel();
			containerLogin.setBounds(2, 30, 696, 418);
			containerLogin.setLayout(null);
			mainPanel.add(containerLogin);
			/* *************** */
			
			/* **** text **** */
			final JTextField textUsername = new JTextField();
			textUsername.setFont(new Font("微软雅黑", Font.PLAIN, 12));
			textUsername.setBounds(containerLogin.getWidth() / 2 - 100, containerLogin.getHeight() / 2 - 80, 200, 30);

			final JPasswordField textPassword = new JPasswordField();
			textPassword.setFont(new Font("微软雅黑", Font.PLAIN, 12));
			textPassword.setBounds(containerLogin.getWidth() / 2 - 100, containerLogin.getHeight() / 2 - 40, 200, 30);

			/* **** button **** */
			//login
			ImageIcon iconLogin = new ImageIcon(ResourcesManager.getTexture("next.png")); //get icon
			labLogin = new JLabel(iconLogin);
			labLogin.setBounds(containerLogin.getWidth() / 2 + 80, containerLogin.getHeight() / 2 - 95, 100, 100);

			labLogin.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					String textUsernameText = textUsername.getText();

					if (!textUsernameText.isEmpty()) {
						char[] password = textPassword.getPassword();
						if (password.length < 1) {
							labLoginState.setForeground(Color.RED);
							labLoginState.setText("请输入正确的密码");
						} else {
							//auth
							ImageIcon iconSignUp = new ImageIcon(ResourcesManager.getTexture("loading.gif")); //get icon
							labLoginState.setIcon(iconSignUp);
							labLoginState.setText("正在连接登陆服务器...");

							boolean loginServerState = Auth.getLoginServerState(); //get Server State(WIP)
							if (loginServerState) {
								/*
								 * Login, if return true, hide the login page,go to next.
								 */
								boolean login = Auth.Login(textUsernameText, password);
								if (login) {
									//login success, go to main page
									containerLogin.setVisible(false);
									containerLogin.setBounds(0, 0, 0, 0);
									mainPanel.remove(containerLogin); //disable login page.
									MainPage mp = new MainPage();// go to next.
								} else {
									textPassword.setText(null);
								}
							}
						}
					} else {
						labLoginState.setForeground(Color.RED);
						labLoginState.setText("请输入正确的用户名");
					}
				}
			});

			//login state
			labLoginState = new JLabel();
			labLoginState.setFont(new Font("微软雅黑", Font.PLAIN, 12));
			labLoginState.setBounds(containerLogin.getWidth() / 2 - 100, containerLogin.getHeight() / 2, 200, 20);


			//add item to login page
			containerLogin.add(textUsername);
			containerLogin.add(textPassword);

			containerLogin.add(labLogin);
			containerLogin.add(labLoginState);
		}
	}

	static class MainPage {
		public MainPage() {

			/* **********container******* */
			container = new JPanel();
			container.setBounds(2, 30, 696, 418);
			container.setLayout(null);
			mainPanel.add(container);
			/* ************************** */

			//launch button
			JButton launch = new JButton("启动客户端");
			launch.setFont(new Font("微软雅黑", Font.PLAIN, 12));
			launch.setBounds(520, 350, 150, 50);
			/* =========================== */

			//add item to container
			container.add(launch);
		}
	}
}



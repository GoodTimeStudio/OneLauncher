package com.mcgoodtime.gtgames.client.gui;

import com.mcgoodtime.gtgames.client.panel.NotePanel;
import com.mcgoodtime.gtgames.client.panel.SettingPanel;
import com.mcgoodtime.gtgames.core.Auth;
import com.mcgoodtime.gtgames.resources.ResourcesManager;

import java.awt.*;
import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import com.mcgoodtime.gtgames.client.panel.LoginPanel;
import com.mcgoodtime.gtgames.client.panel.TitlePanel;

public class MainFrame extends JFrame {

	private static JPanel mainPanel;
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
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		TitlePanel title = new TitlePanel();
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
		mainPanel.add(title);

		LoginPage lp = new LoginPage();
		
	}
	
	static class LoginPage {
		private static JTextField textUsername;
		private static JPasswordField textPassword;

		public LoginPage() {
			/* Auth Container */
			containerLogin = new LoginPanel();
			containerLogin.setBounds(2, 30, 696, 418);
			containerLogin.setLayout(null);
			mainPanel.add(containerLogin);
			/* *************** */
			
			/* **** text **** */
			textUsername = new JTextField();
			textUsername.setFont(new Font("微软雅黑", Font.PLAIN, 12));
			textUsername.setBounds(containerLogin.getWidth() / 2 - 100, containerLogin.getHeight() / 2 - 80, 200, 30);
			textUsername.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					if (e.getKeyChar() == '\n') {
						login();
					}
				}
			});

			textPassword = new JPasswordField();
			textPassword.setFont(new Font("微软雅黑", Font.PLAIN, 12));
			textPassword.setBounds(containerLogin.getWidth() / 2 - 100, containerLogin.getHeight() / 2 - 40, 200, 30);
			textPassword.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					if (e.getKeyChar() == '\n') {
						login();
					}
				}
			});

			/* **** button **** */
			//login
			ImageIcon iconLogin = new ImageIcon(ResourcesManager.getTexture("next.png")); //get icon
			labLogin = new JLabel(iconLogin);
			labLogin.setToolTipText("登陆");
			labLogin.setBounds(containerLogin.getWidth() / 2 + 80, containerLogin.getHeight() / 2 - 95, 100, 100);

			labLogin.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					login();
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

		private static void login() {
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
							new MainPage();// go to next.
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
	}

	static class MainPage {
		public MainPage() {

			/* **********container******* */
			container = new JPanel() {
				@Override
				protected void paintComponent(Graphics g) {
					Graphics2D g2d = (Graphics2D) g;
					InputStream inMainBackground = ResourcesManager.getTextureAsStream("mainBackground.png");
					BufferedImage imageMainBackground = null;
					try {
						imageMainBackground = ImageIO.read(inMainBackground);
					} catch (IOException e) {
						e.printStackTrace();
					}
					g2d.drawImage(imageMainBackground, 0, 0, container.getWidth(), container.getHeight(), null);
				}
			};
			container.setBounds(2, 30, 696, 418);
			container.setLayout(null);
			container.setVisible(true);
			mainPanel.add(container);
			/* ************************** */

			//Notes Panel
			NotePanel notePanel = new NotePanel();
			notePanel.setBounds(10, 10, container.getWidth() - 20, 200 - 10);
			notePanel.setEditable(false);
			notePanel.setOpaque(false);

			try {
				notePanel.setPage("http://minecraft-goodtime.github.io/UpdateNotes/MinecraftGoodTime.html");
			} catch (IOException e) {
				e.printStackTrace();
			}

			/* button */

			//launch button
			ImageIcon iconLaunch = new ImageIcon(ResourcesManager.getTexture("next.png"));
			JLabel labLaunch = new JLabel(iconLaunch);
			labLaunch.setToolTipText("启动客户端");
			labLaunch.setBounds(container.getWidth() - 60, container.getHeight() - 60, 50, 50);

			//setting button
			ImageIcon iconSetting = new ImageIcon(ResourcesManager.getTexture("setting.png"));
			JLabel labSetting = new JLabel(iconSetting);
			labSetting.setToolTipText("设置");
			labSetting.setBounds(container.getWidth() - 120, container.getHeight() - 60, 50, 50);
			labSetting.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								Setting dialog = new Setting();
								dialog.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
			});

			/* =========================== */

			//add item to container
			container.add(notePanel);

			container.add(labLaunch);
			container.add(labSetting);
		}
	}

	static class Setting extends JDialog {
		public Setting() {
			/* Setting Window */
			setUndecorated(true);
			setTitle("GoodTime Games Setting");
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 500, 300);
			SettingPanel setting = new SettingPanel();
			setting.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(setting);
			setting.setLayout(null);
		}
	}
}

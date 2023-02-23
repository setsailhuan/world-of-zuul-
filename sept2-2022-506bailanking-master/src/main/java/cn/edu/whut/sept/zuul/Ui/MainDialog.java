/**
 * 该类是“World-of-Zuul”应用程序的主对话框类。
 *
 * @author  chenpang
 * @version 3.0
 */

package cn.edu.whut.sept.zuul.Ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.edu.whut.sept.zuul.Comm.Command;
import cn.edu.whut.sept.zuul.Comm.CommandWords;
import cn.edu.whut.sept.zuul.Mains.Game;
import cn.edu.whut.sept.zuul.Mains.Goods;
import cn.edu.whut.sept.zuul.Mains.Player;
import cn.edu.whut.sept.zuul.Mains.Room;
import cn.edu.whut.sept.zuul.Mysql.MySqlService;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class MainDialog extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	Game game = new Game(100, (String) "player");
	private boolean isbag;
	JLabel lb = new JLabel();
	private CommandWords commands; // holds all valid command words
	JPanel panel_2 = new JPanel();
	JButton westButton = new JButton("west");
	JButton northButton = new JButton("north");
	JButton eastButton = new JButton("east");
	JButton southButton = new JButton("south");
	JButton btnNewButton = new JButton("trans");
	JButton switchButton = new JButton("switch");
	JButton takeButton = new JButton("take");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainDialog frame = new MainDialog();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 对不同的房间设置对应的背景
	 */
	public void setMainBackGronud() {
		ImageIcon icon = null;
		int w;
		if (isbag) {
			icon = new ImageIcon("image/6.png");
			lb.setIcon(icon);
			return;
		} else
			w = game.getCurrentRoom().getPos();
		w--;
		switch (w) {
			case 0 :
				icon = new ImageIcon("image/outside.png");
				break;
			case 1 :
				icon = new ImageIcon("image/theater.png");
				break;
			case 2 :
				icon = new ImageIcon("image/pub.png");
				break;
			case 3 :
				icon = new ImageIcon("image/lab.png");
				break;
			case 4 :
				icon = new ImageIcon("image/office.png");
				break;
			case -1 :
				icon = new ImageIcon("image/trans.png");
				break;

		}

		lb.setIcon(icon);
	}

	/**
	 * 提示房间的信息及出口
	 */
	public void setRoomTip() {
		Room room = game.getCurrentRoom();
		lblNewLabel.setText("this place is " + room.getShortDescription() + ".."
				+ room.getExitString());
	}

	/**
	 * 创建对话框
	 */
	public MainDialog() {

		commands = new CommandWords();
		isbag = false;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 735);
		setResizable(false);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JButton HelpButton = new JButton("Help");
		HelpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String hstr = "《World of Zuul》是一款简单的文本冒险游戏。\n"
						+ "1.用户可以在一些房间组成的迷宫中探险。\n" 
						+ "2.每个房间有四个方向，但并不是每个方向都有门\n"
						+ "3.每个房间有不同的物品，点击take即可将物品放入包中\n"
						+ "4.所有的房间中有一个随机传送房间\n" 
						+ "5.使用swtich在背包和房间界面切换\n"
						+ "6.找到魔法曲奇，可以增加你的背包容量\n";

				JOptionPane.showConfirmDialog(contentPane, hstr, "帮助",
						JOptionPane.PLAIN_MESSAGE);
			}
		});
		menuBar.add(HelpButton);

		JButton SaveButton = new JButton("Save");
		SaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MySqlService ser = new MySqlService();
				ser.SaveData(game);
			}
		});
		menuBar.add(SaveButton);

		JButton About = new JButton("About");
		About.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String hstr = "Version 4.0;\nPublished by 506bailanKing.";
				JOptionPane.showConfirmDialog(contentPane, hstr, "关于",
						JOptionPane.PLAIN_MESSAGE,
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		menuBar.add(About);

		String s = "World of Zuul";

		for (int i = 0; i < 55; i++) {
			s = "-" + s + "-";
		}

		JMenuItem mntmNewMenuItem = new JMenuItem(s);
		menuBar.add(mntmNewMenuItem);

		JMenuBar menuBar_1 = new JMenuBar();
		menuBar.add(menuBar_1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JTableDemo myModel = new JTableDemo();
		JTable table = new JTable(myModel);
		myModel.setit(game.getCurrentGoods());
		/**
		 * JScrollPane scrollPane = new JScrollPane(table);
		 * contentPane.add(scrollPane, BorderLayout.NORTH);
		 */

		contentPane.add(table, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		takeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				Goods goods;
				game.setTablePos(row);
				if (!isbag) {
					goods = game.getCurrentGoods();
					if (row >= 0 && row < goods.getAllBox().size()) {
						int p = goods.getBox(row).getWeight();
						if (p == 0) {
							String hstr = "Do you want to eat cookies?";
							int res = JOptionPane.showConfirmDialog(contentPane,
									hstr, "Eat", JOptionPane.OK_OPTION,
									JOptionPane.INFORMATION_MESSAGE);
							if (res == 0) {
								hstr = "Increased Backpack Capacity";
								JOptionPane.showConfirmDialog(contentPane, hstr,
										"Bag", JOptionPane.PLAIN_MESSAGE,
										JOptionPane.INFORMATION_MESSAGE);
								game.player.addUp();
							}
						}
						if (p > game.getplayer().getbag()) {
							String hstr = "Your backpack doesn't have "
									+ "enough capacity to hold this item";
							JOptionPane.showConfirmDialog(contentPane, hstr,
									"Back", JOptionPane.PLAIN_MESSAGE,
									JOptionPane.INFORMATION_MESSAGE);
						}
						Command command = commands.get((String) "take");
						command.execute(game);
						goods = game.getCurrentGoods();
					}

				} else {
					goods = game.getpgoods();

					if (row >= 0 && row < goods.getAllBox().size()) {
						if (game.getCurrentGoods().getAllBox().size() >= 6) {
							String hstr = "The room is full of things";
							JOptionPane.showConfirmDialog(contentPane, hstr,
									"Back", JOptionPane.PLAIN_MESSAGE,
									JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						Command command = commands.get((String) "drop");
						command.execute(game);
						goods = game.getpgoods();
					}
				}

				myModel.setit(goods);
				table.repaint();
			}
		});
		contentPane.add(takeButton, BorderLayout.EAST);

		switchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Goods goods;
				if (!isbag) {
					goods = game.getpgoods();
					takeButton.setText("drop");
					Player player = game.getplayer();
					lblNewLabel.setText("The current weight of your bag is "
							+ player.getnowWeight()
							+ ", and the weight you can carry is "
							+ player.getbag());
					westButton.setVisible(false);
					eastButton.setVisible(false);
					northButton.setVisible(false);
					southButton.setVisible(false);
				} else {
					goods = game.getCurrentGoods();
					takeButton.setText("take");
					setRoomTip();
					westButton.setVisible(true);
					eastButton.setVisible(true);
					northButton.setVisible(true);
					southButton.setVisible(true);
				}
				isbag = !isbag;

				myModel.setit(goods);
				table.repaint();
				setMainBackGronud();
			}
		});
		contentPane.add(switchButton, BorderLayout.WEST);

		JButton BackButton = new JButton("Back");
		BackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = "back";
				if (isbag) {

					takeButton.setText("take");
					westButton.setVisible(true);
					eastButton.setVisible(true);
					northButton.setVisible(true);
					southButton.setVisible(true);
					isbag = false;
				} else {
					Command command = commands.get(s);
					command.execute(game);

					if (game.isStart() == true) {
						String hstr = "you are back to where you start!";
						JOptionPane.showConfirmDialog(contentPane, hstr, "Back",
								JOptionPane.PLAIN_MESSAGE,
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
				setRoomTip();
				myModel.setit(game.getCurrentGoods());
				table.repaint();
				setMainBackGronud();
			}
		});
		menuBar.add(BackButton);

		westButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Command command = commands.get((String) "go");
				command.setSecondWord((String) "west");
				command.execute(game);

				setRoomTip();
				myModel.setit(game.getCurrentGoods());
				table.repaint();
				setMainBackGronud();
			}
		});

		panel.add(westButton, BorderLayout.WEST);

		northButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int p = game.getCurrentRoom().getPos();
				System.out.println(p);
				if (p == 1) {
					game.setCurrentRoom(game.getCurrentRoom().getExit("north"));
					setRoomTip();
					myModel.setit(game.getCurrentGoods());
					table.repaint();
					table.setVisible(false);
					setMainBackGronud();
					btnNewButton.setVisible(true);
					westButton.setVisible(false);
					eastButton.setVisible(false);
					northButton.setVisible(false);
					southButton.setVisible(false);
					switchButton.setVisible(false);
					takeButton.setVisible(false);
					return;
				}
				Command command = commands.get((String) "go");
				command.setSecondWord((String) "north");
				command.execute(game);
				setRoomTip();
				myModel.setit(game.getCurrentGoods());
				table.repaint();
				setMainBackGronud();
			}
		});
		/**
		 * panel.add(northButton, BorderLayout.NORTH);
		 */

		eastButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Command command = commands.get((String) "go");
				command.setSecondWord((String) "east");
				command.execute(game);
				setRoomTip();
				myModel.setit(game.getCurrentGoods());
				table.repaint();
				setMainBackGronud();
			}
		});
		panel.add(eastButton, BorderLayout.EAST);

		southButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Command command = commands.get((String) "go");
				command.setSecondWord((String) "south");
				command.execute(game);
				setRoomTip();
				myModel.setit(game.getCurrentGoods());
				table.repaint();
				setMainBackGronud();
			}
		});
		/**
		 * panel_1.add(southButton, BorderLayout.SOUTH);
		 */

		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		lblNewLabel = new JLabel();
		lblNewLabel.setForeground(Color.green);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(Color.black);
		setRoomTip();
		panel.add(lblNewLabel, BorderLayout.NORTH);

		panel_1.add(panel_2, BorderLayout.CENTER);

		panel_1.add(northButton, BorderLayout.NORTH);
		panel_1.add(southButton, BorderLayout.SOUTH);

		setMainBackGronud();
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				btnNewButton.setVisible(false);
				westButton.setVisible(true);
				eastButton.setVisible(true);
				northButton.setVisible(true);
				southButton.setVisible(true);
				switchButton.setVisible(true);
				takeButton.setVisible(true);
				game.setCurrentRoom(game.getCurrentRoom().getExit("south"));
				Command command = commands.get((String) "go");
				command.setSecondWord((String) "north");
				command.execute(game);
				setRoomTip();
				myModel.setit(game.getCurrentGoods());
				table.repaint();
				table.setVisible(true);
				setMainBackGronud();
			}
		});
		panel_2.setLayout(new BorderLayout(0, 0));

		panel_2.add(btnNewButton, BorderLayout.NORTH);
		btnNewButton.setVisible(false);
		panel_2.add(lb, BorderLayout.CENTER);

	}

}

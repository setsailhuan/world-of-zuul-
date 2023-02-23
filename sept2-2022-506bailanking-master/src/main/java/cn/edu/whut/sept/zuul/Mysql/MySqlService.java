/**
 * 该类是“World-of-Zuul”应用程序的数据库服务类。
 *
 * 实现了数据库功能
 * 
 * @author  chenpang
 * @version 3.0
 */
package cn.edu.whut.sept.zuul.Mysql;

import java.sql.*;
import java.util.Vector;

import cn.edu.whut.sept.zuul.Mains.Game;
import cn.edu.whut.sept.zuul.Mains.Goods;
import cn.edu.whut.sept.zuul.Mains.Player;
import cn.edu.whut.sept.zuul.Mains.Room;

public class MySqlService {

	// MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/worldofzuul?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	static Connection conn = null;
	static Statement stmt = null;

	// 数据库的用户名与密码
	static final String USER = "root";
	static final String PASS = "yf20010801";

	public Player getPlayerData(int id) {
		Player player = null;

		try {
			// 注册 JDBC 驱动
			Class.forName(JDBC_DRIVER);

			// 打开链接
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// 执行查询
			System.out.println("查询玩家数据.....");
			stmt = conn.createStatement();
			String sql;

			sql = "SELECT * from player where id = " + String.valueOf(id);
			ResultSet rs = stmt.executeQuery(sql);

			// 展开结果集数据库
			while (rs.next()) {
				// 通过字段检索
				String name = rs.getString("name");
				int pos = rs.getInt("pos");
				int b = rs.getInt("bag");
				int w = rs.getInt("weight");
				player = new Player(id, name, pos, b, w);
			}
			// 完成后关闭
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// 处理 JDBC 错误
			se.printStackTrace();
		} catch (Exception e) {
			// 处理 Class.forName 错误
			e.printStackTrace();
		} finally {
			// 关闭资源
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // 什么都不做
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return player;
	}

	public Goods getGoodsofID(int id) {
		Goods goods = new Goods();
		try {
			// 注册 JDBC 驱动
			Class.forName(JDBC_DRIVER);

			// 打开链接
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// 执行查询
			System.out.println("查询" + id + "信息....");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * from box where belong = " + String.valueOf(id);
			ResultSet rs = stmt.executeQuery(sql);

			// 展开结果集数据库
			while (rs.next()) {
				// 通过字段检索
				id = rs.getInt("id");
				String name = rs.getString("name");
				int weight = rs.getInt("weight");
				String desc = rs.getString("description");

				// 输出数据
				goods.addBox(id, name, weight, desc);
			}
			// 完成后关闭
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// 处理 JDBC 错误
			se.printStackTrace();
		} catch (Exception e) {
			// 处理 Class.forName 错误
			e.printStackTrace();
		} finally {
			// 关闭资源
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // 什么都不做
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return goods;
	}

	public boolean SaveData(Game game) {
		Vector<Goods> vt = game.getAllGoods();

		try {
			// 注册 JDBC 驱动
			Class.forName(JDBC_DRIVER);

			// 打开链接
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// 执行查询
			System.out.println("保存player信息....");
			stmt = conn.createStatement();
			String sql;
			Player player = game.getplayer();
			sql = "UPDATE `player` SET `bag` = '" + player.getbag() + "', `weight` = '" + player.getnowWeight()
					+ "', `pos` = '" + player.getRoom() + "' WHERE (`id` = '" + player.getId() + "')";
			// System.out.print(sql);
			stmt.executeUpdate(sql);

			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// 处理 JDBC 错误
			se.printStackTrace();
		} catch (Exception e) {
			// 处理 Class.forName 错误
			e.printStackTrace();
		} finally {
			// 关闭资源
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // 什么都不做
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		Goods pgoods = game.getpgoods();
		System.out.println("保存物品信息.....");
		for (int j = 0; j < pgoods.getAllBox().size(); j++) {
			int id = pgoods.getBox(j).getId();
			int bg = game.getplayer().getId();
			try {
				// 注册 JDBC 驱动
				Class.forName(JDBC_DRIVER);

				// 打开链接
				conn = DriverManager.getConnection(DB_URL, USER, PASS);

				// 执行查询

				stmt = conn.createStatement();
				String sql;

				sql = "UPDATE `box` SET `belong` = '" + bg + "' WHERE (`id` = '" + id + "');";
				// System.out.print(sql);
				stmt.executeUpdate(sql);

				stmt.close();
				conn.close();
			} catch (SQLException se) {
				// 处理 JDBC 错误
				se.printStackTrace();
			} catch (Exception e) {
				// 处理 Class.forName 错误
				e.printStackTrace();
			} finally {
				// 关闭资源
				try {
					if (stmt != null)
						stmt.close();
				} catch (SQLException se2) {
				} // 什么都不做
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}

		for (int i = 0; i < vt.size(); i++) {
			Goods goods = vt.get(i);

			for (int j = 0; j < goods.getAllBox().size(); j++) {
				int id = goods.getBox(j).getId();
				try {
					// 注册 JDBC 驱动
					Class.forName(JDBC_DRIVER);

					// 打开链接
					conn = DriverManager.getConnection(DB_URL, USER, PASS);

					// 执行查询
					// System.out.println("查询房间.....");
					stmt = conn.createStatement();
					String sql;

					sql = "UPDATE `box` SET `belong` = '" + i + "' WHERE (`id` = '" + id + "');";
					// System.out.print(sql);
					stmt.executeUpdate(sql);

					stmt.close();
					conn.close();
				} catch (SQLException se) {
					// 处理 JDBC 错误
					se.printStackTrace();
				} catch (Exception e) {
					// 处理 Class.forName 错误
					e.printStackTrace();
				} finally {
					// 关闭资源
					try {
						if (stmt != null)
							stmt.close();
					} catch (SQLException se2) {
					} // 什么都不做
					try {
						if (conn != null)
							conn.close();
					} catch (SQLException se) {
						se.printStackTrace();
					}
				}
			}
		}
		System.out.println("保存成功！");
		return true;
	}

	public Vector<Room> getRoom() {
		Vector<MySqlRoom> room = new Vector<MySqlRoom>();
		Vector<Room> rm = new Vector<Room>();
		try {
			// 注册 JDBC 驱动
			Class.forName(JDBC_DRIVER);

			// 打开链接
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// 执行查询
			System.out.println("查询房间.....");
			stmt = conn.createStatement();
			String sql;

			sql = "SELECT * from room";
			ResultSet rs = stmt.executeQuery(sql);

			// 展开结果集数据库
			while (rs.next()) {
				// 通过字段检索
				int id = rs.getInt("id");
				String desc = rs.getString("description");
				int w = rs.getInt("west");
				int e = rs.getInt("east");
				int n = rs.getInt("north");
				int s = rs.getInt("south");
				room.add(new MySqlRoom(id, w, e, n, s, desc));
				rm.add(new Room(desc, id));
			}
			// 完成后关闭
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// 处理 JDBC 错误
			se.printStackTrace();
		} catch (Exception e) {
			// 处理 Class.forName 错误
			e.printStackTrace();
		} finally {
			// 关闭资源
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // 什么都不做
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		for (int i = 0; i < room.size(); i++) {
			Room r = rm.get(i);
			MySqlRoom rpp = room.get(i);
			if (rpp.getWest() >= 0) {
				r.setExit("west", rm.get(rpp.getWest()));
			}
			if (rpp.getEast() >= 0) {
				r.setExit("east", rm.get(rpp.getEast()));
			}
			if (rpp.getNorth() >= 0) {
				r.setExit("north", rm.get(rpp.getNorth()));
			}
			if (rpp.getSouth() >= 0) {
				r.setExit("south", rm.get(rpp.getSouth()));
			}

			rm.set(i, r);
		}

		return rm;
	}

}
/**
 * 该类是“World-of-Zuul”应用程序的主类。
 * 《World of Zuul》是一款简单的文本冒险游戏。用户可以在一些房间组成的迷宫中探险。
 * 你们可以通过扩展该游戏的功能使它更有趣!.
 *
 * 如果想开始执行这个游戏，用户需要创建Game类的一个实例并调用“play”方法。
 *
 * Game类的实例将创建并初始化所有其他类:它创建所有房间，并将它们连接成迷宫；它创建解析器
 * 接收用户输入，并将用户输入转换成命令后开始运行游戏。
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 1.0
 */
package cn.edu.whut.sept.zuul.Mains;

import java.util.Stack;
import java.util.Vector;

import cn.edu.whut.sept.zuul.Comm.Command;
import cn.edu.whut.sept.zuul.Comm.Parser;
import cn.edu.whut.sept.zuul.Mysql.MySqlService;

public class Game {
	public static int MAX_NUM = 100;
	private Parser parser;
	private Room currentRoom;
	private int cnt;
	public Player player;
	private Vector<Goods> vt;
	private Vector<Room> rt;
	private Stack<Room> st;
	private Goods pgoods;
	private int cookie, TablePos;

	/***
	 * 默认构造函数
	 ***/
	public Game() {
		cnt = 0;
		createRooms();
		parser = new Parser();
		TablePos = -1;
	}

	/***
	 * 带参构造函数
	 ***/
	public Game(int id, String name) {
		CreateRoomFromMySql(id, name);
	}

	public int getcookie() {
		return cookie;
	}

	public void deletecookie() {
		cookie = -2;
	}

	public void setplaybag() {
		player.addUp();
	}

	public boolean asweight(int w) {
		return player.addweight(w);
	}

	/***
	 * 从MySql创建房间地图
	 ***/
	public void CreateRoomFromMySql(int id, String name) {
		MySqlService ser = new MySqlService();
		player = ser.getPlayerData(id);
		rt = ser.getRoom();
		pgoods = ser.getGoodsofID(id);
		vt = new Vector<Goods>();
		for (int i = 0; i < rt.size(); i++) {
			vt.add(ser.getGoodsofID(i));
		}
		st = new Stack<Room>();
		currentRoom = rt.get(player.getRoom()); // start game outside
		st.push(currentRoom);
	}

	/***
	 * 创建房间
	 ***/
	public void createRooms() {

		Room outside, theater, pub, lab, office;
		// create the rooms
		cnt = 1;
		outside = new Room("outside the main entrance of the university", cnt++);
		theater = new Room("in a lecture theater", cnt++);
		pub = new Room("in the campus pub", cnt++);
		lab = new Room("in a computing lab", cnt++);
		office = new Room("in the computing admin office", cnt++);

		// initialise room exits
		outside.setExit("east", theater);
		outside.setExit("south", lab);
		outside.setExit("west", pub);

		theater.setExit("west", outside);

		pub.setExit("east", outside);

		lab.setExit("north", outside);
		lab.setExit("east", office);

		office.setExit("west", lab);
		cookie = office.getPos();

		vt = new Vector<Goods>();

		Goods goods = new Goods();
		vt.add(goods);
		goods.addBox(1, "stick", 1, "u can use it to hit sth.");
		goods.addBox(8, "boulder", 6, "it is too heay.");
		goods.addBox(9, "cookie", 0, "magic!");
		vt.add(goods);

		goods = new Goods();
		goods.addBox(2, "gas", 1, "it can be used as fuel.");
		goods.addBox(3, "vilion", 2, "play it !!");
		vt.add(goods);

		goods = new Goods();
		goods.addBox(4, "juice", 1, "u can drink it");
		vt.add(goods);

		goods = new Goods();
		goods.addBox(5, "form", 1, "it maybe very important");
		vt.add(goods);

		goods = new Goods();
		goods.addBox(6, "pen", 1, "u can use it to write");
		goods.addBox(7, "paper", 1, "write sth on it!");
		vt.add(goods);

		st = new Stack<Room>();

		Room trans;
		trans = new Room("", 0);
		trans.setExit("south", outside);
		outside.setExit("north", trans);

		rt = new Vector<Room>();
		rt.add(trans);
		rt.add(outside);
		rt.add(theater);
		rt.add(pub);
		rt.add(lab);
		rt.add(office);

		currentRoom = outside; // start game outside
		st.push(currentRoom);
		pgoods = new Goods();
		player = new Player(-1, "test", 0, 5, 0);
	}

	/***
	 * 程序开始窗口
	 ***/
	public void play() {
		printWelcome();

		// Enter the main command loop. Here we repeatedly read commands and
		// execute them until the game is over.

		boolean finished = false;
		while (!finished) {
			Command command = parser.getCommand();
			if (command == null) {
				System.out.println("I don't understand...");
			} else {
				finished = command.execute(this);
			}
		}

		System.out.println("Thank you for playing.  Good bye.");
	}

	/***
	 * 打印欢迎语
	 ***/
	private void printWelcome() {
		System.out.println();
		System.out.println("Welcome to the World of Zuul!");
		System.out.println("World of Zuul is a new, incredibly boring adventure game.");
		System.out.println("Type 'help' if you need help.");
		System.out.println();
		System.out.println(currentRoom.getLongDescription());
	}

	/***
	 * 获取当前物品信息
	 ***/
	public Goods getCurrentGoods() {
		return vt.get(currentRoom.getPos());
	}

	/***
	 * 获取当前房间信息
	 ***/
	public Room getCurrentRoom() {
		return currentRoom;
	}

	/***
	 * 添加历史房间信息
	 ***/
	public void RoadAdd(Room room) {
		st.push(room);
	}

	/***
	 * 删除历史房间信息
	 ***/
	public boolean Roadpop() {
		if (st.size() > 1)
			st.pop();
		else
			return true;
		currentRoom = st.peek();
		return false;
	}

	/***
	 * 判断游戏是否开始
	 ***/
	public boolean isStart() {
		if (st.size() == 1)
			return true;
		return false;
	}

	/***
	 * 获取房间参数
	 ***/
	public Room getIndexRoom(int pos) {
		return rt.get(pos);
	}

	/***
	 * 设置当前房间
	 ***/
	public void setCurrentRoom(Room room) {
		this.currentRoom = room;
	}

	/***
	 * 更新当前物品信息
	 ***/
	public void updateCuttrntGoods(Goods goods) {
		vt.set(currentRoom.getPos(), goods);
	}

	/***
	 * 更新房间物品信息
	 ***/
	public void updatePgoods(Goods pgoods2) {
		pgoods = pgoods2;
	}

	public Goods getpgoods() {

		return pgoods;
	}

	public void setTablePos(int row) {
		TablePos = row;
	}

	public int getTablePos() {

		return TablePos;
	}

	public Player getplayer() {
		return player;
	}

	public Vector<Goods> getAllGoods() {
		return vt;
	}

	public void setplayerpos(int pos) {
		// TODO Auto-generated method stub
		player.setRoom(pos);
	}

}
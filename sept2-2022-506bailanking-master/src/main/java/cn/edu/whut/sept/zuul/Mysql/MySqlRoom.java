/**
 * 该类是“World-of-Zuul”应用程序的数据库房间类。
 *
 * 实现了连接数据库中房间部分定义
 * 
 * @author  yangfan
 * @version 3.0
 */
package cn.edu.whut.sept.zuul.Mysql;

public class MySqlRoom {
	private int id;
	private int West, East, North, South;

	private String desc;

	public MySqlRoom() {
		id = -1;
	}

	public MySqlRoom(int Id, int w, int e, int n, int s, String Desc) {
		id = Id;
		West = w;
		East = e;
		North = n;
		South = s;
		desc = Desc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getWest() {
		return West;
	}

	public void setWest(int west) {
		West = west;
	}

	public int getEast() {
		return East;
	}

	public void setEast(int east) {
		East = east;
	}

	public int getNorth() {
		return North;
	}

	public void setNorth(int north) {
		North = north;
	}

	public int getSouth() {
		return South;
	}

	public void setSouth(int south) {
		South = south;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}

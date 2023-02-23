/**
 * 该类是“World-of-Zuul”应用程序的玩家类。
 *
 * 实现了玩家功能
 * 
 * @author  pengzhiyi
 * @version 2.1
 */
package cn.edu.whut.sept.zuul.Mains;

public class Player {
	private String username;
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private int bag;
	private int now_weight;
	private int pos;

	public void setName(String a) {
		this.username = a;
	}

	public String getName() {
		return username;
	}

	/**
	 * 计算物品重量和
	 */
	public boolean addweight(int x) {
		if (x > bag) {
			return false;
		}
		now_weight += x;
		bag -= x;
		return true;
	}

	public int getnowWeight() {
		return now_weight;
	}

	/**
	 * 构造函数
	 */
	public Player(int d, String a, int p, int b, int w) {
		id = d;
		username = a;
		pos = p;
		bag = b;
		now_weight = w;
	}

	public void setbag(int a) {
		this.bag = a;
	}

	public void addUp() {
		this.bag += 2;
	}

	public int getbag() {
		return bag;
	}

	public void setRoom(int i) {
		this.pos = i;
	}

	public int getRoom() {
		return this.pos;
	}

}

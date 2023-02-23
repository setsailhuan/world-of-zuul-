/**
 * 该类是“World-of-Zuul”应用程序的box类。
 *
 * 用于实现对物品的记录和基本读写操作
 * 
 * @author  yangfan
 * @version 2.1
 */

package cn.edu.whut.sept.zuul.Mains;

public class Box {

	private int id;
	private String boxname;
	private int weight;
	private String description;

	/**
	 * 构造函数
	 */
	public Box(int i, String a, int b, String c) {
		id = i;
		boxname = a;
		weight = b;
		description = c;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setName(String a) {
		boxname = a;
	}

	public void setWeight(int a) {
		weight = a;
	}

	public void setDesc(String a) {
		description = a;
	}

	public String getName() {
		return this.boxname;
	}

	public int getWeight() {
		return weight;
	}

	public String getDesc() {
		return this.description;
	}

	/**
	 * 打印物品信息
	 */
	public void print() {
		System.out.println(boxname + "\tweight-" + weight + "\t" + description + ".");
	}

}

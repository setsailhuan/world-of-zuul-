/**
 * 该类是“World-of-Zuul”应用程序的物品列表类。
 *
 * 用于实现对房间内的物品的记录和基本读写操作
 * 
 * @author  chenpang
 * @version 2.1
 */

package cn.edu.whut.sept.zuul.Mains;

import java.util.LinkedList;

public class Goods {
	private LinkedList<Box> list = new LinkedList<Box>();

	/**
	 * 获取房间内所有的物品的列表
	 */
	public LinkedList<Box> getAllBox() {
		return list;
	}

	/**
	 * 添加房间物品
	 */
	public void addBox(int id, String a, int b, String c) {
		list.add(new Box(id, a, b, c));
	}

	/**
	 * 获取房间内编号为i的物品
	 */
	public Box getBox(int i) {
		return list.get(i);
	}

	/**
	 * 将一个物品对象添加到列表中
	 */
	public void addtolist(Box a) {
		list.add(a);
	}

	/**
	 * 将编号i的物品从列表中删除
	 */
	public void dropBox(int i) {
		list.remove(i);
	}

	/**
	 * 判断编号i的物品是否存在
	 */
	public boolean isBoxExits(int i) {
		return list.size() < i && i >= 0;
	}

	/**
	 * 显示房间物品
	 */
	public void showAllBox() {
		// System.out.println("there are "+list.size()+":");
		if (list.size() == 0) {
			System.out.println("none");
		}
		for (int i = 0; i < list.size(); i++) {
			System.out.print(i + " : ");
			list.get(i).print();
		}
	}

}

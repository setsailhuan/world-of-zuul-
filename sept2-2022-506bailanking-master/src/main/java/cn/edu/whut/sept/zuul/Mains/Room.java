/**
 * 该类是“World-of-Zuul”应用程序的房间类。
 *
 * 实现了房间的定义与初始化
 * 
 * @author  yangfan
 * @version 1.1
 */
package cn.edu.whut.sept.zuul.Mains;

import java.util.Set;
import java.util.HashMap;
import java.util.LinkedList;

public class Room {
	private String description;
	private HashMap<String, Room> exits; // stores exits of this room.
	private int pos;

	public Room(String description, int p) {
		this.description = description;
		pos = p;
		exits = new HashMap<>();
	}

	public int getPos() {
		return pos;
	}

	public void setExit(String direction, Room neighbor) {
		exits.put(direction, neighbor);
	}

	public String getShortDescription() {
		return description;
	}

	public String getLongDescription() {
		return "You are " + description + ".\n" + getExitString();
	}

	public String getExitString() {
		String returnString = "Exits:";
		Set<String> keys = exits.keySet();
		for (String exit : keys) {
			returnString += " " + exit;
		}
		return returnString;
	}

	public Room getExit(String direction) {
		return exits.get(direction);
	}

}

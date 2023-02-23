/**
 * 该类是“World-of-Zuul”应用程序的命令类。
 *
 * 用于实现基本的命令的功能
 * 
 * @author  chenpang
 * @version 1.0
 */
package cn.edu.whut.sept.zuul.Comm;

import cn.edu.whut.sept.zuul.Mains.Game;

public abstract class Command {
	private String secondWord;

	/**
	 * 构造函数
	 */
	public Command() {
		secondWord = null;
	}

	/**
	 * 返回命令的第二个文字段
	 */
	public String getSecondWord() {
		return secondWord;
	}

	/**
	 * 判断是否有第二个文字段
	 */
	public boolean hasSecondWord() {
		return secondWord != null;
	}

	/**
	 * 设置第二个问字段
	 */
	public void setSecondWord(String secondWord) {
		this.secondWord = secondWord;
	}

	public abstract boolean execute(Game game);
}

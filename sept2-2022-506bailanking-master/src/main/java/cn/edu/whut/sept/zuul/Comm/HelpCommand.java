/**
 * 该类是“World-of-Zuul”应用程序的help命令的类。
 *
 * 用于help命令的显示与响应
 * 
 * @author  pengzhiyi
 * @version 1.1
 */
package cn.edu.whut.sept.zuul.Comm;

import cn.edu.whut.sept.zuul.Mains.Game;

public class HelpCommand extends Command {
	private CommandWords commandWords;

	public HelpCommand(CommandWords words) {
		commandWords = words;
	}

	public boolean execute(Game game) {
		System.out.println("You are lost. You are alone. You wander");
		System.out.println("around at the university.");
		System.out.println();
		System.out.println("Your command words are:");
		commandWords.showAll();
		return false;
	}
}

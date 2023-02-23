/**
 * 该类是“World-of-Zuul”应用程序的back命令类。
 *
 * 实现了回退到上一个房间的功能
 * 
 * @author  yangfan
 * @version 2.1
 */
package cn.edu.whut.sept.zuul.Comm;

import cn.edu.whut.sept.zuul.Mains.Game;

public class BackCommand extends Command {

	@Override
	public boolean execute(Game game) {

		if (game.Roadpop()) {
			System.out.println("you are back to where you start!");
		} else {
			System.out.println(game.getCurrentRoom().getLongDescription());
		}

		return false;
	}

}

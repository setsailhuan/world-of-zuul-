/**
 * 该类是“World-of-Zuul”应用程序的quit命令类。
 *
 * 实现了quit功能
 * 
 * @author  chenpang
 * @version 1.1
 */
package cn.edu.whut.sept.zuul.Comm;

import cn.edu.whut.sept.zuul.Mains.Game;

public class QuitCommand extends Command {
	public boolean execute(Game game) {
		if (hasSecondWord()) {
			System.out.println("Quit what?");
			return false;
		} else {
			return true; // signal that we want to quit
		}
	}
}

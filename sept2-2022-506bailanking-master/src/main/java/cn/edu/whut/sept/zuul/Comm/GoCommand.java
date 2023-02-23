/**
 * 该类是“World-of-Zuul”应用程序的go命令的类。
 *
 * 实现了go命令
 * 
 * @author  chenpang
 * @version 1.1
 */
package cn.edu.whut.sept.zuul.Comm;

import java.util.Calendar;

import cn.edu.whut.sept.zuul.Mains.Game;
import cn.edu.whut.sept.zuul.Mains.Room;

public class GoCommand extends Command {
	public boolean execute(Game game) {
		if (!hasSecondWord()) {
			System.out.println("Go where?");
		}

		String direction = getSecondWord();

		Room nextRoom = game.getCurrentRoom().getExit(direction);

		if (nextRoom == null) {
			System.out.println("There is no door!");
		} else {
			if (nextRoom.getPos() == 0) {
				int pos = (int) (System.currentTimeMillis() % 5) + 1;
				nextRoom = game.getIndexRoom(pos);
			}
			game.RoadAdd(nextRoom);
			game.setCurrentRoom(nextRoom);
			game.setplayerpos(nextRoom.getPos());
			System.out.println(nextRoom.getLongDescription());
		}

		return false;
	}
}

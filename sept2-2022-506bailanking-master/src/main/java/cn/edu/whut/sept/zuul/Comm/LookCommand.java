/**
 * 该类是“World-of-Zuul”应用程序的look命令类。
 *
 * @author  pengzhiyi
 * @version 1.1
 */
package cn.edu.whut.sept.zuul.Comm;

import cn.edu.whut.sept.zuul.Mains.Game;
import cn.edu.whut.sept.zuul.Mains.Goods;

public class LookCommand extends Command {

	@Override
	public boolean execute(Game game) {

		// System.out.println("nihao");

		Goods goods = game.getCurrentGoods();

		goods.showAllBox();

		return false;
	}

}

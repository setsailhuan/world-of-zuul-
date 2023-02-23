/**
 * 该类是“World-of-Zuul”应用程序的放置物品的类。
 *
 * 实现了drop命令
 * 
 * @author  pengzhiyi
 * @version 1.4
 */
package cn.edu.whut.sept.zuul.Comm;

import cn.edu.whut.sept.zuul.Mains.Box;
import cn.edu.whut.sept.zuul.Mains.Game;
import cn.edu.whut.sept.zuul.Mains.Goods;

public class DropCommand extends Command {

	@Override
	public boolean execute(Game game) {

		Goods goods = game.getCurrentGoods();
		Goods pgoods = game.getpgoods();
		System.out.println("\nBefore your operation, all boxes in bag:");
		for (int i = 0; i < 55; i++) {
			System.out.print('-');
		}
		System.out.print('\n');
		pgoods.showAllBox();
		for (int i = 0; i < 55; i++) {
			System.out.print('-');
		}
		System.out.print('\n');
		System.out.println("all boxes in room:");
		goods.showAllBox();
		for (int i = 0; i < 55; i++) {
			System.out.print('-');
		}
		System.out.print('\n');

		int pos = game.getTablePos();
		if (pos == -1) {
			pos = Integer.parseInt(getSecondWord());
			if (pos >= pgoods.getAllBox().size() || pos < 0) {
				System.out.println("wrong drop number" + pos);
				return false;
			}
		}

		Box box = pgoods.getBox(pos);
		goods.addtolist(box);
		pgoods.dropBox(pos);

		game.asweight(-box.getWeight());

		game.updateCuttrntGoods(goods);
		game.updatePgoods(pgoods);
		for (int i = 0; i < 55; i++) {
			System.out.print('*');
		}
		System.out.print('\n');
		System.out.println("After your operation, all boxes in bag:");
		pgoods.showAllBox();
		for (int i = 0; i < 55; i++) {
			System.out.print('*');
		}
		System.out.print('\n');
		System.out.println("all boxes in room:");
		goods.showAllBox();
		for (int i = 0; i < 55; i++) {
			System.out.print('*');
		}
		System.out.print('\n');
		return false;
	}

}

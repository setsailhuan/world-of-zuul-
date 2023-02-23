/**
 * 该类是“World-of-Zuul”应用程序的拿起物品的类。
 *
 * 实现了take命令
 * 
 * @author  pengzhiyi
 * @version 1.4
 */
package cn.edu.whut.sept.zuul.Comm;

import cn.edu.whut.sept.zuul.Mains.Box;
import cn.edu.whut.sept.zuul.Mains.Game;
import cn.edu.whut.sept.zuul.Mains.Goods;

public class TakeCommand extends Command {

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
			if (pos + 1 > goods.getAllBox().size() || pos < 0) {
				System.out.println("wrong take number");
				return false;
			}
		}
		Box box = goods.getBox(pos);
		int w = box.getWeight();
		if (w == 0) {
			System.out.println("you eat this cookie the bag will increase\n");
			goods.dropBox(pos);
			game.updateCuttrntGoods(goods);
			if (pos != -1) {
				game.player.addUp();
			}
		} else if (game.asweight(w)) {
			pgoods.addtolist(box);
			goods.dropBox(pos);
			game.updateCuttrntGoods(goods);
			game.updatePgoods(pgoods);
		} else {
			System.out.println("\ntoo heavy to carry it!!\n");
		}

		System.out.println("\nAfter your operation, all boxes in bag:");
		for (int i = 0; i < 55; i++) {
			System.out.print('*');
		}
		System.out.print('\n');
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

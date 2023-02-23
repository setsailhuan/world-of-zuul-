/**
 * 该类是“World-of-Zuul”应用程序的mysql的测试类。
 *
 * 实现了对mysql的测试
 * 
 * @author  chenpang
 * @version 3.3
 */
package cn.edu.whut.sept.zuul.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cn.edu.whut.sept.zuul.Comm.CommandWords;
import cn.edu.whut.sept.zuul.Mains.Game;
import cn.edu.whut.sept.zuul.Mains.Goods;

class MySQlTest {

	private static CommandWords commands = new CommandWords();
	private static Game game = new Game();

	@BeforeEach
	void setUp() throws Exception {
		game.CreateRoomFromMySql(100, (String) "player");
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testPlayer() {
		int pos = game.player.getRoom();
		int bg = game.player.getbag();
		assertEquals(pos, 1);
		assertEquals(bg, 5);
	}

	@Test
	void testGoods() {
		boolean f = false;

		Goods g = game.getCurrentGoods();

		int sz = g.getAllBox().size();

		String nm = "wallet";

		for (int i = 0; i < sz; i++) {
			if (g.getBox(i).getId() == 15) {

				f = g.getBox(i).getName().equals(nm);
				break;
			}
		}
		assertEquals(true, f);
	}

	@Test
	void testRoom() {
		int pos = game.player.getRoom();
		int rp = game.getCurrentRoom().getPos();
		assertEquals(pos, rp);
	}

}

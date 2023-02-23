/**
 * 该类是“World-of-Zuul”应用程序的take&drop命令类的测试类。
 *
 * 实现了take&drop命令的测试
 * 
 * @author  chenpang
 * @version 3.3
 */
package cn.edu.whut.sept.zuul.Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cn.edu.whut.sept.zuul.Comm.Command;
import cn.edu.whut.sept.zuul.Comm.CommandWords;
import cn.edu.whut.sept.zuul.Mains.Game;

public class TakeAndDropTest {

	private static CommandWords commands = new CommandWords();
	private static Game game = new Game();

	@Before
	public void setUp() throws Exception {
		game.createRooms();
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testTakeFull() {
		Command command = commands.get((String) "take");
		command.setSecondWord((String) "1");
		int ans = game.player.getnowWeight();
		command.execute(game);
		assertEquals(ans, game.player.getnowWeight());
	}

	@Test
	public void testTake() {
		Command command = commands.get((String) "take");
		command.setSecondWord((String) "0");
		int ans = game.getCurrentGoods().getBox(0).getWeight();
		command.execute(game);
		assertEquals(ans, game.player.getnowWeight());
	}

	@Test
	public void testTakeCookie() {
		Command command = commands.get((String) "take");
		command.setSecondWord((String) "2");
		command.execute(game);
		assertEquals(7, game.player.getbag());
	}

	@Test
	public void testDrop() {
		Command command = commands.get((String) "take");
		command.setSecondWord((String) "0");
		command.execute(game);
		int x = game.getpgoods().getBox(0).getId();
		command = commands.get((String) "drop");
		command.setSecondWord((String) "0");
		command.execute(game);
		assertEquals(x, game.getCurrentGoods().getBox(2).getId());
	}

}

/**
 * 该类是“World-of-Zuul”应用程序的go&back命令类的测试类。
 *
 * 实现了go&back命令的测试
 * 
 * @author  chenpang
 * @version 3.3
 */
package cn.edu.whut.sept.zuul.Test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cn.edu.whut.sept.zuul.Comm.Command;
import cn.edu.whut.sept.zuul.Comm.CommandWords;
import cn.edu.whut.sept.zuul.Mains.Game;

class GoAndBackTest {
	private static CommandWords commands = new CommandWords();
	private static Game game = new Game();

	@BeforeEach
	void setUp() throws Exception {
		game.createRooms();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGoWset() {
		Command command = commands.get((String) "go");
		command.setSecondWord((String) "west");
		command.execute(game);
		assertEquals(3, game.getCurrentRoom().getPos());
	}

	@Test
	void testGoEast() {
		Command command = commands.get((String) "go");
		command.setSecondWord((String) "east");
		command.execute(game);
		assertEquals(2, game.getCurrentRoom().getPos());
	}

	@Test
	void testGoSouth() {
		Command command = commands.get((String) "go");
		command.setSecondWord((String) "south");
		command.execute(game);
		assertEquals(4, game.getCurrentRoom().getPos());
	}

	@Test
	void testGoNorth() {
		Command command = commands.get((String) "go");
		command.setSecondWord((String) "south");
		command.execute(game);
		command.setSecondWord((String) "north");
		command.execute(game);
		assertEquals(1, game.getCurrentRoom().getPos());
	}

	@Test
	void testBack() {
		int a1, a2;
		a1 = game.getCurrentRoom().getPos();

		Command command = commands.get((String) "go");
		command.setSecondWord((String) "south");
		command.execute(game);

		a2 = game.getCurrentRoom().getPos();
		command.setSecondWord((String) "east");
		command.execute(game);

		command = commands.get((String) "back");
		command.execute(game);
		assertEquals(a2, game.getCurrentRoom().getPos());

		command = commands.get((String) "back");
		command.execute(game);
		assertEquals(a1, game.getCurrentRoom().getPos());
	}

}

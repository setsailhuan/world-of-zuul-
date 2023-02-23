/**
 * 该类是“World-of-Zuul”应用程序的命令的文字类。
 *
 * 实现了对输入命令的hash构造
 * 
 * @author  chenpang
 * @version 1.1
 */
package cn.edu.whut.sept.zuul.Comm;

import java.util.HashMap;
import java.util.Iterator;

public class CommandWords {
	private HashMap<String, Command> commands;

	public CommandWords() {
		commands = new HashMap<String, Command>();
		commands.put("go", new GoCommand());
		commands.put("help", new HelpCommand(this));
		commands.put("quit", new QuitCommand());
		commands.put("look", new LookCommand());
		commands.put("back", new BackCommand());
		commands.put("drop", new DropCommand());
		commands.put("take", new TakeCommand());
	}

	public Command get(String word) {
		return (Command) commands.get(word);
	}

	public void showAll() {
		for (Iterator i = commands.keySet().iterator(); i.hasNext();) {
			System.out.print(i.next() + "  ");
		}
		System.out.println();
	}
}

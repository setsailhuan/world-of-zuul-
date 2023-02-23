/**
 * 该类是“World-of-Zuul”应用程序的表格相关的类。
 *
 * 用于表格的显示与响应
 * 
 * @author  chenpang
 * @version 1.1
 */
package cn.edu.whut.sept.zuul.Ui;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import cn.edu.whut.sept.zuul.Mains.Box;
import cn.edu.whut.sept.zuul.Mains.Goods;

/**
 * 函数：表格列表 功能：显示列表
 */
public class JTableDemo extends AbstractTableModel {
	/**
	 * 用来存放表格数据的线性表
	 */
	private Vector TableData;
	String[][] TableDataArray;
	/**
	 * 表格的列标题
	 */
	private Vector TableColumn;
	private int effectnum;
	/**
	 * 显示列名 String[] columnNames= {"列名1","列名2","列名3"};
	 */
	public JTableDemo() {
		TableData = new Vector();

		TableColumn = new Vector();
		TableColumn.add("ID");
		TableColumn.add("name");
		TableColumn.add("weight");
		TableColumn.add("Description");
	}
	/**
	 * 返回表的行数，返回TableData上挂的String数组个数
	 */
	@Override
	public int getRowCount() {

		return TableData.size();
	}

	public void setit(Goods goods) {
		LinkedList<Box> list = goods.getAllBox();
		TableData.removeAllElements();

		for (int i = 0; i < list.size(); i++) {
			Box box = list.get(i);
			String[] line = {String.valueOf(i), box.getName(),
					String.valueOf(box.getWeight()), box.getDesc()};
			TableData.add(line);
		}
		effectnum = list.size() - 1;
		for (int j = effectnum; j < 5; j++) {
			String[] line = {" ", " ", " ", " "};
			TableData.add(line);
		}

	}
	/**
	 * 返回表的列数，直接返回TableTitle.size()
	 */
	@Override
	public int getColumnCount() {

		return TableColumn.size();
	}
	/**
	 * 获取相应坐标位置的值，分下面两步 
	 * 获取每一行对应的String[]数组 提取出对 应的数据
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		String LineTemp[] = (String[]) this.TableData.get(rowIndex);
		return LineTemp[columnIndex];
	}
	/**
	 * 打印物品信息
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	/**
	 * 当单元格的数据发生改变的时候调用该函数重设单元格的数据
	 * 数据是放在TableData中,修改数据就是修改的
	 * TableData中的数据，所以我们仅仅在此处将TableData的对应数据修改即可
	 * 然后调用函数fireTableCellUpdated(int row,int col);
	 * 更新一下表格对应位置的数据显示即可完成对表格的数据修改；
	 */
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		((String[]) this.TableData
				.get(rowIndex))[columnIndex] = (String) aValue;
		this.fireTableCellUpdated(rowIndex, columnIndex);
	}
	/**
	 * 不加这一个方法列名是ABC读不进来
	 */
	public String getColumnName(int name) {
		return (String) TableColumn.get(name);

	}

}

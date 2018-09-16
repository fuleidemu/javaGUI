package com.zhaoyingbin.mywindow;

import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;

public class MyMenu {

	private Frame frame;
	private MenuBar mb;
	private Menu m, submenu, m1;
	private MenuItem closeItem, openItem, saveItem;
	private FileDialog openDia, saveDia;
	private TextArea tArea;
	private File file;

	public MyMenu() {
		init();

	}

	private void init() {
		// TODO Auto-generated method stub
		frame = new Frame();
		frame.setBounds(200, 300, 600, 600);
		// frame.setLayout(new FlowLayout());
		mb = new MenuBar();
		m = new Menu("文件");
		m1 = new Menu("编辑");
		submenu = new Menu("文件下的子菜单");
		closeItem = new MenuItem("退出");
		openItem = new MenuItem("打开");
		saveItem = new MenuItem("保存");
		tArea = new TextArea();

		// 文件菜单下内容
		m.add(submenu);
		m.add(closeItem);
		m.add(openItem);
		m.add(saveItem);

		submenu.add(closeItem);

		mb.add(m);
		mb.add(m1);
		frame.setMenuBar(mb);
		openDia = new FileDialog(frame, "打开", FileDialog.LOAD);
		saveDia = new FileDialog(frame, "保存", FileDialog.SAVE);

		frame.add(tArea);
		myEvent();
		frame.setVisible(true);

	}

	private void myEvent() {
		saveItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (file == null) {
					saveDia.setVisible(true);
					String dirPath = saveDia.getDirectory();
					String fileName = saveDia.getFile();
					if (dirPath == null || fileName == null) {
						return;

					}
					file = new File(dirPath, fileName);
				}
				BufferedWriter bufferedWriter = null;
				try {
					bufferedWriter = new BufferedWriter(new FileWriter(file));

					String string = tArea.getText();

					bufferedWriter.write(string);

					bufferedWriter.flush();

					bufferedWriter.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});
		openItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				openDia.setVisible(true);
				String dirPath = openDia.getDirectory();
				String fileName = openDia.getFile();
				if (dirPath == null || fileName == null) {
					return;
				}
				tArea.setText("");
				file = new File(dirPath, fileName);
				try {
					BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
					String line = null;
					while ((line = bufferedReader.readLine()) != null) {
						tArea.append(line);
					}

				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		closeItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});

	}
}

package com.zhaoyingbin.mywindow;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class Welcom {

	private Button but;
	private Frame f;
	private TextField tf;
	private TextArea ta;
	private Dialog d;
	private Label lab;
	private Button okBut;

	public Welcom() {
		init(); // TODO Auto-generated constructor stub
	}

	private void init() {
		f = new Frame();
		f.setBounds(200, 300, 600, 600);
		f.setLayout(new FlowLayout());
		tf = new TextField(60);
		but = new Button("转到");
		ta = new TextArea(25, 70);
		d = new Dialog(f, "提示信息", true);
		d.setBounds(200, 200, 500, 500);
		d.setLayout(new FlowLayout());
		lab = new Label();
		okBut = new Button("确定");
		d.add(lab);
		d.add(okBut);
		// d本身是一个窗体,所以不能加载frame中
		f.add(tf);
		f.add(but);
		f.add(ta);
		myEvent();
		f.setVisible(true);
	}

	private void myEvent() {
		okBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				d.setVisible(false);
			}
		});
		okBut.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					d.setVisible(false);
				}
			}
		});
		d.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				d.setVisible(false);
				System.exit(0);

			}

		});

		but.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showDir();
			}
		});
		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		tf.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent event) {
				if (event.getKeyCode() == KeyEvent.VK_ENTER) {
					showDir();

				}
			}

		});

	}

	private void showDir() {
		String dirPath = tf.getText();
		File dir = new File(dirPath);
		if (dir.exists() && dir.isDirectory()) {
			ta.setText("");
			String[] name = dir.list();
			for (String string : name) {
				System.out.println(string);
				ta.append(string + "\r\n");
			}
		} else {
			String info = "路径不存在";
			lab.setText(info);
			d.setVisible(true);
		}
	}
}

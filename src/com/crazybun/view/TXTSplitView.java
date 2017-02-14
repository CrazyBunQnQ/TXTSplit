package com.crazybun.view;

import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.crazybun.utils.TxtUtil;

public class TXTSplitView {
	private static String path;
	private static String fileType;
	private static int count = 5;

	public static void main(String[] args) {
		init();
	}

	private static void init() {
		JFrame fpath = new JFrame("大TXT文件分割");
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		ActionListener btnFileAL;
		ActionListener btnStartAL;
		DocumentListener numTL;
		JLabel tip1 = new JLabel("待分割的TXT文件路径：");// 标签
		JLabel jpath = new JLabel("请选择要分割的TXT文件路径...");
		JButton btnFile = new JButton("浏览");
		JLabel tip_directory = new JLabel("TXT保存位置：");
		JLabel tip_count = new JLabel("分割数量：");
		JTextField num = new JTextField("5");
		JLabel directory = new JLabel("");
		JButton btnStart = new JButton("开始");

		btnFileAL = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FileDialog fd = new FileDialog(new Frame(), "请选择要分割的txt文件", FileDialog.LOAD);
				fd.setVisible(true);
				path = fd.getDirectory() + fd.getFile();
				fileType = path.substring(path.length() - 3, path.length());
				if (fileType.toLowerCase().equals("txt")) {
					jpath.setText(path);
					btnStart.setEnabled(true);
				} else {
					jpath.setText("请选择.txt格式的文件！");
					btnStart.setEnabled(false);
				}
				directory.setText(fd.getDirectory());
			}
		};

		numTL = new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				count = Integer.valueOf(num.getText());
				System.out.println(count);
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				count = Integer.valueOf(num.getText());
				System.out.println(count);
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				count = Integer.valueOf(num.getText());
				System.out.println(count);
			}
		};
				

		btnStartAL = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TxtUtil.splitTxt(path, count);
			}
		};

		fpath.setFont(new Font("楷体", Font.PLAIN, 24));
		fpath.setLayout(gridbag);

		// 对第一个控件进行约束
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 2.0;// 设置水平方向分布的权重
		c.gridwidth = 2;
		c.gridheight = 1;
		c.insets = new Insets(20, 20, 0, 0);// 设置与上左下右组件间的边距
		// tip1.setSize(100, 30);
		gridbag.setConstraints(tip1, c);
		fpath.add(tip1);

		// 对第二个控件进行约束
		c.weightx = 6.0;
		c.insets = new Insets(20, 20, 0, 0);// 设置与上下左右组件间的边距
		gridbag.setConstraints(jpath, c);
		fpath.add(jpath);

		// 对第三个控件进行约束
		c.weightx = 1.0;
		c.insets = new Insets(20, 20, 0, 20);
		c.gridwidth = GridBagConstraints.REMAINDER;
		btnFile.addActionListener(btnFileAL);
		gridbag.setConstraints(btnFile, c);
		fpath.add(btnFile);

		// 重置权重，另起一行
		// 对第二行第一个控件进行约束
		c.weightx = 2.0;
		c.insets = new Insets(20, 20, 0, 0);
		gridbag.setConstraints(tip_directory, c);
		fpath.add(tip_directory);

		// 对第二行第二个控件进行约束
		c.weightx = 7.0;
		c.insets = new Insets(20, 20, 0, 20);
		c.gridwidth = GridBagConstraints.REMAINDER;
		gridbag.setConstraints(directory, c);
		fpath.add(directory);
		
		c.weightx = 7.0;
		c.insets = new Insets(20, 20, 0, 20);
		c.gridwidth = GridBagConstraints.REMAINDER;
		gridbag.setConstraints(tip_count, c);
		fpath.add(tip_count);
		
		c.weightx = 7.0;
		c.insets = new Insets(20, 20, 0, 20);
		c.gridwidth = GridBagConstraints.REMAINDER;
		num.getDocument().addDocumentListener(numTL);;
		gridbag.setConstraints(num, c);
		fpath.add(num);

		// 另起一行对该控件进行约束
		c.weightx = 0;
		c.insets = new Insets(0, 200, 20, 200);
		btnStart.addActionListener(btnStartAL);
		btnStart.setEnabled(false);
		gridbag.setConstraints(btnStart, c);
		fpath.add(btnStart);

		fpath.setVisible(true);
		fpath.setSize(600, 200);
	}
}
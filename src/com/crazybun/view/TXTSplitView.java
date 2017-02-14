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
		JFrame fpath = new JFrame("��TXT�ļ��ָ�");
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		ActionListener btnFileAL;
		ActionListener btnStartAL;
		DocumentListener numTL;
		JLabel tip1 = new JLabel("���ָ��TXT�ļ�·����");// ��ǩ
		JLabel jpath = new JLabel("��ѡ��Ҫ�ָ��TXT�ļ�·��...");
		JButton btnFile = new JButton("���");
		JLabel tip_directory = new JLabel("TXT����λ�ã�");
		JLabel tip_count = new JLabel("�ָ�������");
		JTextField num = new JTextField("5");
		JLabel directory = new JLabel("");
		JButton btnStart = new JButton("��ʼ");

		btnFileAL = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FileDialog fd = new FileDialog(new Frame(), "��ѡ��Ҫ�ָ��txt�ļ�", FileDialog.LOAD);
				fd.setVisible(true);
				path = fd.getDirectory() + fd.getFile();
				fileType = path.substring(path.length() - 3, path.length());
				if (fileType.toLowerCase().equals("txt")) {
					jpath.setText(path);
					btnStart.setEnabled(true);
				} else {
					jpath.setText("��ѡ��.txt��ʽ���ļ���");
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

		fpath.setFont(new Font("����", Font.PLAIN, 24));
		fpath.setLayout(gridbag);

		// �Ե�һ���ؼ�����Լ��
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 2.0;// ����ˮƽ����ֲ���Ȩ��
		c.gridwidth = 2;
		c.gridheight = 1;
		c.insets = new Insets(20, 20, 0, 0);// �������������������ı߾�
		// tip1.setSize(100, 30);
		gridbag.setConstraints(tip1, c);
		fpath.add(tip1);

		// �Եڶ����ؼ�����Լ��
		c.weightx = 6.0;
		c.insets = new Insets(20, 20, 0, 0);// �������������������ı߾�
		gridbag.setConstraints(jpath, c);
		fpath.add(jpath);

		// �Ե������ؼ�����Լ��
		c.weightx = 1.0;
		c.insets = new Insets(20, 20, 0, 20);
		c.gridwidth = GridBagConstraints.REMAINDER;
		btnFile.addActionListener(btnFileAL);
		gridbag.setConstraints(btnFile, c);
		fpath.add(btnFile);

		// ����Ȩ�أ�����һ��
		// �Եڶ��е�һ���ؼ�����Լ��
		c.weightx = 2.0;
		c.insets = new Insets(20, 20, 0, 0);
		gridbag.setConstraints(tip_directory, c);
		fpath.add(tip_directory);

		// �Եڶ��еڶ����ؼ�����Լ��
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

		// ����һ�жԸÿؼ�����Լ��
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
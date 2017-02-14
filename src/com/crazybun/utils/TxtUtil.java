package com.crazybun.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TxtUtil {
	/**
	 * ƽ���ָ�һ��TXT�ļ���ע�⣺������е�˳�򣡣�
	 * @param path �ļ�·��
	 * @param fname TXT����
	 * @param count ��Ϊ����
	 */
	public static void splitTxt(String path, String fname, int count) {
		try {
			FileReader read = new FileReader(path + fname);
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(read);
			String row;
			List<FileWriter> flist = new ArrayList<FileWriter>();
			for (int i = 0; i < count; i++) {
				flist.add(new FileWriter(path + "text" + i + ".txt"));
			}
			int rownum = 1;
			while ((row = br.readLine()) != null) {
				flist.get(rownum % count).append(row + "\r\n");
				rownum++;
			}
			for (int i = 0; i < flist.size(); i++) {
				flist.get(i).close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void splitTxt(String fullPath, int count) {
		int index = fullPath.indexOf("\\");
		String path = fullPath.substring(0, index);
		String fname = fullPath.substring(index);
		splitTxt(path, fname, count);
	}
}
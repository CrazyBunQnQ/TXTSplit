package com.crazybun.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TxtUtil {
    /**
     * 平均分割一个TXT文件（注意：会打乱行的顺序！）
     *
     * @param path  文件路径
     * @param fname TXT名称
     * @param count 分为几份
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
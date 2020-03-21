package xinguanbingdu.compare;

/**
 * 链接文件去重
 */

import xinguanbingdu.tool.Help;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeleteTheSameLink {
    private String path = "E:\\冯老师\\链接\\links.txt";
    public List<String> list;
    private int num = 1;

    public static void main(String[] args) {
        DeleteTheSameLink deleteTheSameLink = new DeleteTheSameLink();
        try {
            deleteTheSameLink.readFile();
        } catch (IOException e) {
            System.out.println("文件未找到！");
        }
        //去重
        deleteTheSameLink.deleteTheSame();
        //重写文件
        deleteTheSameLink.reWrite2File();

    }

    // 读取文件，将文件的link存在list中，做去重准备
    public void readFile() throws IOException {
        FileReader fr = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fr);
        String str = null;
        list = new ArrayList<>();
        while (true) {
            str = bufferedReader.readLine();
            if (str != null) {
                str = str.substring(str.indexOf('h'));
                System.out.println(str);
                list.add(str);
            } else {
                break;
            }
        }
        bufferedReader.close();
        fr.close();
    }

    //去重
    public void deleteTheSame() {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).equals(list.get(j)))
                    list.remove(j);
            }
        }
        // 删除原文件
        boolean delete = new File(path).delete();
        System.out.println("删除原文件" + delete);
    }

    // 从list写入文件，重写文件
    public void reWrite2File() {
        for (String s : list) {
            s = num + "\t" + s;
            try {
                Help.wirte2File(s, path, true);
                System.out.println("写入" + num + "行");
                num++;
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("重写出错！");
            }
        }
    }

}

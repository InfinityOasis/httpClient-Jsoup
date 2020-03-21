package xinguanbingdu.compare;

/**
 * 链接文件去重
 */

import xinguanbingdu.tool.Help;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DeleteTheSameLink {
    private static String path = "E:\\冯老师\\链接\\links.txt";

    private int num = 1;

    public static void main(String[] args) {
        List<String> list = null;
        DeleteTheSameLink deleteTheSameLink = new DeleteTheSameLink();
        try {
            list = Help.readFile(path,list);
        } catch (IOException e) {
            System.out.println("文件未找到！");
        }
        //去重
        deleteTheSameLink.deleteTheSame(list);
        //重写文件
        deleteTheSameLink.reWrite2File(list);

    }



    //去重
    public void deleteTheSame(List<String> list) {
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
    public void reWrite2File(List<String> list) {
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

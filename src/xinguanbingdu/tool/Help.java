package xinguanbingdu.tool;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Help {

    /**
     * 写入文件
     * @param str 要写的字符串
     * @param path
     * @param flag 附加或重写
     * @throws IOException
     */
    public static void wirte2File(String str, String path, boolean flag) throws IOException {
        File file = new File(path);
         if (!file.exists())
            file.createNewFile();
        FileWriter fileWriter = new FileWriter(path, flag);
        if (str != null)
            fileWriter.write(str + "\n");
        fileWriter.close();
    }

    /**
     * 重载方法，目的是用加上编码设置 UTF-8
     * @param str
     * @param path
     * @throws IOException
     */
    public static void wirte2File(String str, String path) throws IOException {
        File file = new File(path);
        if (!file.exists())
            file.createNewFile();
        BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
        if (str != null)
            fileWriter.write(str + "\n");
        fileWriter.flush();
        fileWriter.close();
    }
    // 读取文件，将文件的link存在list中，做去重准备
    public static List<String> readFile(String path, List<String> list) throws IOException {
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
        return list;
    }

}

package xinguanbingdu.compare;

import xinguanbingdu.tool.Help;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CompareWith {
    private String path0 = "E:\\冯老师\\症状\\原症状\\症状列表.txt",
            path1 = "E:\\冯老师\\症状\\匹配结果.txt";

    public static void main(String[] args) {
        CompareWith compareWith = new CompareWith();
        try {
            compareWith.compareWithList();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("compareWithList IOException !");
        }
    }

    public void compareWithList() throws IOException {
        File file0 = new File(path0),
                file1 = new File(path1);
        getFile(file0);
        getFile(file1);
        //读取列表文件
        FileReader fileReader = new FileReader(file0);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        int n = 1;  // 第n个短语
        while (true) {
            String s = bufferedReader.readLine();
            //循环读取文本文件，获得短语出现次数
            if (s == null)
                break;
            int appear = 0;
            System.out.println("比较第" + n + "个短语：" + s);
            for (int num = 1; num <= 497; num++) {
                String text = "";
                String fpath = "E:\\冯老师\\文本\\" + num + ".txt";
                System.out.print("读取第" + num + "个文件------");
                //判断文件是否为空，空直接循环到下个文件
                if (new DeleteTheNullFile().isNull(new File(fpath)))
                    System.out.println("为null文件，跳过！目前出现次数为 " + appear + " 次");
                else {
                    try {
                        text = getText(fpath);
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("未找到文件");
                    }
                    int i = appearNumber(text, s);
                    appear += i;
                    System.out.println("出现次数" + i + " ; 目前总出现次数" + appear);
                }
            }
            System.out.println("------------");
            //写入次数
            Help.wirte2File("出现 " + appear + " 次\t\t\t\t" + s, "E:\\冯老师\\症状\\匹配结果.txt", true);
            n++;
        }
        bufferedReader.close();
        fileReader.close();
    }

    /**
     * 获取指定字符串出现的次数
     *
     * @param srcText  源字符串
     * @param findText 要查找的字符串
     * @return
     */
    public static int appearNumber(String srcText, String findText) {
        int count = 0;
        Pattern p = Pattern.compile(findText);
        Matcher m = p.matcher(srcText);
        while (m.find()) {
            count++;
        }
        return count;
    }

    // File文件处理
    public void getFile(File file) throws IOException {
        if (!file.exists())
            file.createNewFile();
    }

    //读取文本文件，返回一个text String值
    public String getText(String path) throws IOException {
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String s = "";
        while (true) {
            String s1 = bufferedReader.readLine();
            s += s1;
            if (s1 == null)
                break;
        }
        System.out.println("文本内容 ：" + s);
        bufferedReader.close();
        fileReader.close();
        return s;
    }

}

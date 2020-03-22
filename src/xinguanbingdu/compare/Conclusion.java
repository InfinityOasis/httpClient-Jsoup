package xinguanbingdu.compare;

import xinguanbingdu.tool.Help;

import java.io.*;

public class Conclusion {
    public static void main(String[] args) {
        String path0 = "E:\\冯老师\\症状\\匹配结果.txt",
                path1 = "E:\\冯老师\\症状\\匹配结果-小于20次数.txt",
                path2 = "E:\\冯老师\\症状\\匹配结果-大于20次数.txt";
        try {
            new Conclusion().conclusionTheFiile(new File(path0), path1, path2);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("False!");
        }
    }

    /**
     * 识别出现非 0 次数
     *
     * @param file  识别目标文件
     * @param path  生成的非0次数文件路径
     * @param path1 生成的大于20次的文件路径
     * @throws IOException
     */
    //将次数为 0 的识别剔除，并将非0次数新生成一个文件
    public void conclusionTheFiile(File file, String path, String path1) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String s = "";
        int n = 1;
        while (true) {
            s = bufferedReader.readLine();
            if (s == null)
                break;
            if (s.charAt(3) == '0')
                System.out.println("第" + n + "个  " + s + "  出现0次，剔除");
            else if ((s.charAt(3) >= '2' && s.charAt(4) != ' ') || (s.charAt(3) == '1' && s.charAt(4) != ' ' && s.charAt(5) != ' ')) {
                Help.wirte2File(s, path1, true);
                System.out.println("第" + n + "个  " + s + "出现大于20次，写入文件");
            } else {
                Help.wirte2File(s, path, true);
                System.out.println("第" + n + "个  " + s + "出现小于20次，写入文件");
            }
            n++;
        }
        bufferedReader.close();
    }
}

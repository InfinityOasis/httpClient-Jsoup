package xinguanbingdu.text;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import xinguanbingdu.tool.Help;
import xinguanbingdu.tool.HttpGetInfo;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 获取文本文件
 */
public class GetText {
    private static String path = "E:\\冯老师\\链接\\links.txt";
    private static String path1 = "E:\\冯老师\\文本\\";
    private int num = 1;

    public static void main(String[] args) {
        GetText getText = new GetText();
        new File(path1).mkdir();
        getText.getTexts2File(getText.getLinks(path));
    }

    //获取链接列表
    private List<String> getLinks(String path) {
        List<String> links = null;
        try {
            links = Help.readFile(path, links);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("读取文件失败");
        }
        return links;
    }

    //循环列表链接，爬取text文本
    private void getTexts2File(List<String> list) {
        for (String url : list) {
            String result = null;
            try {
                result = HttpGetInfo.getResult(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Document parse = Jsoup.parse(result);
            Elements select = parse.select("html").select("body");
            String text = select.text();
            System.out.println("读取文本第" + num + "个");
            try {
                Help.wirte2File(text, path1 + num + ".txt", false);
                System.out.println("写入第" + num + "个文本成功");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("写入第" + num + "个文本失败！");
            }
            num++;
        }
    }
}

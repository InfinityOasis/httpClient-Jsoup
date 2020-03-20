package xinguanbingdu.links;
/**
 * 获取链接列表
 */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import xinguanbingdu.tool.Help;
import xinguanbingdu.tool.HttpGetInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetLinks {
    private static String url = "http://www.baidu.com/s?wd=%E6%96%B0%E5%86%A0%E8%82%BA%E7%82%8E%2B%E7%97%87%E7%8A%B6%2B%E4%B8%AD%E8%8D%AF%2B%E8%AF%8A%E6%96%AD&pn=0&oq=%E6%96%B0%E5%86%A0%E8%82%BA%E7%82%8E%2B%E7%97%87%E7%8A%B6%2B%E4%B8%AD%E8%8D%AF%2B%E8%AF%8A%E6%96%AD&ie=utf-8&usm=1&rsv_pq=c0939aec0003d597&rsv_t=48e6TLqnX%2BXEaU4Fpecy08H52DfqrS5Q3xvJEia0hMldcmbZ9uCMUuT8HHY";
    private String path;
    private String name;
    private List<String> listLinks;
    private static int num = 0;
    private int pageNum = 1;

    public static void main(String[] args) {
        GetLinks getLinks = new GetLinks();
        for (int i = 0; ; i++) {
            if (num >= 500)
                break;
            List<String> strs = getLinks.getEachPaperLinks();
            for (String str : strs) {
                try {
                    Help.wirte2File(str, "E:\\冯老师\\链接\\links.txt",true);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("读写失败！");
                }
            }
        }
    }

    // jsoup解析
    // 获取工具类返回的html,并用Jsoup解析,返回list集合，并改变URL到下一页
    public List<String> getEachPaperLinks() {
        String result = null;
        try {
            result = HttpGetInfo.getResult(url);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("false");
            return null;
        }
//        System.out.println(result);  //测试result
        Document document = Jsoup.parse(result);
        document.setBaseUri(url);

        //jsoup 具体抓取links
        Elements links = document.select("div[id=content_left]").select("h3.t").select("a");
        listLinks = new ArrayList<>();
        for (Element link : links) {
            num++;
            String attr = link.attr("abs:href");
            System.out.println(num + "   " + attr);
            listLinks.add(num + "\t" + attr);
            if (num == 500)
                break;
        }
        System.out.println("当前页数：" + pageNum + "\n链接数：" + links.size());
        // 获取下一页链接
        // if 除去未加载出的 loading 问题
        if (links.size() != 0) {
            try {
                url = "http://www.baidu.com/s?wd=%E6%96%B0%E5%86%A0%E8%82%BA%E7%82%8E%2B%E7%97%87%E7%8A%B6%2B%E4%B8%AD%E8%8D%AF%2B%E8%AF%8A%E6%96%AD&pn=" + pageNum + "0&oq=%E6%96%B0%E5%86%A0%E8%82%BA%E7%82%8E%2B%E7%97%87%E7%8A%B6%2B%E4%B8%AD%E8%8D%AF%2B%E8%AF%8A%E6%96%AD&ie=utf-8&usm=1&rsv_pq=c0939aec0003d597&rsv_t=48e6TLqnX%2BXEaU4Fpecy08H52DfqrS5Q3xvJEia0hMldcmbZ9uCMUuT8HHY";

                //url = document.select("div[id=page]").select("a.n").attr("abs:href");
                pageNum += 1;
            } catch (Exception e) {
                System.out.println("nextPage False !! 当前页数为" + pageNum);
            }
            System.out.println("--------------------------");
            System.out.println("第" + pageNum + "页：" + url);

        }

        return listLinks;
    }

}

package xinguanbingdu.tool;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class HttpGetInfo {
    public static String getResult(String url) throws Exception {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build();
             CloseableHttpResponse response = httpClient.execute(new HttpGetConfig(url))) {
            String result = EntityUtils.toString(response.getEntity());
            return result;
        } catch (Exception e) {
            System.out.println("获取失败");
            return "";
        }
    }
}

/**
 * 内部类，继承HttpGet，为了设置请求超时的参数
 *
 * @author tom
 */
class HttpGetConfig extends HttpGet {
    public HttpGetConfig(String url) {
        super(url);
        setDefaulConfig();
    }

    private void setDefaulConfig() {
        this.setConfig(RequestConfig.custom()
                .setConnectionRequestTimeout(10000)
                .setConnectTimeout(10000)
                .setSocketTimeout(10000).build());
        this.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36");
    }
}

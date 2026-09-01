package cc.hongz.ztools.http;

import cc.hongz.ztools.core.date.DateUtil;
import cc.hongz.ztools.core.date.TimeInterval;
import cc.hongz.ztools.core.io.resource.StringResource;
import cc.hongz.ztools.core.lang.Console;
import cc.hongz.ztools.core.net.SSLProtocols;
import cc.hongz.ztools.core.net.url.UrlBuilder;
import cc.hongz.ztools.core.util.CharsetUtil;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.CookieManager;
import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {@link HttpRequest}单元测试.
 *
 * @author Looly
 */
@DisplayName("HttpRequest 单元测试")
public class HttpRequestTest {
    final String url = "http://photo.qzone.qq.com/fcgi-bin/fcg_list_album?uin=88888&outstyle=2";

    @Test
    @Disabled
    @DisplayName("测试 HTTPS GET 请求")
    public void getHttpsTest() {
        final String body = HttpRequest.get("https://www.hutool.cn/").timeout(10).execute().body();
        Console.log(body);
    }

    @Test
    @Disabled
    @DisplayName("测试 HTTPS GET 请求 then 回调")
    public void getHttpsThenTest() {
        HttpRequest
                .get("https://hutool.cn")
                .then(response -> Console.log(response.body()));
    }

    @Test
    @Disabled
    @DisplayName("测试连接关闭后获取 Cookie")
    public void getCookiesTest() {
        // 检查在Connection关闭情况下Cookie是否可以正常获取
        final HttpResponse res = HttpRequest.get("https://www.oschina.net/").execute();
        final String body = res.body();
        Console.log(res.getCookies());
        Console.log(body);
    }

    @Test
    @Disabled
    @DisplayName("测试请求对象字符串表示")
    public void toStringTest() {
        final String newUrl = "https://hutool.cn?ccc=你好";

        final HttpRequest request = HttpRequest.get(newUrl).form("a", "测试");
        Console.log(request.toString());
    }

    @Test
    @Disabled
    @DisplayName("测试 HEAD 请求")
    public void asyncHeadTest() {
        final HttpResponse response = HttpRequest.head(url).execute();
        final Map<String, List<String>> headers = response.headers();
        Console.log(headers);
        Console.log(response.body());
    }

    @Test
    @Disabled
    @DisplayName("测试异步 GET 请求")
    public void asyncGetTest() {
        final TimeInterval timer = DateUtil.timer();
        final HttpResponse body = HttpRequest.get(url).charset("GBK").executeAsync();
        final long interval = timer.interval();
        timer.restart();
        Console.log(body.body());
        final long interval2 = timer.interval();
        Console.log("Async response spend {}ms, body spend {}ms", interval, interval2);
    }

    @Test
    @Disabled
    @DisplayName("测试同步 GET 请求")
    public void syncGetTest() {
        final TimeInterval timer = DateUtil.timer();
        final HttpResponse body = HttpRequest.get(url).charset("GBK").execute();
        final long interval = timer.interval();
        timer.restart();
        Console.log(body.body());
        final long interval2 = timer.interval();
        Console.log("Async response spend {}ms, body spend {}ms", interval, interval2);
    }

    @Test
    @Disabled
    @DisplayName("测试自定义 GET 请求")
    public void customGetTest() {
        // 自定义构建HTTP GET请求，发送Http GET请求，针对HTTPS安全加密，可以自定义SSL
        final HttpRequest request = HttpRequest.get(url)
                // 自定义返回编码
                .charset(CharsetUtil.CHARSET_GBK)
                // 禁用缓存
                .disableCache()
                // 自定义SSL版本
                .setSSLProtocol(SSLProtocols.TLSv12);
        Console.log(request.execute().body());
    }

    @Test
    @Disabled
    @DisplayName("测试 deflate 压缩响应")
    public void getDeflateTest() {
        final String res = HttpRequest.get("https://comment.bilibili.com/67573272.xml")
                .execute().body();
        Console.log(res);
    }

    @Test
    @Disabled
    @DisplayName("测试 GET 请求携带 body")
    public void bodyTest() {
        final String ddddd1 = HttpRequest.get("https://baijiahao.baidu.com/s").body("id=1625528941695652600").execute().body();
        Console.log(ddddd1);
    }

    /**
     * 测试GET请求附带body体是否会变更为POST.
     */
    @Test
    @Disabled
    @DisplayName("测试 GET 请求附带表单")
    public void getLocalTest() {
        final List<String> list = new ArrayList<>();
        list.add("hhhhh");
        list.add("sssss");

        final Map<String, Object> map = new HashMap<>(16);
        map.put("recordId", "12321321");
        map.put("page", "1");
        map.put("size", "2");
        map.put("sizes", list);

        HttpRequest
                .get("http://localhost:8888/get")
                .form(map)
                .then(resp -> Console.log(resp.body()));
    }

    @Test
    @Disabled
    @DisplayName("测试不编码的 GET 请求")
    public void getWithoutEncodeTest() {
        final String newUrl = "https://img-cloud.voc.com.cn/140/2020/09/03/c3d41b93e0d32138574af8e8b50928b376ca5ba61599127028157.png?imageMogr2/auto-orient/thumbnail/500&pid=259848";
        final HttpRequest get = HttpUtil.createGet(newUrl);
        Console.log(get.getUrl());
        final HttpResponse execute = get.execute();
        Console.log(execute.body());
    }

    @Test
    @Disabled
    @DisplayName("测试手动重定向")
    public void followRedirectsTest() {
        // 从5.7.19开始关闭JDK的自动重定向功能，改为手动重定向
        // 当有多层重定向时，JDK的重定向会失效，或者说只有最后一个重定向有效，因此改为手动更易控制次数
        // 此链接有两次重定向，当设置次数为1时，表示最多执行一次重定向，即请求2次
        final String newUrl = "http://api.rosysun.cn/sjtx/?type=2";
//        String url = "https://api.btstu.cn/sjtx/api.php?lx=b1";

        // 方式1：全局设置
        HttpGlobalConfig.setMaxRedirectCount(1);
        HttpResponse execute = HttpRequest.get(newUrl).execute();
        Console.log(execute.getStatus(), execute.header(Header.LOCATION));

        // 方式2，单独设置
        execute = HttpRequest.get(newUrl).setMaxRedirectCount(1).execute();
        Console.log(execute.getStatus(), execute.header(Header.LOCATION));
    }

    @Test
    @Disabled
    @DisplayName("测试重定向携带 Cookie")
    public void followRedirectsCookieTrueTest() {
        final String newUrl = "http://localhost:8888/redirect1";
        CookieManager cookieManager = new CookieManager();
        HttpRequest.setCookieManager(cookieManager);
        HttpResponse execute = HttpRequest.get(newUrl)
                .setMaxRedirectCount(20)
                .setFollowRedirectsCookie(true)
                .execute();
        List<HttpCookie> cookies = cookieManager.getCookieStore().getCookies();
        Console.log(execute.getStatus(), cookies);
    }

    @Test
    @Disabled
    @DisplayName("测试重定向不携带 Cookie")
    public void followRedirectsCookieFalseTest() {
        final String newUrl = "http://localhost:8888/redirect1";
        CookieManager cookieManager = new CookieManager();
        HttpRequest.setCookieManager(cookieManager);
        HttpResponse execute = HttpRequest.get(newUrl)
                .setMaxRedirectCount(20)
                .execute();
        List<HttpCookie> cookies = cookieManager.getCookieStore().getCookies();
        Console.log(execute.getStatus(), cookies);
    }

    @Test
    @Disabled
    @DisplayName("测试添加拦截器")
    public void addInterceptorTest() {
        HttpUtil.createGet("https://hutool.cn")
                .addInterceptor(Console::log)
                .addResponseInterceptor((res) -> Console.log(res.getStatus()))
                .execute();
    }

    @Test
    @Disabled
    @DisplayName("测试添加全局拦截器")
    public void addGlobalInterceptorTest() {
        GlobalInterceptor.INSTANCE.addRequestInterceptor(Console::log);
        HttpUtil.createGet("https://hutool.cn").execute();
    }

    @Test
    @Disabled
    @DisplayName("测试 GET 请求带表单")
    public void getWithFormTest() {
        final String newUrl = "https://postman-echo.com/get";
        final Map<String, Object> map = new HashMap<>();
        map.put("aaa", "application+1@qqq.com");
        final HttpRequest request = HttpUtil.createGet(newUrl).form(map);
        Console.log(request.execute().body());
    }

    @Test
    @Disabled
    @DisplayName("测试 URL 带参数 GET 请求")
    public void urlWithParamIfGetTest() {
        final UrlBuilder urlBuilder = new UrlBuilder();
        urlBuilder.setScheme("https").setHost("hutool.cn");

        final HttpRequest httpRequest = new HttpRequest(urlBuilder);
        httpRequest.setMethod(Method.GET).execute();
    }

    @Test
    @Disabled
    @DisplayName("测试 GET 请求获取地址")
    public void issueI5Y68WTest() {
        final HttpResponse httpResponse = HttpRequest.get("http://82.157.17.173:8100/app/getAddress").execute();
        Console.log(httpResponse.body());
    }

    @Test
    @Disabled
    @DisplayName("测试 HttpRequest.of 构建请求")
    public void issueIAAE88Test() {
        final HttpRequest request = HttpRequest.of("http://127.0.0.1:8003/com.rnd.aiq:message/message/getName/15", null);
        Console.log(request);
    }

    @Test
    @Disabled
    @DisplayName("测试表单提交 HttpResource")
    public void testHttpResource() {

        HttpRequest httpRequest = HttpRequest.post("http://127.0.0.1:8080/testHttpResource");
        HttpResponse response = httpRequest.form("user", new HttpResource(new StringResource("{\n" +
                        "    \"name\": \"张三\",\n" +
                        "    \"age\": \"16\"\n" +
                        "}"), "application/json"))
                .form("passport", "12456")
                .execute();
        Console.log(response.body());
    }

    @Test
    @Disabled
    @DisplayName("测试 URL 解码")
    public void issueIAAOC1Test() {
        HttpGlobalConfig.setDecodeUrl(true);
        HttpRequest request = HttpRequest.get("http://localhost:9999/qms/bus/qmsBusReportCenterData/getReportDataList?reportProcessNo=A00&goodsName=工业硫酸98%&conReportTypeId=1010100000000000007&measureDateStr=2024-07-01");
        request.execute();
    }

    @Test
    @Disabled
    @DisplayName("测试请求头聚合")
    public void testHttpHead() {
        Map<String, String> map = new HashMap<>();
        map.put("test", "test");
        HttpRequest httpRequest = HttpRequest.post("http://127.0.0.1:8080/testHttpHead")
            .header("test", "test1")
            .headerMap(map, false)
            .headerAggregation(true);
        System.out.println(httpRequest.headers());
        HttpResponse execute = httpRequest.execute();
        System.out.println(execute.body());
    }
}

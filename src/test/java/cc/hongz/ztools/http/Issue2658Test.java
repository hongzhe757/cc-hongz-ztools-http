package cc.hongz.ztools.http;

import cc.hongz.ztools.core.lang.Console;
import cc.hongz.ztools.http.cookie.GlobalCookieManager;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.HttpCookie;
import java.util.List;

@DisplayName("Issue 2658 全局 Cookie 管理器测试")
public class Issue2658Test {

    @SuppressWarnings("resource")
    @Test
    @Disabled
    @DisplayName("GET 请求全局 Cookie 获取测试")
    public void getWithCookieTest() {
        HttpRequest.get("https://www.baidu.com/").execute();
        final List<HttpCookie> cookies = GlobalCookieManager.getCookieManager().getCookieStore().getCookies();
        Console.log("###" + cookies);
        HttpRequest.get("https://www.baidu.com/").execute();
    }
}

package cc.hongz.ztools.http;

import cc.hongz.ztools.core.lang.Console;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.HttpCookie;

@DisplayName("Issue I5TPSY 重定向请求测试")
public class IssueI5TPSYTest {
    @Test
    @Disabled
    @DisplayName("带 Cookie 重定向请求测试")
    public void redirectTest() {
        final String url = "https://bsxt.gdzwfw.gov.cn/UnifiedReporting/auth/newIndex";
        final HttpResponse res = HttpUtil.createGet(url).setFollowRedirects(true)
                .cookie(new HttpCookie("iPlanetDirectoryPro", "123"))
                .execute();
        Console.log(res.body());
    }
}

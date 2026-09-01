package cc.hongz.ztools.http.useragent;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("IssueIB3SJF 微信 Windows 客户端 UA 解析测试")
public class IssueIB3SJFTest {

    @Test
    @DisplayName("判断微信 Windows 客户端 UA 是否为移动端")
    void isMobileTest() {
        String str = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) "
            + "Chrome/122.0.0.0 Safari/537.36 NetType/WIFI MicroMessenger/7.0.20.1781(0x6700143B) "
            + "WindowsWechat(0x63090c11) XWEB/11275 Flue";
        UserAgent ua = UserAgentUtil.parse(str);

        Assertions.assertFalse(ua.isMobile());
        Assertions.assertEquals("7.0.20.1781", ua.getBrowser().getVersion(str));
    }
}

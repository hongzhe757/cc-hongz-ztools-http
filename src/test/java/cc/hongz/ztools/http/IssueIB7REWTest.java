package cc.hongz.ztools.http;

import cc.hongz.ztools.core.lang.Console;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Issue IB7REW TLS 请求测试")
public class IssueIB7REWTest {
    @Test
    @Disabled
    @DisplayName("TLS 命名曲线请求测试")
    void getTest() {
        System.setProperty("jdk.tls.namedCurves", "secp256r1,secp384r1,secp521r1");
        final String s = HttpUtil.get("https://ebssec.boc.cn/");
        Console.log(s);
    }
}

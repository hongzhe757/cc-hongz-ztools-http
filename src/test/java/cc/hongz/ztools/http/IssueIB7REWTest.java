package cc.hongz.ztools.http;

import cc.hongz.ztools.core.lang.Console;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class IssueIB7REWTest {
    @Test
    @Disabled
    void getTest() {
        System.setProperty("jdk.tls.namedCurves", "secp256r1,secp384r1,secp521r1");
        final String s = HttpUtil.get("https://ebssec.boc.cn/");
        Console.log(s);
    }
}

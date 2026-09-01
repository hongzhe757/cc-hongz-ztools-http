package cc.hongz.ztools.http;

import cc.hongz.ztools.core.io.FileUtil;
import cc.hongz.ztools.core.lang.Console;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Issue 3314 REST 请求 Body 测试")
public class Issue3314Test {
    @Test
    @Disabled
    @DisplayName("REST 请求 Body 测试")
    public void postTest() {
        String url = "https://hutool.cn/test/getList";
        final String body = HttpRequest.get(url)
            .setRest(true)
            .body(FileUtil.readBytes("d:/test/3314.xlsx"))
            .execute()
            .body();

        Console.log(body);
    }
}

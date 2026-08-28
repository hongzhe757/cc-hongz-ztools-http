package cc.hongz.ztools.http;

import cc.hongz.ztools.core.io.FileUtil;
import cc.hongz.ztools.core.lang.Console;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class Issue3314Test {
    @Test
    @Disabled
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

package cc.hongz.ztools.http.server;

import cc.hongz.ztools.http.HttpUtil;

import java.io.IOException;
import org.junit.jupiter.api.DisplayName;

@SuppressWarnings("checkstyle:HideUtilityClassConstructor")
@DisplayName("Issue #3568 处理器异常测试")
public class Issue3568Test {
    @DisplayName("处理器抛出 IOException 验证异常处理")
    public static void main(final String[] args) {
        HttpUtil.createServer(8888)
            .addHandler("/", httpExchange -> {
                throw new IOException("111");
            })
            .start();
    }
}

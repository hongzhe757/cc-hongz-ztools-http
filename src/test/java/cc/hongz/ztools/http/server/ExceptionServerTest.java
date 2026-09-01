package cc.hongz.ztools.http.server;

import cc.hongz.ztools.http.HttpUtil;
import cc.hongz.ztools.http.server.filter.DefaultExceptionFilter;

import org.junit.jupiter.api.DisplayName;

@SuppressWarnings("checkstyle:HideUtilityClassConstructor")
@DisplayName("HTTP 服务器异常过滤器测试")
public class ExceptionServerTest {
    @DisplayName("注册默认异常过滤器验证运行时异常处理")
    public static void main(final String[] args) {
        HttpUtil.createServer(8888)
            .addFilter(new DefaultExceptionFilter())
            .addAction("/", (req, res) -> {
                throw new RuntimeException("Test Exception");
            })
            .start();
    }
}

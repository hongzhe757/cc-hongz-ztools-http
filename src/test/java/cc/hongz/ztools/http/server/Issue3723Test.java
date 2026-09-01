package cc.hongz.ztools.http.server;

import cc.hongz.ztools.core.util.IdUtil;
import cc.hongz.ztools.http.ContentType;
import cc.hongz.ztools.http.HttpUtil;
import org.junit.jupiter.api.DisplayName;

@SuppressWarnings("checkstyle:HideUtilityClassConstructor")
@DisplayName("Issue #3723 过滤器请求 ID 透传测试")
public class Issue3723Test {
    @DisplayName("在过滤器中生成请求 ID 并写入响应头")
    public static void main(final String[] args) {
        SimpleServer server = HttpUtil.createServer(8888);
        server.addFilter((req, res, chain) -> {
            String requestId = IdUtil.fastSimpleUUID();
            req.getHttpExchange().setAttribute("requestId", requestId);
            res.addHeader("X-Request-Id", requestId);

            res.write("new Content");

            chain.doFilter(req.getHttpExchange());
        });
        server.addAction("/", (req, res) -> res.write("Hello Hutool Server", ContentType.JSON.getValue()));
        server.start();
    }
}

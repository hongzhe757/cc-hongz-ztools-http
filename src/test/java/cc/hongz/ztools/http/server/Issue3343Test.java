package cc.hongz.ztools.http.server;

import cc.hongz.ztools.core.date.DateUtil;
import cc.hongz.ztools.core.lang.Console;
import cc.hongz.ztools.core.map.multi.ListValueMap;
import cc.hongz.ztools.http.HttpUtil;
import org.junit.jupiter.api.DisplayName;

/**
 * http://localhost:8888/?name=hutool.
 */
@SuppressWarnings("checkstyle:HideUtilityClassConstructor")
@DisplayName("Issue #3343 过滤器获取请求参数测试")
public class Issue3343Test {
    @DisplayName("在过滤器和处理逻辑中获取请求参数")
    public static void main(final String[] args) {
        final SimpleServer server = HttpUtil.createServer(8888)
            .addFilter((req, res, chain) -> {
                Console.log(DateUtil.now() + " got request: " + req.getPath());
                Console.log(" >   from : " + req.getClientIP());
                // 过滤器中获取请求参数
                Console.log(" > params : " + req.getParams());
                chain.doFilter(req.getHttpExchange());
            });

        server.addAction("/", Issue3343Test::index);

        server.start();
    }

    private static void index(final HttpServerRequest request, final HttpServerResponse response) {
        // 具体逻辑中再次获取请求参数
        ListValueMap<String, String> params = request.getParams();
        Console.log("index params: " + params);
        response.getWriter().write("GOT: " + params);
    }
}

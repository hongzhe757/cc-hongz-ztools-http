package cc.hongz.ztools.http.server;

import cc.hongz.ztools.core.util.IdUtil;
import cc.hongz.ztools.http.ContentType;
import cc.hongz.ztools.http.HttpUtil;

public class Issue3723Test {
    public static void main(String[] args) {
        SimpleServer server = HttpUtil.createServer(8888);
        server.addFilter((req, res, chain) -> {
            String requestId = IdUtil.fastSimpleUUID();
            req.getHttpExchange().setAttribute("requestId", requestId);
            res.addHeader("X-Request-Id", requestId);

            res.write("new Content");

            chain.doFilter(req.getHttpExchange());
        });
        server.addAction("/", (req, res)-> res.write("Hello Hutool Server", ContentType.JSON.getValue()));
        server.start();
    }
}

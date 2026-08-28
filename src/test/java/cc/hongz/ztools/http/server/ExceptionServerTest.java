package cc.hongz.ztools.http.server;

import cc.hongz.ztools.http.HttpUtil;
import cc.hongz.ztools.http.server.filter.DefaultExceptionFilter;

import java.io.IOException;

public class ExceptionServerTest {
    public static void main(final String[] args) {
        HttpUtil.createServer(8888)
            .addFilter(new DefaultExceptionFilter())
            .addAction("/", (req, res) -> {
                throw new RuntimeException("Test Exception");
            })
            .start();
    }
}

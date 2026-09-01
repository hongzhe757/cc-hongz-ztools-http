package cc.hongz.ztools.http.server;

import cc.hongz.ztools.core.swing.DesktopUtil;
import cc.hongz.ztools.http.ContentType;
import cc.hongz.ztools.http.HttpUtil;
import org.junit.jupiter.api.DisplayName;

@SuppressWarnings("checkstyle:HideUtilityClassConstructor")
@DisplayName("HTTP 服务器最简示例测试")
public class BlankServerTest {
    @DisplayName("启动返回 JSON 的空白服务器并打开浏览器")
    public static void main(final String[] args) {
        HttpUtil.createServer(8888)
                .addAction("/", (req, res) -> res.write("Hello Hutool Server", ContentType.JSON.getValue()))
                .start();

        DesktopUtil.browse("http://localhost:8888/");
    }
}

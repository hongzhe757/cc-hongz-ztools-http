package cc.hongz.ztools.http.server;

import cc.hongz.ztools.core.swing.DesktopUtil;
import cc.hongz.ztools.http.HttpUtil;
import org.junit.jupiter.api.DisplayName;

@SuppressWarnings("checkstyle:HideUtilityClassConstructor")
@DisplayName("HTTP 静态文档服务器测试")
public class DocServerTest {

    @DisplayName("以指定根目录启动静态文档服务器并浏览首页")
    public static void main(final String[] args) {
        HttpUtil.createServer(80)
                // 设置默认根目录，
                .setRoot("D:\\workspace\\site\\hutool-site")
                .start();

        DesktopUtil.browse("http://localhost/");
    }
}

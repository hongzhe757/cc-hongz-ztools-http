package cc.hongz.ztools.http.server;

import cc.hongz.ztools.core.swing.DesktopUtil;
import cc.hongz.ztools.http.ContentType;
import cc.hongz.ztools.http.HttpUtil;

public class BlankServerTest {
    public static void main(String[] args) {
        HttpUtil.createServer(8888)
                .addAction("/", (req, res)-> res.write("Hello Hutool Server", ContentType.JSON.getValue()))
                .start();

        DesktopUtil.browse("http://localhost:8888/");
    }
}

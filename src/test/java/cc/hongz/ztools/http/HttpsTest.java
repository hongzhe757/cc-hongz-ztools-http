package cc.hongz.ztools.http;

import cc.hongz.ztools.core.lang.Console;
import cc.hongz.ztools.core.thread.ThreadUtil;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

@DisplayName("Https 单元测试")
public class HttpsTest {

    /**
     * 测试单例的SSLSocketFactory是否有线程安全问题.
     */
    @Test
    @Disabled
    @DisplayName("测试单例 SSLSocketFactory 线程安全")
    public void getTest() {
        final AtomicInteger count = new AtomicInteger();
        for (int i = 0; i < 100; i++) {
            ThreadUtil.execute(() -> {
                final String s = HttpUtil.get("https://www.baidu.com/");
                Console.log(count.incrementAndGet());
            });
        }
        ThreadUtil.sync(this);
    }
}

package cc.hongz.ztools.http;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("HTMLFilter 单元测试")
public class HTMLFilterTest {
    @Test
    @DisplayName("测试过滤 HTML 事件属性")
    public void issue3433Test() {
        String p1 = "<p>a</p>";
        String p2 = "<p onclick=\"bbbb\">a</p>";

        final HTMLFilter htmlFilter = new HTMLFilter();
        String filter = htmlFilter.filter(p1);
        assertEquals("<p>a</p>", filter);

        filter = htmlFilter.filter(p2);
        assertEquals("<p>a</p>", filter);
    }
}

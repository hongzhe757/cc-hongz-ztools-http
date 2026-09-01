package cc.hongz.ztools.http;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Issue I8YV0K HtmlUtil 移除 HTML 属性测试")
public class IssueI8YV0KTest {

    @Test
    @DisplayName("移除 HTML 属性（属性值含空格）测试")
    public void removeHtmlAttrTest() {
        final String str = "<content styleCode=\"xmChange yes\">";
        assertEquals("<content>", HtmlUtil.removeHtmlAttr(str, "styleCode"));
    }

    @Test
    @DisplayName("移除 HTML 属性（自闭合标签）测试")
    public void removeHtmlAttrTest2() {
        final String str = "<content styleCode=\"xmChange\"/>";
        assertEquals("<content/>", HtmlUtil.removeHtmlAttr(str, "styleCode"));
    }

    @Test
    @DisplayName("移除 HTML 属性（含多个属性）测试")
    public void removeHtmlAttrTest3() {
        String str = "<content styleCode=\"dada ada\" data=\"dsad\" >";
        assertEquals("<content data=\"dsad\">", HtmlUtil.removeHtmlAttr(str, "styleCode"));
    }
}

package cc.hongz.ztools.http;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Html单元测试.
 *
 * @author looly
 *
 */
@SuppressWarnings("checkstyle:HideUtilityClassConstructor")
@DisplayName("HtmlUtil 单元测试")
public class HtmlUtilTest {

    @Test
    @DisplayName("测试移除指定 HTML 标签")
    public void removeHtmlTagTest() {
        //非闭合标签
        String str = "pre<img src=\"xxx/dfdsfds/test.jpg\">";
        String result = HtmlUtil.removeHtmlTag(str, "img");
        assertEquals("pre", result);

        //闭合标签
        str = "pre<img>";
        result = HtmlUtil.removeHtmlTag(str, "img");
        assertEquals("pre", result);

        //闭合标签
        str = "pre<img src=\"xxx/dfdsfds/test.jpg\" />";
        result = HtmlUtil.removeHtmlTag(str, "img");
        assertEquals("pre", result);

        //闭合标签
        str = "pre<img />";
        result = HtmlUtil.removeHtmlTag(str, "img");
        assertEquals("pre", result);

        //包含内容标签
        str = "pre<div class=\"test_div\">dfdsfdsfdsf</div>";
        result = HtmlUtil.removeHtmlTag(str, "div");
        assertEquals("pre", result);

        //带换行
        str = "pre<div class=\"test_div\">\r\n\t\tdfdsfdsfdsf\r\n</div>";
        result = HtmlUtil.removeHtmlTag(str, "div");
        assertEquals("pre", result);
    }

    @Test
    @DisplayName("测试清理 HTML 标签")
    public void cleanHtmlTagTest() {
        //非闭合标签
        String str = "pre<img src=\"xxx/dfdsfds/test.jpg\">";
        String result = HtmlUtil.cleanHtmlTag(str);
        assertEquals("pre", result);

        //闭合标签
        str = "pre<img>";
        result = HtmlUtil.cleanHtmlTag(str);
        assertEquals("pre", result);

        //闭合标签
        str = "pre<img src=\"xxx/dfdsfds/test.jpg\" />";
        result = HtmlUtil.cleanHtmlTag(str);
        assertEquals("pre", result);

        //闭合标签
        str = "pre<img />";
        result = HtmlUtil.cleanHtmlTag(str);
        assertEquals("pre", result);

        //包含内容标签
        str = "pre<div class=\"test_div\">dfdsfdsfdsf</div>";
        result = HtmlUtil.cleanHtmlTag(str);
        assertEquals("predfdsfdsfdsf", result);

        //带换行
        str = "pre<div class=\"test_div\">\r\n\t\tdfdsfdsfdsf\r\n</div><div class=\"test_div\">BBBB</div>";
        result = HtmlUtil.cleanHtmlTag(str);
        assertEquals("pre\r\n\t\tdfdsfdsfdsf\r\nBBBB", result);
    }

    @Test
    @DisplayName("测试清理空标签")
    public void cleanEmptyTagTest() {
        String str = "<p></p><div></div>";
        String result = HtmlUtil.cleanEmptyTag(str);
        assertEquals("", result);

        str = "<p>TEXT</p><div></div>";
        result = HtmlUtil.cleanEmptyTag(str);
        assertEquals("<p>TEXT</p>", result);

        str = "<p></p><div>TEXT</div>";
        result = HtmlUtil.cleanEmptyTag(str);
        assertEquals("<div>TEXT</div>", result);

        str = "<p>TEXT</p><div>TEXT</div>";
        result = HtmlUtil.cleanEmptyTag(str);
        assertEquals("<p>TEXT</p><div>TEXT</div>", result);

        str = "TEXT<p></p><div></div>TEXT";
        result = HtmlUtil.cleanEmptyTag(str);
        assertEquals("TEXTTEXT", result);
    }

    @Test
    @DisplayName("测试解开 HTML 标签")
    public void unwrapHtmlTagTest() {
        //非闭合标签
        String str = "pre<img src=\"xxx/dfdsfds/test.jpg\">";
        String result = HtmlUtil.unwrapHtmlTag(str, "img");
        assertEquals("pre", result);

        //闭合标签
        str = "pre<img>";
        result = HtmlUtil.unwrapHtmlTag(str, "img");
        assertEquals("pre", result);

        //闭合标签
        str = "pre<img src=\"xxx/dfdsfds/test.jpg\" />";
        result = HtmlUtil.unwrapHtmlTag(str, "img");
        assertEquals("pre", result);

        //闭合标签
        str = "pre<img />";
        result = HtmlUtil.unwrapHtmlTag(str, "img");
        assertEquals("pre", result);

        //闭合标签
        str = "pre<img/>";
        result = HtmlUtil.unwrapHtmlTag(str, "img");
        assertEquals("pre", result);

        //包含内容标签
        str = "pre<div class=\"test_div\">abc</div>";
        result = HtmlUtil.unwrapHtmlTag(str, "div");
        assertEquals("preabc", result);

        //带换行
        str = "pre<div class=\"test_div\">\r\n\t\tabc\r\n</div>";
        result = HtmlUtil.unwrapHtmlTag(str, "div");
        assertEquals("pre\r\n\t\tabc\r\n", result);
    }

    @Test
    @DisplayName("测试移除标签避免误删 img")
    public void unwrapTest2() {
        // 避免移除i却误删img标签的情况
        final String htmlString = "<html><img src='aaa'><i>测试文本</i></html>";
        final String tagString = "i,br";
        final String cleanTxt = HtmlUtil.removeHtmlTag(htmlString, false, tagString.split(","));
        assertEquals("<html><img src='aaa'>测试文本</html>", cleanTxt);
    }

    @Test
    @DisplayName("测试 HTML 转义与反转义")
    public void escapeTest() {
        final String html = "<html><body>123'123'</body></html>";
        final String escape = HtmlUtil.escape(html);
        assertEquals("&lt;html&gt;&lt;body&gt;123&#039;123&#039;&lt;/body&gt;&lt;/html&gt;", escape);
        final String restoreEscaped = HtmlUtil.unescape(escape);
        assertEquals(html, restoreEscaped);
        assertEquals("'", HtmlUtil.unescape("&apos;"));
    }

    @Test
    @DisplayName("测试不断开空格转义")
    public void escapeTest2() {
        final char c = ' '; // 不断开空格（non-breaking space，缩写nbsp。)
        assertEquals(c, 160);
        final String html = "<html><body> </body></html>";
        final String escape = HtmlUtil.escape(html);
        assertEquals("&lt;html&gt;&lt;body&gt;&nbsp;&lt;/body&gt;&lt;/html&gt;", escape);
        assertEquals(" ", HtmlUtil.unescape("&nbsp;"));
    }

    @Test
    @DisplayName("测试 HTML 过滤")
    public void filterTest() {
        final String html = "<alert></alert>";
        final String filter = HtmlUtil.filter(html);
        assertEquals("", filter);
    }

    @Test
    @DisplayName("测试移除 HTML 属性")
    public void removeHtmlAttrTest() {

        // 去除的属性加双引号测试
        String html = "<div class=\"test_div\"></div><span class=\"test_div\"></span>";
        String result = HtmlUtil.removeHtmlAttr(html, "class");
        assertEquals("<div></div><span></span>", result);

        // 去除的属性后跟空格、加单引号、不加引号测试
        html = "<div class=test_div></div><span Class='test_div' ></span>";
        result = HtmlUtil.removeHtmlAttr(html, "class");
        assertEquals("<div></div><span></span>", result);

        // 去除的属性位于标签末尾、其它属性前测试
        html = "<div style=\"margin:100%\" class=test_div></div><span Class='test_div' width=100></span>";
        result = HtmlUtil.removeHtmlAttr(html, "class");
        assertEquals("<div style=\"margin:100%\"></div><span width=100></span>", result);

        // 去除的属性名和值之间存在空格
        html = "<div style = \"margin:100%\" class = test_div></div><span Class = 'test_div' width=100></span>";
        result = HtmlUtil.removeHtmlAttr(html, "class");
        assertEquals("<div style = \"margin:100%\"></div><span width=100></span>", result);
    }

    @Test
    @DisplayName("测试移除指定标签所有属性")
    public void removeAllHtmlAttrTest() {
        final String html = "<div class=\"test_div\" width=\"120\"></div>";
        final String result = HtmlUtil.removeAllHtmlAttr(html, "div");
        assertEquals("<div></div>", result);
    }

    @Test
    @DisplayName("测试移除多 class 属性")
    public void issueI6YNTFTest() {
        String html = "<html><body><div class=\"a1 a2\">hello world</div></body></html>";
        String cleanText = HtmlUtil.removeHtmlAttr(html, "class");
        assertEquals("<html><body><div>hello world</div></body></html>", cleanText);

        html = "<html><body><div class=a1>hello world</div></body></html>";
        cleanText = HtmlUtil.removeHtmlAttr(html, "class");
        assertEquals("<html><body><div>hello world</div></body></html>", cleanText);
    }
}

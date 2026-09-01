package cc.hongz.ztools.http;

import cc.hongz.ztools.core.util.CharsetUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * ContentType 单元测试.
 *
 *
 */
@DisplayName("ContentType 单元测试")
public class ContentTypeTest {

    @Test
    @DisplayName("测试构建 Content-Type 字符串")
    public void testBuild() {
        String result = ContentType.build(ContentType.JSON, CharsetUtil.CHARSET_UTF_8);
        assertEquals("application/json;charset=UTF-8", result);
    }

    @Test
    @DisplayName("测试解析带前导空格的 Content-Type")
    public void testGetWithLeadingSpace() {
        String json = " {\n" +
            "     \"name\": \"hutool\"\n" +
            " }";
        ContentType contentType = ContentType.get(json);
        assertEquals(ContentType.JSON, contentType);
    }
}

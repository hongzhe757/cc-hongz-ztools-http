package cc.hongz.ztools.http;

import cc.hongz.ztools.core.util.CharsetUtil;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

@DisplayName("Issue 3536 URL 表单编码与参数序列化测试")
public class Issue3536Test {

    @Test
    @DisplayName("URL 表单编码测试")
    public void urlWithFormUrlEncodedTest() {
        String url = "https://hutool.cn/test";
        final Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("redirect_uri", "https://api.hutool.cn/v1/test");
        paramMap.put("scope", "a,b,c你");

        final String s = HttpUtil.urlWithFormUrlEncoded(url, paramMap, CharsetUtil.CHARSET_UTF_8);
        assertEquals("https://hutool.cn/test?scope=a,b,c%E4%BD%A0&redirect_uri=https://api.hutool.cn/v1/test", s);
    }

    @Test
    @DisplayName("参数序列化测试")
    public void toParamsTest() {
        final Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("redirect_uri", "https://api.hutool.cn/v1/test");
        paramMap.put("scope", "a,b,c你");

        final String params = HttpUtil.toParams(paramMap, CharsetUtil.CHARSET_UTF_8, true);
        assertEquals("scope=a%2Cb%2Cc%E4%BD%A0&redirect_uri=https%3A%2F%2Fapi.hutool.cn%2Fv1%2Ftest", params);
    }
}

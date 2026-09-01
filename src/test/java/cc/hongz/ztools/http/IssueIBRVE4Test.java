package cc.hongz.ztools.http;

import cc.hongz.ztools.core.util.CharsetUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Issue IBRVE4 URL 参数解码测试")
public class IssueIBRVE4Test {
    @Test
    @DisplayName("无参数值 URL 解码参数 Map 测试")
    public void decodeParamMapNoParamTest() {
        // 参数值不存在分界标记等号时
        // 无参数值时
        final Map<String, List<String>> paramMap = HttpUtil.decodeParams("https://hutool.cn/api.action", CharsetUtil.CHARSET_UTF_8);
        assertEquals(0, paramMap.size());
    }
    @Test
    @DisplayName("无参数值 URL 解码参数列表 Map 测试")
    public void decodeParamMapListNoParamTest() {
        // 参数值不存在分界标记等号时
        // 无参数值时
        final Map<String, String> paramMap1 = HttpUtil.decodeParamMap("https://hutool.cn/api.action", CharsetUtil.CHARSET_UTF_8);
        assertEquals(0, paramMap1.size());
    }
}

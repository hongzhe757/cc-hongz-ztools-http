package cc.hongz.ztools.http.body;

import cc.hongz.ztools.core.io.resource.StringResource;
import cc.hongz.ztools.core.util.CharsetUtil;
import cc.hongz.ztools.http.HttpResource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;

@DisplayName("MultipartBody 单元测试")
public class MultipartBodyTest {

    @Test
    @DisplayName("测试创建 multipart 请求体")
    public void buildTest() {
        Map<String, Object> form = new HashMap<>();
        form.put("pic1", "pic1 content");
        form.put("pic2", new HttpResource(
                new StringResource("pic2 content"), "text/plain"));
        form.put("pic3", new HttpResource(
                new StringResource("pic3 content", "pic3.jpg"), "image/jpeg"));

        final MultipartBody body = MultipartBody.create(form, CharsetUtil.CHARSET_UTF_8);

        assertNotNull(body.toString());
//        Console.log(body);
    }
}

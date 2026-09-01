package cc.hongz.ztools.http;

import cc.hongz.ztools.core.io.resource.StringResource;
import cc.hongz.ztools.core.lang.Console;
import cc.hongz.ztools.json.JSONUtil;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Rest类型请求单元测试.
 *
 * @author looly
 */
@DisplayName("Rest 类型请求单元测试")
public class RestTest {

    @Test
    @DisplayName("POST JSON 请求 Content-Type 测试")
    public void contentTypeTest() {
        HttpRequest request = HttpRequest.post("http://localhost:8090/rest/restTest/")//
                .body(JSONUtil.createObj()
                        .set("aaa", "aaaValue")
                        .set("键2", "值2").toString());
        assertEquals("application/json;charset=UTF-8", request.header("Content-Type"));
    }

    @Test
    @Disabled
    @DisplayName("POST 请求测试")
    public void postTest() {
        HttpRequest request = HttpRequest.post("http://localhost:8888/restTest/")//
                .body(new StringResource(JSONUtil.createObj()
                        .set("aaa", "aaaValue")
                        .set("键2", "值2").toString()));
        Console.log(request.execute().body());
    }

    @Test
    @Disabled
    @DisplayName("POST JSON 请求测试")
    public void postTest2() {
        String result = HttpUtil.post("http://localhost:8090/rest/restTest/", JSONUtil.createObj()//
                .set("aaa", "aaaValue")
                .set("键2", "值2").toString());
        Console.log(result);
    }

    @Test
    @Disabled
    @DisplayName("GET 请求携带 Body 测试")
    public void getWithBodyTest() {
        HttpRequest request = HttpRequest.get("http://localhost:8888/restTest")//
                .header(Header.CONTENT_TYPE, "application/json")
                .body(JSONUtil.createObj()
                        .set("aaa", "aaaValue")
                        .set("键2", "值2").toString());
        Console.log(request.execute().body());
    }

    @Test
    @Disabled
    @DisplayName("GET 请求带代理和 Body 测试")
    public void getWithBodyTest2() {
        HttpRequest request = HttpRequest.get("https://ad.oceanengine.com/open_api/2/advertiser/info/")//
                // Charles代理
                .setHttpProxy("localhost", 8888)
                .header("Access-Token", "")
                .body(JSONUtil.createObj()
                        .set("advertiser_ids", new Long[]{1690657248243790L})
                        .set("fields", new String[]{"id", "name", "status"}).toString());
        Console.log(request);
        Console.log(request.execute().body());
    }

    @Test
    @Disabled
    @DisplayName("GET 请求响应头测试")
    public void getTest() {
        final HttpRequest request = HttpRequest.get("http://localhost:8888/restTest");
        final HttpResponse execute = request.execute();
        Console.log(execute.headers());
    }
}

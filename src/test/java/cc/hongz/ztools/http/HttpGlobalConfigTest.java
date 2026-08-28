package cc.hongz.ztools.http;

import org.junit.jupiter.api.Test;

@SuppressWarnings("checkstyle:HideUtilityClassConstructor")
public class HttpGlobalConfigTest {
    @Test
    public void allowPatchTest() {
        HttpGlobalConfig.allowPatch();
    }
}

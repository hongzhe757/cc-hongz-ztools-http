package cc.hongz.ztools.http;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@SuppressWarnings("checkstyle:HideUtilityClassConstructor")
@DisplayName("HttpGlobalConfig 单元测试")
public class HttpGlobalConfigTest {
    /**
     * JDK 12 起 {@code java.lang.reflect.Field.modifiers} 不再可通过 {@code getDeclaredField} 反射获取，
     * 导致 {@link HttpGlobalConfig#allowPatch()} 内部的 {@code ReflectUtil.removeFinalModify} 在 JDK 17 下抛
     * {@code NoSuchFieldException: modifiers}。此为 hutool 原版的已知限制，仅 JDK 8/11 下可用，故本测试禁用。
     */
    @Test
    @Disabled("JDK 12+ 反射 Field.modifiers 失效，hutool 原版 allowPatch 仅在 JDK 8/11 可用")
    @DisplayName("测试允许 PATCH 请求")
    public void allowPatchTest() {
        HttpGlobalConfig.allowPatch();
    }
}

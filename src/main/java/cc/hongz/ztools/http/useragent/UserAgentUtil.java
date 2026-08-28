package cc.hongz.ztools.http.useragent;

/**
 * User-Agent工具类.
 *
 * @author looly
 *
 */
@SuppressWarnings("checkstyle:HideUtilityClassConstructor")
public class UserAgentUtil {

    /**
     * 解析User-Agent.
     *
     * @param userAgentString User-Agent字符串
     * @return {@link UserAgent}
     */
    public static UserAgent parse(final String userAgentString) {
        return UserAgentParser.parse(userAgentString);
    }

}

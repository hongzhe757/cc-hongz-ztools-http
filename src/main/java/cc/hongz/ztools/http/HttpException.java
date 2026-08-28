package cc.hongz.ztools.http;

import cc.hongz.ztools.core.util.StrUtil;

/**
 * HTTP异常.
 *
 * @author xiaoleilu
 */
public class HttpException extends RuntimeException {
    private static final long serialVersionUID = 8247610319171014183L;

    /**
     * 构造。
     *
     * @param e 异常
     */
    public HttpException(final Throwable e) {
        super(e.getMessage(), e);
    }

    /**
     * 构造。
     *
     * @param message 异常消息
     */
    public HttpException(final String message) {
        super(message);
    }

    /**
     * 构造。
     *
     * @param messageTemplate 异常消息模板，使用{}作为占位符
     * @param params          参数
     */
    public HttpException(final String messageTemplate, final Object... params) {
        super(StrUtil.format(messageTemplate, params));
    }

    /**
     * 构造。
     *
     * @param message   异常消息
     * @param throwable 异常
     */
    public HttpException(final String message, final Throwable throwable) {
        super(message, throwable);
    }

    /**
     * 构造。
     *
     * @param message            异常消息
     * @param throwable          异常
     * @param enableSuppression  是否启用抑制
     * @param writableStackTrace 是否可写堆栈
     */
    public HttpException(final String message, final Throwable throwable, final boolean enableSuppression,
            final boolean writableStackTrace) {
        super(message, throwable, enableSuppression, writableStackTrace);
    }

    /**
     * 构造。
     *
     * @param throwable       异常
     * @param messageTemplate 异常消息模板，使用{}作为占位符
     * @param params          参数
     */
    public HttpException(final Throwable throwable, final String messageTemplate, final Object... params) {
        super(StrUtil.format(messageTemplate, params), throwable);
    }
}

package cc.hongz.ztools.http.webservice;

import cc.hongz.ztools.core.util.StrUtil;

/**
 * SOAP异常.
 * 
 * @author xiaoleilu
 */
public class SoapRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 8247610319171014183L;

    /**
     * 构造.
     *
     * @param e 异常
     */
    public SoapRuntimeException(final Throwable e) {
        super(e.getMessage(), e);
    }

    /**
     * 构造.
     *
     * @param message 消息
     */
    public SoapRuntimeException(final String message) {
        super(message);
    }

    /**
     * 构造.
     *
     * @param messageTemplate 消息模板
     * @param params          参数
     */
    public SoapRuntimeException(final String messageTemplate, final Object... params) {
        super(StrUtil.format(messageTemplate, params));
    }

    /**
     * 构造.
     *
     * @param message   消息
     * @param throwable 异常
     */
    public SoapRuntimeException(final String message, final Throwable throwable) {
        super(message, throwable);
    }

    /**
     * 构造.
     *
     * @param throwable       异常
     * @param messageTemplate 消息模板
     * @param params          参数
     */
    public SoapRuntimeException(final Throwable throwable, final String messageTemplate, final Object... params) {
        super(StrUtil.format(messageTemplate, params), throwable);
    }
}

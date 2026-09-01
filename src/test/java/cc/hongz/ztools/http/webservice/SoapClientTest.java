package cc.hongz.ztools.http.webservice;

import cc.hongz.ztools.core.lang.Console;
import cc.hongz.ztools.core.util.CharsetUtil;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

/**
 * SOAP相关单元测试.
 *
 * @author looly
 *
 */
@DisplayName("SOAP 客户端单元测试")
public class SoapClientTest {

    @Test
    @Disabled
    @DisplayName("SOAP 请求测试")
    public void requestTest() {
        SoapClient client = SoapClient.create("http://www.webxml.com.cn/WebServices/IpAddressSearchWebService.asmx")
        .setMethod("web:getCountryCityByIp", "http://WebXml.com.cn/")
        .setCharset(CharsetUtil.CHARSET_GBK)
        .setParam("theIpAddress", "218.21.240.106");

        Console.log(client.getMsgStr(true));

        Console.log(client.send(true));
    }

    @Test
    @Disabled
    @DisplayName("SOAP 请求返回 Message 测试")
    public void requestForMessageTest() throws SOAPException {
        SoapClient client = SoapClient.create("http://www.webxml.com.cn/WebServices/IpAddressSearchWebService.asmx")
                .setMethod("web:getCountryCityByIp", "http://WebXml.com.cn/")
                .setParam("theIpAddress", "218.21.240.106");

        SOAPMessage message = client.sendForMessage();
        Console.log(message.getSOAPBody().getTextContent());
    }
}

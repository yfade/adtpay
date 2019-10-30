package com.pay.ali;

/**
 * @ClassName: AliPayConfig
 * @Description: 需要传给支付宝SDK的公共基本参数
 * @author: Linn
 * @Date: 2019/4/3 9:22
 */
public class AliDevPayConfig {

    /**
     * 1.商户appid
     */
    public static String APPID = "2014072300007148";

    /**
     * 私钥 pkcs8格式的
     */
    public static String RSA_PRIVATE_KEY = "MIIEVGILKJLKJLKJLQ9S";

    /**
     * 3.支付宝公钥
     */
    public static String ALIPAY_PUBLIC_KEY = "MIIEVGILKJLKJLKJLQ9S";

    /**
     * 4.服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
     */
//这个着重讲一下..支付完成后,支付宝会通过这个url请求到你的服务端..
//这个url一定是要公网可以访问才行...如果需要测试的话..我后面有讲到..
//这里你可以先写你本地项目的url 例如:localhost:8080/项目名/访问路径
    public static String notify_url = "http://2hu4349021.wicp.vip/pay/aliNotify";

    /**
     * 5.页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
     */
    //这里同上..不做详细说明了..
    public static String return_url = "http://2hu4349021.wicp.vip/pay/returnUrl";

    /**
     * 正式环境支付宝网关，如果是沙箱环境需更改成https://openapi.alipaydev.com/gateway.do
     */
    public static String URL = "https://openapi.alipaydev.com/gateway.do";

    /**
     * 7.编码
     */
    public static String CHARSET = "UTF-8";

    /**
     * 私钥 pkcs8格式的
     */
    // 8.返回格式
    public static String FORMAT = "json";

    /**
     * //签名方式 加密类型
     */
    public static String SIGNTYPE = "RSA2";

}
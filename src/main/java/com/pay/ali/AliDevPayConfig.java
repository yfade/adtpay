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
    public static String APPID = "2016101600697932";

    /**
     * 私钥 pkcs8格式的
     */
    public static String RSA_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCmlB6/j4gIBjRm2ui3oqU/M+SoE5Irxqn45ADHzJ2BGs4NALEnbuJsgXiuP5ZzmhpfBjacZkdHSd0O6EzHiRvwu0eg4hfJcJ8kB6H+PGnYXt82Iel5dQNM+Ce5JV/5eSvcBgCoR0dQRGD5kSMR2rlx0zBu2VS09LGzgAn2J42ov9189QeX8WrUtoSro77CpeuER1WaQhSoHXa3M7axIG2x67+Y6QgrNNjgAiT0PVCW9ukp9tnc7sf3W3uSCHvLOwTsBp40q7dftMpHfGb1z6L8vS/vDAoyVVNrwmDUCarb3xb30mwYYDuBe/1HaCF7WpLV5ZYy6HItiLsI4UQv2uebAgMBAAECggEAaKOC7TJscR30rOe2aCRzSVB/wlUI7RbOjtoq41dVWVRNOz3ECtFMynxKIqsvB082YZq1k2lsPQvZIRQrpuwp8P6z8AwWhSOJ1Eg3Kk0o8BIohQiocPNgL2lzCoq/zyaetofOweaElZdek2bocL7E3wua0QcUftd3CRrVJxuRkP5vg1lA/2/m+ttdL2btSNvyienosTS/xweWn6EFdpifW/2BZHcHK0UskMKDdxm4hnMn+uyXocszZL46b6Wcji4cOLCP6fFjjzHCC4HMfE2WSvHJ61gHm/c16Q7dRiBWLn3p+Ea2c4EXKwgSSFApCF2yraMkpXBhEIeaTxA0W+5SAQKBgQDsYZR9aMAZEZOtqZgshYDOganxZm5xKhhR4nX9lU3Cq20iRq2A7Cy6pbBcxja3EE9l5EUO0uO77b/luHyW5fsd8iGHDmA9NJaEL+kD3Dp4wo5o55zsMtF3JVdMpNLPDAXr57gkftRp4GWu6bOMY5tAwEFaK7N6BqFBNaX3AjjuawKBgQC0Z2+izhSGhu1cdocqEbU12PLXWSc1R0COjI9TrRjf1BH7h87OxMaAl1pP7irq6WFHfOK6iHu+CLPEhqrC2D+88nPpURHJKeQpQXBqLQVg/u7fH+XLymRoj53hnC0IGCfQ4dVWdeFRZ5V6JC6ncj7nWw18WQ9R4/1llhOU8CDXkQKBgQCQt5EMUmRBNMSf7pOutjFMvI+Jrjdxat8myqmIDa9kvkT7ViAFLRKZVrUpp2R6IS8q51zsHWq1dHImtxL342chR0sFf2SmNBruZNKYvWzC4AIF8b7d0KLy+dZ/ou05O1govF29YjSzeWFne8aj7ZtwQgQAq36e944j+S9ZkW+/bwKBgELm2Q+XPSVmhD/19Mo1Ju6BNg2GknO/qME0fzKYztCy5LhS9O7ZKoQlnOhneqMDSYApYCus0bb3ozMOfeUkLP71KucrmVzwnBxwmC/H4mbGI++69I1O0U21dOdl84086AceuO8HaDe4IkbZhDO0un8x1R54KKZF1oMF1N027/cBAoGBAM9R2FlhQxy5dlyqQd2d3nzwUGBz37er637Dn+bQNVLk8i0fJhLBEk61KjmR+6dwlCI0+Ny3BdbXmw7W+Q1HCoeljdtAdQCJdxiFb6nqfyUfJtQXz+PV8FnqxeB8PL5PbjC3RehayhEdk9dHjS02QMckwYvkEFuX9woroscwtCuP";

    /**
     * 3.支付宝公钥
     */
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyDknZTX2y3c12zJxke34jTjrI95u8RykN66k7K6eEm9c7msjUrtTur0yfHuhH7fmBPTQNaXSN8rDzzUTh30FKOlHfxABIssKdMdZ2jptYWjO2oC0bDGogynmmZwSUy2v8QcbrYbstQgSlvBz+AW1RBkS/uQ5Wt1+IbwnyHwMLkDKQJ89yCflDq8n8Xtt5Wxde8fkoJXCBdziWACkJBda1hylEtbanIySX+YDuLRamiMXtYgBwPBgTL2BRigpSRXWekDQqad62X58lWSdYtaxbcLHa8taUmVydoyEyLISi/ckzL8hGES5VvotQduTV+j4fa1VHpuuRg0Wes0A9twbfwIDAQAB";

    /**
     * 4.服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
     */
    //这个着重讲一下..支付完成后,支付宝会通过这个url请求到你的服务端..
    //这个url一定是要公网可以访问才行...如果需要测试的话..我后面有讲到..
    //这里你可以先写你本地项目的url 例如:localhost:8080/项目名/访问路径
    public static String NOTIFY_URL = "http://2hu4349021.wicp.vip/pay/aliNotify";

    /**
     * 5.页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
     */
    //这里同上..不做详细说明了..
    public static String RETURN_URL = "http://2hu4349021.wicp.vip/pay/returnUrl";

    /**
     * 正式环境支付宝网关，如果是沙箱环境需更改成https://openapi.alipaydev.com/gateway.do
     */
    public static String SERVER_URL = "https://openapi.alipaydev.com/gateway.do";

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
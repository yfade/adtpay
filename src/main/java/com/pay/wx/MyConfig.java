package com.pay.wx;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class MyConfig extends WXPayConfig {

    private byte[] certData;

    public MyConfig() throws Exception {
        /*String certPath = "/path/to/apiclient_cert.p12";
        File file = new File(certPath);
        InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();*/
    }

    public String getAppID() {
        return "wx426b3015555a46belllllllllllllll";
    }

    public String getMchID() {
        return "1900009851";
    }

    public String getKey() {
        return "8934e7d15453e97507ef794cf7b0519d";
    }

    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    public int getHttpReadTimeoutMs() {
        return 10000;
    }

    @Override
    IWXPayDomain getWXPayDomain() {
        return new IWXPayDomain() {
            @Override
            public void report(String domain, long elapsedTimeMillis, Exception ex) {

            }

            @Override
            public DomainInfo getDomain(WXPayConfig config) {
                return new DomainInfo(WXPayConstants.DOMAIN_API, true);
            }
        };
    }
}

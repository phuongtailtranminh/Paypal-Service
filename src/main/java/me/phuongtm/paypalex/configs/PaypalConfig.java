package me.phuongtm.paypalex.configs;

import me.phuongtm.paypalex.enums.PaypalMode;

public class PaypalConfig {

    private String clientId;
    private String clientSecret;
    private PaypalMode mode;

    public PaypalConfig(String clientId, String clientSecret, PaypalMode mode) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.mode = mode;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public void setMode(PaypalMode mode) {
        this.mode = mode;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public PaypalMode getMode() {
        return mode;
    }

}

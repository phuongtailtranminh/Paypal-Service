package me.phuongtm.paypalex.configs;

import com.paypal.api.payments.Item;
import me.phuongtm.paypalex.enums.Currency;
import me.phuongtm.paypalex.enums.PaymentIntent;
import me.phuongtm.paypalex.enums.PaymentMethod;

import java.util.List;

public class PaymentConfig {

    private PaymentMethod paymentMethod;
    private PaymentIntent paymentIntent;
    private String successUrl;
    private String cancelUrl;
    private String description;
    private List<Item> itemList;
    private Currency currency;

    private PaymentConfig() { }

    public static PaymentConfig builder() {
        return new PaymentConfig();
    }

    public PaymentConfig withPaymentMethod(PaymentMethod method) {
        this.paymentMethod = method;
        return this;
    }

    public PaymentConfig withPaymentIntent(PaymentIntent intent) {
        this.paymentIntent = intent;
        return this;
    }

    public PaymentConfig withSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
        return this;
    }

    public PaymentConfig withCancelUrl(String cancelUrl) {
        this.cancelUrl = cancelUrl;
        return this;
    }

    public PaymentConfig withDescription(String description) {
        this.description = description;
        return this;
    }

    public PaymentConfig withItemList(List<Item> itemList) {
        this.itemList = itemList;
        return this;
    }

    public PaymentConfig withCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public PaymentConfig build() {
        return this;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public PaymentIntent getPaymentIntent() {
        return paymentIntent;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public String getCancelUrl() {
        return cancelUrl;
    }

    public String getDescription() {
        return description;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public Currency getCurrency() {
        return currency;
    }
}

package me.phuongtm.paypalex;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import me.phuongtm.paypalex.configs.PaymentConfig;
import me.phuongtm.paypalex.configs.PaypalConfig;
import me.phuongtm.paypalex.enums.Currency;
import me.phuongtm.paypalex.enums.PaymentMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PaypalService {

    private PaypalConfig config;
    private APIContext apiContext;

    public PaypalService(PaypalConfig config) {
        this.config = config;
        this.apiContext = new APIContext(config.getClientId(), config.getClientSecret(), config.getMode().name());
    }

    public APIContext getApiContext() {
        return apiContext;
    }

    public PaymentHistory getPaymentHistory(Map<String, String> queryParam) throws PayPalRESTException {
        return Payment.list(this.apiContext, queryParam);
    }

    public Payment getPaymentById(String id) throws PayPalRESTException {
        return Payment.get(this.apiContext, id);
    }

    public DetailedRefund refund(String saleId, RefundRequest refundRequest) throws PayPalRESTException {
        Sale sale = new Sale();
        sale.setId(saleId);
        return sale.refund(apiContext, refundRequest);
    }

    public Payment makePayment(PaymentConfig config) throws PayPalRESTException {
        List<Item> itemList = config.getItemList();
        // Build transaction info
        Amount amount = new Amount();
        amount.setCurrency(config.getCurrency().name());
        amount.setTotal(calTotalAndSetCurrency(itemList, config.getCurrency()));
        Transaction transaction = new Transaction();
        // Avoid concurrency request
        transaction.setInvoiceNumber(UUID.randomUUID().toString());
        transaction.setAmount(amount);
        transaction.setDescription(config.getDescription());
        transaction.setItemList(buildItemList(itemList));
        List<Transaction> listTransactions = new ArrayList<Transaction>();
        listTransactions.add(transaction);
        // Build payment info
        Payment payment = new Payment();
        payment.setIntent(config.getPaymentIntent().name());
        payment.setPayer(buildPayer(config.getPaymentMethod()));
        payment.setRedirectUrls(buildRedirectUrls(config.getSuccessUrl(), config.getCancelUrl()));
        payment.setTransactions(listTransactions);
        return payment.create(apiContext);
    }

    private Payer buildPayer(PaymentMethod paymentMethod) {
        Payer payer = new Payer();
        payer.setPaymentMethod(paymentMethod.name());
        return payer;
    }

    private RedirectUrls buildRedirectUrls(String successUrl, String cancelUrl) {
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setReturnUrl(successUrl);
        redirectUrls.setCancelUrl(cancelUrl);
        return redirectUrls;
    }

    private String calTotalAndSetCurrency(List<Item> itemList, Currency currency) {
        double total = 0;
        for (Item item : itemList) {
            // Item currency is required
            // Item currency must be matched with transaction currency
            item.setCurrency(currency.name());
            total+= Double.valueOf(item.getPrice()) * Integer.valueOf(item.getQuantity());
        }
        return String.format("%.2f", total);
    }

    private ItemList buildItemList(List<Item> listItems) {
        ItemList itemList = new ItemList();
        itemList.setItems(listItems);
        return itemList;
    }

}

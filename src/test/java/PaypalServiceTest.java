import com.paypal.api.payments.Item;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentHistory;
import com.paypal.base.rest.PayPalRESTException;
import me.phuongtm.paypalex.PaypalService;
import me.phuongtm.paypalex.configs.PaymentConfig;
import me.phuongtm.paypalex.configs.PaypalConfig;
import me.phuongtm.paypalex.constants.QueryParam;
import me.phuongtm.paypalex.enums.Currency;
import me.phuongtm.paypalex.enums.PaymentIntent;
import me.phuongtm.paypalex.enums.PaymentMethod;
import me.phuongtm.paypalex.enums.PaypalMode;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaypalServiceTest {

    private final String CLIENT_ID = "Aaj2nn4HxtpuwCGYRHyyQWelZnJxZi9b5tLpOTGdPbujxqUWnJQS6npvXxITFyLCEleo67ARFzIFqIUP";
    private final String CLIENT_SECRET = "EPOHKInRIUK_ukHEBJqYRLMEua-gPaM8B4FM6qR4LO9Qvmi61CCV9g374EveibF2I2SpsYPF-gTDuq7t";
    private final PaypalMode MODE = PaypalMode.sandbox;

    private PaypalService service;

    @Before
    public void setUp() throws Exception {
        PaypalConfig config = new PaypalConfig(CLIENT_ID, CLIENT_SECRET, MODE);
        service = new PaypalService(config);
    }

    @Test
    public void shouldCreatePaymentSuccessful() throws PayPalRESTException {
        List<Item> listItem = new ArrayList<Item>();
        // Item 1
        Item item = new Item();
        item.setName("Item 1");
        item.setPrice("1.02");
        item.setQuantity("5");
        // Item 2
        Item item2 = new Item();
        item2.setName("Item 2");
        item2.setPrice("4.01");
        item2.setQuantity("1");
        listItem.add(item);
        listItem.add(item2);
        PaymentConfig paymentConfig = PaymentConfig
                .builder()
                .withPaymentIntent(PaymentIntent.SALE)
                .withPaymentMethod(PaymentMethod.PAYPAL)
                .withCancelUrl("http://cancel")
                .withSuccessUrl("http://success")
                .withCurrency(Currency.USD)
                .withItemList(listItem)
                .build();
        Payment payment = service.makePayment(paymentConfig);
        System.out.println(payment.getLinks());
    }

    @Test
    public void shouldReturnPaymentHistory() throws PayPalRESTException {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put(QueryParam.COUNT, "10");
        PaymentHistory paymentHistory = service.getPaymentHistory(queryParam);
        System.out.println(paymentHistory.toJSON());
    }

}

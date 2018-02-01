# Paypal-Service
Enhanced Paypal Java SDK

### Register your service
> PaypalConfig config = new PaypalConfig(CLIENT_ID, CLIENT_SECRET, PaypalMode.sandbox);
> PaypalService service = new PaypalService(config);

### Create your payment
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

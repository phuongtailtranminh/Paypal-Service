# Paypal-Service
Enhanced Paypal Java SDK

#### Create Paypal Developer Account & REST API App

Go to: https://developer.paypal.com/developer/accounts/
Then navigate: My Apps & Credentials -> REST API apps -> Create App
Select the one that you've just created to get CLIENT_ID and CLIENT_SECRET
Change it to LIVE when your app in production mode

#### Register your service
        PaypalConfig config = new PaypalConfig(CLIENT_ID, CLIENT_SECRET, MODE);
        PaypalService service = new PaypalService(config);

#### Create your payment
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
        System.out.println(payment.getLinks().get(1).getHref());

#### Get Confirmation Link & Redirect User to it
        payment.getLinks().get(1).getHref()
        
#### Write Controller with these signature to handle success and cancel cases

        @GetMapping("/success") // match withSuccessUrl property of PaymentConfig object
        public void handleSuccess(@RequestParam("paymentId") String paymentId,
                                           @RequestParam("PayerID") String payerId) {}
                                          
        @GetMapping("/cancel") // match withCancelUrl property of PaymentConfig object
        public void handleCancel(@RequestParam("token") String token)

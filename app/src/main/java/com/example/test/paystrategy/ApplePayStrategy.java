package com.example.test.paystrategy;

public class ApplePayStrategy implements PayStrategy {

    @Override
    public String getName() {
        return "ApplePay";
    }

    @Override
    public int getDiscountedAmount(int price) {
        return (int) (price * 0.8);
    }
}

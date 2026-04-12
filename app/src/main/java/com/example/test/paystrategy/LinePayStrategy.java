package com.example.test.paystrategy;

public class LinePayStrategy implements PayStrategy {
    @Override
    public String getName() {
        return "LinePay";
    }

    @Override
    public int getDiscountedAmount(int price) {
        return price;
    }
}

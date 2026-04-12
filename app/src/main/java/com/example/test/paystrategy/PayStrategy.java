package com.example.test.paystrategy;

public interface PayStrategy {

    String getName();

    int getDiscountedAmount(int price);
}

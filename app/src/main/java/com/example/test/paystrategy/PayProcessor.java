package com.example.test.paystrategy;

import androidx.annotation.NonNull;

public class PayProcessor {
    private PayProcessor() {
    }

    public static String process(int price, @NonNull PayStrategy strategy) {
        return String.format("使用 %s 付款 %s 元", strategy.getName(), strategy.getDiscountedAmount(price));
    }
}

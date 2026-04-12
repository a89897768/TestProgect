package com.example.test;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test.blindbox.BlindBoxManager;
import com.example.test.blindbox.IBlindBoxDecorator;
import com.example.test.blindboxfactory.ToyBlindBoxFactory;
import com.example.test.paystrategy.ApplePayStrategy;
import com.example.test.paystrategy.LinePayStrategy;
import com.example.test.paystrategy.PayProcessor;
import com.example.test.paystrategy.PayStrategy;

public class MainActivity extends AppCompatActivity {
    private static final int BLIND_BOX_PRICE = 100;

    private TextView mPaymentDetails;
    private TextView mProductContent;
    private TextView mToyBoxName;
    private TextView mToyName;
    private final BlindBoxManager.IEventListener mToyBoxBlindBoxManagerEventListener = createToyBoxBlindBoxManagerEventListener();
    private final BlindBoxManager.IEventListener mToyBlindBoxManagerEventListener = createToyBlindBoxManagerEventListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPaymentDetails = findViewById(R.id.id_main_payment_details_value);
        mProductContent = findViewById(R.id.id_main_product_content);
        mToyBoxName = findViewById(R.id.id_main_toy_box_name);
        mToyName = findViewById(R.id.id_main_toy_name);

        View linePayBuy = findViewById(R.id.id_main_line_pay_buy);
        linePayBuy.setOnClickListener(createBuyClickListener(new LinePayStrategy()));

        View applePayBuy = findViewById(R.id.id_main_apple_pay_buy);
        applePayBuy.setOnClickListener(createBuyClickListener(new ApplePayStrategy()));

        BlindBoxManager.getInstance().addEventListener(mToyBoxBlindBoxManagerEventListener);
        BlindBoxManager.getInstance().addEventListener(mToyBlindBoxManagerEventListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BlindBoxManager.getInstance().removeEventListener(mToyBoxBlindBoxManagerEventListener);
        BlindBoxManager.getInstance().removeEventListener(mToyBlindBoxManagerEventListener);
    }

    private void buyBlindBox(PayStrategy strategy) {
        IBlindBoxDecorator blindBox = ToyBlindBoxFactory.createRandomToyBlindBox();
        BlindBoxManager.getInstance().setToyBlindBox(blindBox);
        mPaymentDetails.setText(PayProcessor.process(BLIND_BOX_PRICE, strategy));
        mProductContent.setText(blindBox.getContent());
    }

    private View.OnClickListener createBuyClickListener(PayStrategy strategy) {
        return v -> buyBlindBox(strategy);
    }

    private BlindBoxManager.IEventListener createToyBoxBlindBoxManagerEventListener() {
        return toyBlindBox -> mToyBoxName.setText(toyBlindBox == null ? "沒有盒子" : toyBlindBox.getToyBox().getName());
    }

    private BlindBoxManager.IEventListener createToyBlindBoxManagerEventListener() {
        return toyBlindBox -> mToyName.setText(toyBlindBox == null ? "沒有玩具" : toyBlindBox.getToy().getName());
    }
}

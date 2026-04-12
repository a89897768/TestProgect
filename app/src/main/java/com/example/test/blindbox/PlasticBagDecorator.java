package com.example.test.blindbox;

import com.example.test.toy.IToy;
import com.example.test.toy.IToyBox;

public class PlasticBagDecorator implements IBlindBoxDecorator {
    private final IBlindBoxDecorator mBlindBoxDecorator;

    public PlasticBagDecorator(IBlindBoxDecorator blindBoxDecorator) {
        mBlindBoxDecorator = blindBoxDecorator;
    }

    @Override
    public IToyBox getToyBox() {
        return mBlindBoxDecorator.getToyBox();
    }

    @Override
    public IToy getToy() {
        return mBlindBoxDecorator.getToy();
    }

    @Override
    public String getContent() {
        return "塑膠袋 + " + mBlindBoxDecorator.getContent();
    }
}

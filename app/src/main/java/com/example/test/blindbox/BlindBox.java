package com.example.test.blindbox;

import com.example.test.toy.IToy;
import com.example.test.toy.IToyBox;

public class BlindBox implements IBlindBoxDecorator {
    private final IToyBox mToyBox;
    private final IToy mToy;

    public BlindBox(IToyBox toyBox, IToy toy) {
        mToyBox = toyBox;
        mToy = toy;
    }

    @Override
    public IToyBox getToyBox() {
        return mToyBox;
    }

    @Override
    public IToy getToy() {
        return mToy;
    }

    @Override
    public String getContent() {
        return "盲盒";
    }
}

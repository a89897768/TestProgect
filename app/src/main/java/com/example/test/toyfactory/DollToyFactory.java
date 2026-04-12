package com.example.test.toyfactory;

import com.example.test.toy.DollToy;
import com.example.test.toy.GlassToyBox;
import com.example.test.toy.IToy;
import com.example.test.toy.IToyBox;

public class DollToyFactory implements IToyFactory {
    @Override
    public IToyBox createToyBox() {
        return new GlassToyBox();
    }

    @Override
    public IToy createToy() {
        return new DollToy();
    }
}

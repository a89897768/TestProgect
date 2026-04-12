package com.example.test.toyfactory;

import com.example.test.toy.CarToy;
import com.example.test.toy.CartonToyBox;
import com.example.test.toy.IToy;
import com.example.test.toy.IToyBox;

public class CarToyFactory implements IToyFactory {
    @Override
    public IToyBox createToyBox() {
        return new CartonToyBox();
    }

    @Override
    public IToy createToy() {
        return new CarToy();
    }
}

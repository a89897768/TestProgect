package com.example.test.toyfactory;

import com.example.test.toy.IToy;
import com.example.test.toy.IToyBox;
import com.example.test.toy.PlasticToyBox;
import com.example.test.toy.RobotToy;

public class RobotToyFactory implements IToyFactory {
    @Override
    public IToyBox createToyBox() {
        return new PlasticToyBox();
    }

    @Override
    public IToy createToy() {
        return new RobotToy();
    }
}

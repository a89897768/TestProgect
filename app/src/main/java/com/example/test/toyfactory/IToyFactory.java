package com.example.test.toyfactory;

import com.example.test.toy.IToy;
import com.example.test.toy.IToyBox;

public interface IToyFactory {
    IToyBox createToyBox();

    IToy createToy();
}

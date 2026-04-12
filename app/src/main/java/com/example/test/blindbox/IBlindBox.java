package com.example.test.blindbox;

import com.example.test.toy.IToy;
import com.example.test.toy.IToyBox;

public interface IBlindBox {
    IToyBox getToyBox();

    IToy getToy();
}

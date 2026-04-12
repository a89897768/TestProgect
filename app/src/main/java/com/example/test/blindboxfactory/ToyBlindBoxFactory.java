package com.example.test.blindboxfactory;

import androidx.annotation.NonNull;

import com.example.test.blindbox.BlindBox;
import com.example.test.blindbox.IBlindBoxDecorator;
import com.example.test.blindbox.PlasticBagDecorator;
import com.example.test.toy.ToyType;
import com.example.test.toyfactory.CarToyFactory;
import com.example.test.toyfactory.DollToyFactory;
import com.example.test.toyfactory.IToyFactory;
import com.example.test.toyfactory.RobotToyFactory;

public class ToyBlindBoxFactory {

    private ToyBlindBoxFactory() {
    }

    public static IBlindBoxDecorator createRandomToyBlindBox() {
        ToyType[] toyTypeArray = ToyType.values();
        int randomIndex = (int) (Math.random() * toyTypeArray.length);
        ToyType toyType = toyTypeArray[randomIndex];
        return new PlasticBagDecorator(createToyBlindBox(toyType));
    }

    public static @NonNull IBlindBoxDecorator createToyBlindBox(@NonNull ToyType type) {
        IToyFactory toyFactory;
        switch (type) {
            case Car: {
                toyFactory = new CarToyFactory();
                break;
            }
            case Robot: {
                toyFactory = new RobotToyFactory();
                break;
            }
            case Doll: {
                toyFactory = new DollToyFactory();
                break;
            }
            default: {
                throw new IllegalArgumentException("新增卻沒處理到的玩具類型");
            }
        }

        return new BlindBox(toyFactory.createToyBox(), toyFactory.createToy());
    }
}

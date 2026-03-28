package com.example.test;

public class Singleton {
    private static final Singleton mInstance = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        return mInstance;
    }
}

//當時一直說之前練習都習慣寫在同一個裡面，要求在同一個裡面做，就給他在同一個裡面寫看看
interface CellPhone{
    //一樣的問題，沒有存取修飾符，講Singleton的時候也都講過了，還是一樣
    void createMonitor();
}

class APhone implements CellPhone{
    //一樣的問題，沒有存取修飾符，講Singleton的時候也都講過了，還是一樣
    @Override
    public void createMonitor() {
        System.out.println("製造螢幕！");
    }
}

//只做一個APhone沒有體現做工廠的用處
class Factory{
    //一樣的問題，沒有存取修飾符，講Singleton的時候也都講過了，還是一樣
    APhone mAPhone = new APhone();
    //一樣的問題，沒有存取修飾符，講Singleton的時候也都講過了，還是一樣
    //跟著工廠new出來，不會有第二個實例，只會一直使用同一個東西

    public void createPhone(){
        //沒有參數，無法體現工廠的價值
        //沒有回傳值，工廠沒用處，工廠就是要生產，沒回傳值相當於沒有生產
        mAPhone.createMonitor();
    }
}

Factory f = new Factory();
f.createPhone();
//不再程式碼區塊中無法執行方法，這甚至不在類別區塊中
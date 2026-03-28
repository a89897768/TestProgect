package com.example.test;

public class Factory {
    interface CellPhone{
        void createMonitor();
    }

    class APhone implements CellPhone{
        @Override
        public void createMonitor() {
            System.out.println("製造螢幕！");
        }
    }

    APhone mAPhone = new APhone();

    public void createPhone(){
        mAPhone.createMonitor();
    }
}

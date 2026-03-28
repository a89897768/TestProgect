package com.example.test;

public class Factory {
    public APhone mAPhone = new APhone();

    public interface CellPhone{
        void createMonitor();
    }

    public class APhone implements CellPhone{
        @Override
        public void createMonitor() {
            System.out.println("製造螢幕！");
        }
    }

    public void createPhone(){
        mAPhone.createMonitor();
    }
}

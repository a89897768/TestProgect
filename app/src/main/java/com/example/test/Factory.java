package com.example.test;

public class Factory {
    public APhone mAPhone = new APhone();
    public BPhone mBPhone = new BPhone();

    public interface CellPhone{
        void createMonitor();
    }

    public class APhone implements CellPhone{
        @Override
        public void createMonitor() {
            System.out.println("製造螢幕！");
        }
    }

    public class BPhone implements CellPhone{
        @Override
        public void createMonitor() {
            System.out.println("製造螢幕！");
        }
    }

    public void createPhone(String name){
        if(name.equals("A")){
            mAPhone.createMonitor();
        }else{
            mBPhone.createMonitor();
        }
    }
}

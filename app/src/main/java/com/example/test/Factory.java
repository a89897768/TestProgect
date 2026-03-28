package com.example.test;

public class Factory {
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
            APhone mAPhone = new APhone();
            mAPhone.createMonitor();
        }else{
            BPhone mBPhone = new BPhone();
            mBPhone.createMonitor();
        }
    }
}

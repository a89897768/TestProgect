package com.example.test;

public class Singleton {
    //到這裡是IDE自己建立的沒有出問題
    public void instance = new Singleton();
    /*
        CodeStyle上週5講過，周2被Glynn提醒，詢問說有誤會，也解開誤會
        檢核時還是沒完善，這裡沒加m，除非設計上成員單純是要直接存取，例如 data.xqId、holder.textView
    */

    //中途也不知道 new Singleton()，最後想很久填了出來，但這與他後續的設計衝突
    //不知道成員宣告要怎麼寫，但他TextView就知道怎麼寫，一直填void，問他為什麼填這個他說「因為沒有回傳值」
    /*
        知道修飾符的意思，但都亂填，例如這邊他知道public是公開給外部存取，但他把它的instance成員開放給外部存取
        相當於下面的getInstance沒有用
    */
    //沒有static修飾符，根本無法成為單例
    public Singleton(){}
    //一樣的問題，存取修飾符隨便設，建構子開放外部執行根本無法成為單例

    public void getInstance(){
        //沒有static修飾符，外部根本無法取得實例
        //知道void是沒有回傳值還是亂填，問應該填什麼，一下String一下int隨便猜
        if(instance == null){
            //上面有創建實例，這裡根本進不來，懶加載跟積極加載混為一罈
            instance = new Singleton();
        }

        return getInstance();
        //不知道為什麼搞出遞迴，自己命名都已經叫get「Instance」，卻不知道要回傳instance
        //依然反應出根本不知道「自己在做什麼」，就只是在「默寫單例模式的範例」而已
        //明明有教要習慣使用ReFormat Code，看起來還是沒在按
    }
}

package com.wyp.innsky;

/**
 * author:wangyp
 * Date:2018/12/1
 * Description:
 */
public enum  Week {

    Monday("monday",1);


    private String day;
    private int num;

    Week(String day,int num){
        this.day = day;
        this.num = num;
    }

    public boolean isMonday(){
       return this == Monday;
    }
}

package com.wyp.innsky;

/**
 * author:wangyp
 * Date:2018/12/3
 * Description:
 */
public enum  Week {

    Monday("monday", 1);

    private String day;
    private int num;

    private Week(String var3, int var4) {
        this.day = var3;
        this.num = var4;
    }

    public boolean isMonday() {
        return this == Monday;
    }
}

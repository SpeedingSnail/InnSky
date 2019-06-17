package com.wyp.innsky;

/**
 * author:wangyp
 * Date:2018/12/3
 * Description:
 */
public enum  Type {
    SELECT{
        @Override
        public void tt(String str) {
            
        }
    }, UPDATE{
        @Override
        public void tt(String str) {

        }
    };


    public abstract void tt(String str);
}

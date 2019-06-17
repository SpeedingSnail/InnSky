package com.wyp.innsky;


/**
 * author:wangyp
 * Date:2018/12/3
 * Description:
 */
public class JavaTest {

    public static void main(){
        Fulit fulit = Fulit.Apple;
    }

    enum Fulit{
        Apple("apple",1);

        private String name;
        private int weight;

        Fulit(String name,int weight){
            this.name = name;
            this.weight = weight;
        }
    }
}

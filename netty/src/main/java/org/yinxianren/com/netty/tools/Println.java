package org.yinxianren.com.netty.tools;

public interface Println {
    default void println(Object obj){
        System.out.println(obj);
    }

    default void print(Object obj){
        System.out.print(obj);
    }
}

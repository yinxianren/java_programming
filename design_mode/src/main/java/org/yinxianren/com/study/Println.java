package org.yinxianren.com.study;

public interface Println {
    default void println(Object obj){
        System.out.println(obj);
    }
}

package org.yinxianren.springboot.tools;

public interface Println {
    default void println(Object obj){
        System.out.println(obj);
    }
}

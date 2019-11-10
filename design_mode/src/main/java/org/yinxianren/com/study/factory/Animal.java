package org.yinxianren.com.study.factory;

public interface Animal {

    void makeSound();

    default void println(String str){
        System.out.println(str);
    }
}

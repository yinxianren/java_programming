package org.yinxianren.com.study.factory.simple;

public interface Animal {

    void makeSound();

    default void println(String str){
        System.out.println(str);
    }
}

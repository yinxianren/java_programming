package org.yinxianren.com.study.facade;

public interface TestInterface {
    void test();
    default void println(String str){
        System.out.println(str);
    }
}

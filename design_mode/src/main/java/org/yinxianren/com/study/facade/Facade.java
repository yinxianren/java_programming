package org.yinxianren.com.study.facade;

public class Facade {

    public void invokeAll(){
        new TestA().test();
        new TestC().test();
        new TestB().test();
    }


}

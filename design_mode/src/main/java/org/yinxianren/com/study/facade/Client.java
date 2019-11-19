package org.yinxianren.com.study.facade;

import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;

public class Client {

    @Test
    public void test(){
        new Facade().invokeAll();
    }

}

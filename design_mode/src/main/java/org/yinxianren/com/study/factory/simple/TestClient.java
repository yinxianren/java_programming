package org.yinxianren.com.study.factory.simple;

import org.junit.Test;

public class TestClient {


    /**
     *  测试简单工厂
     *
     *  测试结果：
     * I'm a cart,meow meow..
     * animal(1)-->1323468230
     * animal(2)-->1645995473
     *
     * 总结：
     *   通过java反射一个对象，得到的是一个新的对象，所以他们的hash值也就不一样；
     *
     */
    @Test
    public void testSimpleFactory(){
        Animal animal = SimpleFactory.createAnimal(Cart.class);
        animal.makeSound();
        animal.println(String.valueOf("animal(1)-->"+animal.hashCode()));
        Animal animalTwo = SimpleFactory.createAnimal(Cart.class);
        animal.println(String.valueOf("animal(2)-->"+animalTwo.hashCode()));
    }






}

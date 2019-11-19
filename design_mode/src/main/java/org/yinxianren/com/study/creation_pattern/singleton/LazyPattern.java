package org.yinxianren.com.study.creation_pattern.singleton;
/**
 * 单例模式（懒汉式，线程不安全）
 * 线程不安全，没有加锁。
 * 此方式在确定是单线程的情况下，可以保证创建的对象唯一性
 */
public final class LazyPattern {

    private static LazyPattern intance = null;

    private LazyPattern(){}

    public static LazyPattern getIntance(){
        if( null == intance){
            intance = new LazyPattern();
        }
        return intance;
    }

    public void say() {
        System.out.println("Hello");
    }
}

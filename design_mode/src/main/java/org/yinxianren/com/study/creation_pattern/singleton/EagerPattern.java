package org.yinxianren.com.study.creation_pattern.singleton;

/**
 * 单例模式（饿汉式）
 * 线程安全，没有加锁，执行效率会提高。
 * 类加载时就初始化，浪费内存，容易产生垃圾对象
 * <p>
 * 单例类使用了final修饰，防止被继承
 * 这种写法比较简单，就是在类装载的时候就完成实例化。instance作为类变量，
 * 在类初始化过程中，会被收集进<clinit>()方法中，该方法会保障同步，从而避免了线程同步问题。
 * 在类装载的时候就完成实例化，若从未使用过这个实例，则会造成内存的浪费。
 */
public final class EagerPattern {
    //创建 SingleObject1 的一个对象
    private static EagerPattern instance = new EagerPattern();
    //私有化构造函数，这样该类就不会被实例化
    private EagerPattern() {}
    //获取唯一可用的对象
    public static EagerPattern getInstance() {
        return instance;
    }
    public void say() {
        System.out.println("Hello");
    }
}

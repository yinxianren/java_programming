package org.yinxianren.com.study.factory.simple;

import org.yinxianren.com.study.factory.Animal;

/**
 * 简单静态工厂：
 *   私有构造器
 *   提供静态方法
 */
public class SimpleFactory {
    /**
     * 避免被创建多个实例
     */
    private  SimpleFactory(){

    }

    /**
     *  没有单例实现的简单静态工厂
     * @param clz
     * @return
     */
   public static Animal createAnimal(Class clz){
      Animal animal=null;
       try {
           animal = (Animal) clz.newInstance();
       } catch (Exception e) {
           e.printStackTrace();
       }
       return  animal;
   }
}

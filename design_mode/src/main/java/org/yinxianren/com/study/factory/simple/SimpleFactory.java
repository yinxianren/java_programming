package org.yinxianren.com.study.factory.simple;

public class SimpleFactory {

   static Animal createAnimal(Class clz){
      Animal animal=null;
       try {
           animal = (Animal) clz.newInstance();
       } catch (Exception e) {
           e.printStackTrace();
       }
       return  animal;
   }
}

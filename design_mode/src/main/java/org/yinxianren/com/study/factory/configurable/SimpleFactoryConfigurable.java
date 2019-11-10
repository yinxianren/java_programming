package org.yinxianren.com.study.factory.configurable;

import org.yinxianren.com.study.factory.Animal;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class SimpleFactoryConfigurable {

    private SimpleFactoryConfigurable(){

    }

    public static Animal createAnimal(){
        Animal animal = null;
        try {
            Properties properties = new Properties();
            InputStream inputStream = SimpleFactoryConfigurable.class.getResourceAsStream("application.properties");
            properties.load(inputStream);
            animal = (Animal) Class.forName(properties.getProperty("factory.obj.cart")).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return animal;
    }


}

package org.yinxianren.com.study.creation_pattern;

import lombok.Data;
import lombok.Getter;
import org.yinxianren.com.study.Println;
import org.yinxianren.com.study.creation_pattern.singleton.EagerPattern;
import org.yinxianren.com.study.creation_pattern.singleton.LazyPattern;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test  implements Println {

    @org.junit.Test
    public void testEagerPattern(){
        EagerPattern eagerPattern = EagerPattern.getInstance();
        eagerPattern.say();
        println(eagerPattern.hashCode());
        println(eagerPattern.toString());
    }

    @org.junit.Test
    public void testLazyPattern() throws InterruptedException {
        List<Tag> list = new ArrayList<>();
        for(int i = 0; i < 300; i++){
            String index = i+"";
            new Thread(()->{
                LazyPattern lazyPattern = LazyPattern.getIntance();
                String msg = String.format("i = %s,hashCode = %s",index,lazyPattern.hashCode());
                println(msg);
                list.add(new Tag()
                        .setMsg(msg)
                        .setHashCode(lazyPattern.hashCode()));
            }).start();

        }
        Thread.currentThread().join(3000);
        println("******************************************************************");
        list.stream().collect(Collectors.groupingBy(Tag::getHashCode)).forEach((k,v)->{
            println(v.get(0).getHashCode());
        });

    }

    @Getter
    private class Tag{
        private int hashCode;
        private String msg;

        public Tag setHashCode(int hashCode) {
            this.hashCode = hashCode;
            return this;
        }

        public Tag setMsg(String msg) {
            this.msg = msg;
            return this;
        }
    }

}


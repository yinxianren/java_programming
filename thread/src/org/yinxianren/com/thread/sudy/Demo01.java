package org.yinxianren.com.thread.sudy;

public class Demo01 {

    private  static int count = 10;

    private void test(){
        System.out.println(String.format("当前线程名：%s,count:%s",
                Thread.currentThread().getName(),--count));
    }


    public static void main(String[] args) throws InterruptedException {
        Demo01  d = new Demo01();
        for( int i = 1 ; i <= count ;i++ ){
            new Thread( ()->d.test(),"Thead-"+i).start();
        }
        Thread.sleep(5000);
    }
}

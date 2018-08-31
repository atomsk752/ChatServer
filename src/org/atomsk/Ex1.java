package org.atomsk;

public class Ex1 implements Runnable { //객체하나에 쓰레드 n개를 붙여보자




    @Override
    public void run() {

        for (int i = 0; i < 10000; i++) {

            System.out.println(Thread.currentThread().getName()+":"+this);

        }
    }

    public static void main(String[] args) {

        Ex1 obj = new Ex1();

        new Thread(obj).start();
        new Thread(obj).start();
        new Thread(obj).start();
        new Thread(obj).start();



    }



}

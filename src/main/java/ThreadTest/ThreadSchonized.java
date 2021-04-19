package ThreadTest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author hhj
 * @create 2021-04-08 20:01
 */
public class ThreadSchonized {
    public static void main(String[] args) throws InterruptedException {
        Log log=LogFactory.getLog(ThreadSchonized.class);
        log.info("1....");
//        Clerk temp =new Clerk();
//        //Thread a=new Thread(temp);
//        Thread b=new Thread(new Product(temp));
//        Thread c=new Thread(new Consumer(temp));
//
//        //a.setName("clerk");
//        b.setName("pro");
//        c.setName("con");
//        //a.start();
//        b.start();
//        c.start();

    }
}

class Count{
    public static Object lock=new Object();
    public static int count=0;
}

class AddThread extends Thread{
    @Override
    public void run() {
        for(int i=0;i<100;i++){
            synchronized (Count.lock){
                Count.count++;
            }
        }
    }
}

class DecThread extends Thread{
    @Override
    public void run() {
        for(int i=0;i<100;i++){
            synchronized (Count.lock){

                Count.count--;
            }


        }
    }
}

class addobj {
    public static int count =0;
    public void add(){

        synchronized (this){
            count++;
        }
    }
    public void dec(){
        synchronized (this){
            count--;
        }
    }


}

class window implements Runnable {
    private static int num=100;
    static ReentrantLock lock=new ReentrantLock();

    @Override
    public void run() {

        while (true){
            try {
                lock.lock();
                if(num>0){
                    System.out.println(Thread.currentThread()+":"+Integer.toString(num));
                    num--;
                }
            }finally {
                lock.unlock();
            }


    }
    }
}

class bank implements Runnable{
    private static int money=0;
    private static ReentrantLock lock =new ReentrantLock(true);
    @Override
    public void run() {
        for(int i=0;i<3;i++){
            try {
                lock.lock();
                money+=1000;
                System.out.println(Thread.currentThread()+":"+Integer.toString(money));
            }
            finally {
                lock.unlock();
            }
        }
    }
}


class Product implements Runnable{
    private int productNum = 0;
    private Clerk clerk;
    public Product(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(100);

                clerk.produce();

            }
            catch (InterruptedException e){
                e.printStackTrace();
            }

        }
    }
}


class Consumer implements Runnable{
    private Clerk clerk;
    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(200);
                clerk.consum();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }

        }
    }
}

class Clerk {
    private int productNum = 0;
    public synchronized void produce() {
        if(productNum<20){
            productNum++;
            System.out.println("pro:"+Integer.toString(productNum));
            notify();
        }
        else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
    public synchronized void consum() {
        if(productNum>0){
            System.out.println("con:"+Integer.toString(productNum));
            productNum--;
            notify();
        }
        else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



    }
}





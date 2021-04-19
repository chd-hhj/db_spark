package ThreadTest;


import java.time.LocalTime;

/**
 * @author hhj
 * @create 2021-04-08 16:20
 */
public class test {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread4();
        t.setDaemon(true);
        t.start();
        //t.interrupt();
        System.out.println("main1");
        //t.join(100);
        System.out.println("main2");
    }
}

class Thread2 extends Thread{
    @Override
    public void run() {
        Thread3 thread3 = new Thread3();
        thread3.start();

        try {
            thread3.join();
    }
        catch (InterruptedException e) {
            System.out.println("interrupted");
            thread3.isInterruptedValue=true;

        }

    }
}


class   Thread3 extends Thread{
    public boolean isInterruptedValue=false;

    @Override
    public void run() {
        int i=1;
        while (!isInterruptedValue){
            System.out.println("thread3:"+i);
            i++;
            try {
                Thread.sleep(100);
            }
            catch (InterruptedException e){
                break;
            }
        }
    }
}

class Thread4 extends Thread{
    @Override
    public void run() {
        while (true){
            System.out.println(LocalTime.now());
            try {
                Thread.sleep(100);
            }
            catch (InterruptedException e){
                break;
            }
        }
    }
}



package test.multithreading;


//by extending thread class
public class MultithreadingEx1 extends Thread{

    public void run() {
        System.out.println("thread is running");
    }

    public static void main(String args[]) {
        MultithreadingEx1 obj = new MultithreadingEx1();
        obj.start();

    }
}

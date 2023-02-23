package test.multithreading;

public class ImplRunnableInterface implements Runnable{

    public void run() {
        System.out.println("Thread is running fine");
    }

    public static void main(String ags[]) {
       ImplRunnableInterface obj = new ImplRunnableInterface();
       Thread tObj = new Thread(obj);
       tObj.start();
    }

}

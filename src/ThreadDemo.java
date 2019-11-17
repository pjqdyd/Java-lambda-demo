
public class ThreadDemo {

    //创建线程写法
    public static void main(String[] args) {

        //1
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        }).start();

        //lambda
        new Thread(() -> System.out.println("hello")).start();
    }

}

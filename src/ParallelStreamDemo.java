import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

public class ParallelStreamDemo {

    public static void main(String[] args) {

        //并行流是在一个多线程的情况下执行的(线程池), sequential产生串行流
        IntStream.range(1, 10).parallel().peek(System.out::println).count();

        //并行流使用的线程池是默认的ForkJoinPool.commonPool
        //默认的线程数是当前cpu的核心数
        //使用这个属性可以修改
        //System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "20");

        //使用自己的创建的线程池
        //ForkJoinPool pool = new ForkJoinPool(20);
        //pool.submit(() -> IntStream.range(1, 10).parallel().peek(System.out::println).count());
        //pool.shutdown();

    }

}

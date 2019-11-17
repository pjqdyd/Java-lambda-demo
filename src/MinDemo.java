import java.util.stream.IntStream;

public class MinDemo {

    //找出数组中的最小值
    public static void main(String[] args) {
        //传统做法
        int[] nums = {23, 43, 45, -55, 90, 21};
        int min = Integer.MAX_VALUE;
        for (int i : nums){
            if (i < min){
                min = i;
            }
        }
        System.out.println(min);

        //jdk8函数式编程
        int min2 = IntStream.of(nums).min().getAsInt();
        int min3 = IntStream.of(nums).parallel().min().getAsInt();
        System.out.println(min2);
        System.out.println(min3);

    }

}

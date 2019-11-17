import java.util.function.*;

public class FunctionDemo {

    public static void main(String[] args) {
        //断言函数
        Predicate<Integer> predicate = i -> i>0;
        IntPredicate intPredicate = i -> i>0;
        System.out.println(predicate.test(-9));

        //消费函数接口
        Consumer<String> consumer = s -> System.out.println(s);
        consumer.accept("输出的数据");

        //提供函数
        Supplier<String> stringSupplier = () -> "提供的字符串";
        String s = stringSupplier.get();
        System.out.println(s);

        //输入输出函数接口, String是输出类型
        Function<Integer, String> function = integer -> integer.toString() + "字符串";
        String s1 = function.apply(100);
        System.out.println(s1);

        //一元函数,输入输出相同
        UnaryOperator<Integer> operator = integer -> integer*2;
        Integer s2 = operator.apply(3);
        System.out.println(s2);

        //两个输入的函数,String是输出类型
        BiFunction<Integer, Long, String> biFunction = (integer, aLong) -> integer.toString() + aLong.toString();
        String s3 = biFunction.apply(100, 200L);
        System.out.println(s3);

        //二元函数, 输入输出相同
        BinaryOperator<Integer> binaryOperator = (i, j) -> i*j;
        Integer s4 = binaryOperator.apply(2, 3);
        System.out.println(s4);


    }

}

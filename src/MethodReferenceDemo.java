import java.util.function.Consumer;

public class MethodReferenceDemo {
    public static void test(String s){
        System.out.println("静态方法" + s);
    }
    public static void main(String[] args) {
        //方法引用
        Consumer<String> consumer = System.out::println;
        consumer.accept("接收的数据");

        //类静态方法引用
        Consumer<String> consumer2 = MethodReferenceDemo::test;
        consumer2.accept("接收的数据");

        //非静态方法, 使用对象实例引用, 或使用BiFunction使用
        // Xxx x = new Xxx();
        // Function<Integer, Integer> function = x::methodName;
        // function.apply(2);
        // BiFunction<Xxx, Integer, Integer> biFunction = Xxx::methodName;
        // biFunction.apply(x, 2);

        //构造方法的引用
        //Supplier<Xxx> supplier = Xxx::new;
        //Xxx x = supplier.get();

        //带参数的构造方法
        //Function<String, Xxx> = function = Xxx::new;
        //Xxx x = function.apply("构造参数");
    }
}

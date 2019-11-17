import java.util.function.Function;

//级联表达式和柯里化(函数返回函数)
public class CurryDemo {

    public static void main(String[] args) {

        //级联表达式
        Function<Integer, Function<Integer, Integer>> fun = x -> y -> x+y;
        System.out.println(fun.apply(2).apply(3));

    }

}

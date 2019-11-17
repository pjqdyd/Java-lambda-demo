import java.util.function.Consumer;

//变量引用
public class VarDemo {

    public static void main(String[] args) {
        String str = "字符串";
        Consumer<String> consumer = s -> System.out.println(s + str);
        consumer.accept("拼接");
    }

}

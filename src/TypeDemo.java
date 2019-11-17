
@FunctionalInterface
interface MyMath{
    int add(int x, int y);
}

public class TypeDemo {

    public static void main(String[] args) {

        //变量类型定义
        MyMath myMath = (x, y) -> x + y;

        //数组中
        MyMath[] myMaths = {(x, y) -> x + y};

        //强转
        Object myMath2 = (MyMath) (x, y) -> x + y;
    }

}

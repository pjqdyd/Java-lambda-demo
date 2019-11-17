import java.text.DecimalFormat;
import java.util.function.Function;

//@FunctionalInterface
//interface MoneyFormat{
//    String format(int i);
//}
//
//class MyMoney{
//    private final int money;
//    public MyMoney(int money){
//        this.money = money;
//    }
//    public void printMoney(MoneyFormat moneyFormat){
//        System.out.println("我的存款:" + moneyFormat.format(this.money));
//    }
//}

//使用jdk8提供的函数接口Function
class MyMoney{
    private final int money;
    public MyMoney(int money){
        this.money = money;
    }
    public void printMoney(Function<Integer, String> moneyFormat){
        System.out.println("我的存款:" + moneyFormat.apply(this.money));
    }
}

public class MoneyDemo {
//    public static void main(String[] args) {
//        MyMoney myMoney = new MyMoney(9999999);
//        myMoney.printMoney(i -> new DecimalFormat("#,###").format(i));
//    }

    public static void main(String[] args) {
        MyMoney myMoney = new MyMoney(9999999);
        Function<Integer, String> moneyFormat = integer -> new DecimalFormat("#,###").format(integer);
        myMoney.printMoney(moneyFormat);
        //支持链式调用
        myMoney.printMoney(moneyFormat.andThen(s -> "人民币" + s));

    }
}

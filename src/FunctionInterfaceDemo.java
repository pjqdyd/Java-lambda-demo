
//编译校验注解, 接口只有一个要实现的方法countNum
@FunctionalInterface
interface Interface1{
    int countNum(int i);

    //jdk8后接口可以有默认实现
    default int addNum(int i, int j){
        return i + j;
    }
}

public class FunctionInterfaceDemo {

    public static void main(String[] args) {

        Interface1 i1 = (i) -> i*2;
        Interface1 i2 = i -> i*2;
        Interface1 i3 = (int i) -> i*2;
        Interface1 i4 = (int i) -> {
            System.out.println(i);
            return i*2;
        };

        //调用默认方法
        int x = i1.addNum(1,2);
    }


}

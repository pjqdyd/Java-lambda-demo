import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamDemo {

    public static void main(String[] args) {
        //----流的创建----
        //集合: Collection.stream/parallelStream
        //数组: Arrays.stream
        //数字Stream: IntStream/LongStream.range/rangeClosed
        //随机对象: Random.ints/longs/doubles
        //自己创建: Stream.generate/iterate

        //集合创建:
        List<String> list = Arrays.asList("ab", "cd", "ef");
        list.stream();
        list.parallelStream();

        //数组创建:
        Arrays.stream(new int[]{1, 2, 3});

        //创建数字流
        IntStream.of(1, 2, 3);
        IntStream.rangeClosed(1, 10);

        //使用Random创建一个无限流
        new Random().ints().limit(10);
        //自己产生流
        Random random = new Random();
        Stream.generate(() -> random.nextInt()).limit(20);

        //----流的中间操作------
        //无状态操作: map/mapToXxx /flatMap/flatMapToXxx / filter / peek / unordered
        //有状态操作: distinct / sorted / limit / skip

        String[] strings = {"ab", "cdE", "Fghi"};
        //每个单词转小写
        Stream.of(strings).map(s -> s.toLowerCase()).forEach(System.out::println);
        //flatMap A->B属性(集合),最终得到A元素里面的所有B属性集合
        //获取每个s字符串,收集s的char数组为新的集合流
        Stream.of(strings).flatMap(s -> s.chars().boxed()).forEach(
                integer -> System.out.print((char) integer.intValue())
        );
        //peek 用于debug, 和forEach类似, 但不是终止操作
        Stream.of(strings).peek(System.out::println).forEach(System.out::println);

        //filter过滤, limit限流
        new Random().ints().filter(i -> i<100).limit(10).forEach(System.out::println);

        //------终止操作------
        //非短路操作: forEach /forEachOrdered /collect /toArray /reduce /min /max /count
        //短路操作: findFirst /findAny /allMatch /anyMatch /noneMatch
        //注意:在使用并行流时, 通常使用forEachOrdered来保证顺序

        //收集成list
        Stream.of(strings).collect(Collectors.toList());

        //使用reduce拼接'|'
        Optional<String> s = Stream.of(strings).reduce((s1, s2) -> s1 + "|" +s2);
        System.out.println(s.orElse("空"));

        //max返回最大, 传入一个比较器
        //Optional<String> max = Stream.of(strings).max((s1, s2) -> s1.length() - s2.length());
        Optional<String> max = Stream.of(strings).max(Comparator.comparingInt(String::length));

        //findFirst, 只返回第一个
        OptionalInt one = new Random().ints().findFirst();
        System.out.println(one.getAsInt());

    }

}

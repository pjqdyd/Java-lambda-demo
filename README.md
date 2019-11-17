
### JDK 8的新特性lambda函数式编程使用案例


#### 1.自带函数接口:

```
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
    String s = function.apply(100);
    System.out.println(s);

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
```

#### 2.方法引用:

```
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
```

#### 3.级联表达式:

```
    //级联表达式
    Function<Integer, Function<Integer, Integer>> fun = x -> y -> x+y;
    System.out.println(fun.apply(2).apply(3));
```

#### 4.Stream流式编程:

```
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
```

#### 5.Stream并行流:

```
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
```
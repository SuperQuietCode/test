package com.company;

import com.company.vo.User;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 定义一个list并赋值
 * 网站：
 * https://blog.csdn.net/qq_41170102/article/details/105654847?depth_1-utm_source=distribute.pc_category.none-task-blog-hot-5&request_id=&utm_source=distribute.pc_category.none-task-blog-hot-5
 *
 * @author ZhaoXin
 * @date 2020/4/22 14:46
 */
public class controller {
    public static void main(String[] args) throws IOException {
        List<User> list = new ArrayList<User>() {
            {
                add(new User(5, "ckhhh", "123123"));
                add(new User(1, "zhangsan", "1232312"));
                add(new User(3, "lisi", "000000"));
                add(new User(2, "lisi", "4324324"));
                add(new User(4, "wangwu", "99999"));
            }
        };

        /*
            filter的使用,按照指定条件过滤集合
            显示值为lisi的信息
         */
        list.stream().filter(user -> user.getUserName().equals("lisi")).
                forEach(user ->
                        System.out.println(user.toString()));
        System.out.println("-------------------------------");
        /*
            map使用 从一种类型转换为另一种类型的集合 user-string
         */
        list.stream().map(user -> user.getUserName()).forEach(user -> System.out.println(user));
        System.out.println("-------------------------------");

        /*
            flatMap使用 接受一个元素返回一个新的流 -- 将对象转换为一个流操作
            将元素分割成字符然后打印
         */
        list.stream().flatMap(user -> Arrays.stream(user.getUserName().split(""))).
                forEach(user -> System.out.println(user));
        System.out.println("-------------------------------");

        /*
            peek操作 无状态的 操作完成之后流还可以使用，foreach使用之后就不能用了
            peek和foreach交替打印
         */
        list.stream().peek(user -> System.out.println(user.getUserName())).
                forEach(user -> System.out.println(user.toString()));
        System.out.println("-------------------------------");

        /*
            sort操作，对数据按照规则排序
            sorted是有状态的  所以会等peek结束之后再操作
            peek和foreach就不会再交替打印了
         */
        list.stream().peek(user -> System.out.println(user.getUserName())).
                sorted(Comparator.comparing(User::getUserId)).
                forEach(user -> System.out.println(user.toString()));
        System.out.println("-------------------------------");

        /*
            distinct 操作 去重
         */
        list.stream().map(user -> user.getUserName()).distinct().forEach(user -> System.out.println(user));
        System.out.println("-------------------------------");

        /*
            skip操作 过滤
            按照id排序后过滤掉前三条
         */
        list.stream().sorted(Comparator.comparing(user -> user.getUserId())).skip(3).
                forEach(user -> System.out.println(user.toString()));
        System.out.println("-------------------------------");

        /*
            limit操作  获取前三条
         */
        list.stream().sorted(Comparator.comparing(user -> user.getUserId())).limit(3)
                .forEach(user -> System.out.println(user.toString()));
        System.out.println("-------------------------------");
        System.out.println("==========短路操作==========");

        /*
            allMatch 是否全部满足终端操作，短路操作
            如果发现不匹配的，就不会继续执行了，peek打印的数据到不符合这块就停止了

            判断是否所有的用户id都大于2，一旦发现小于等于2的就返回false
         */
        boolean b = list.stream().peek(user -> System.out.println(user.getUserId()))
                .allMatch(user -> user.getUserId() > 2);
        System.out.println(b);
        System.out.println("-------------------------------");

        /*
            anyMatch使用：只要又满足的就返回true
            短路的  找到一个符合的就返回true
            判断是否有用户id大于2，一旦发现大于2的就返回true
         */
        boolean c = list.stream().peek(user -> System.out.println(user.getUserId()))
                .anyMatch(user -> user.getUserId() > 2);
        System.out.println(c);
        System.out.println("-------------------------------");

        /*
            noneMatch操作 所有的都没有匹配上就返回true
            短路的 找到一个符合条件的就返回false
            判断是否没有用户大于2，一旦发现大于2的就返回false
         */
        boolean d = list.stream().peek(user -> System.out.println(user.getUserId()))
                .noneMatch(user -> user.getUserId() > 2);
        System.out.println(d);
        System.out.println("-------------------------------");

        /*
            fineFirst操作：获取第一个对象
            短路的  获取到了就返回
            获取list中的第一个对象
         */
        Optional<User> first = list.stream()
                .findFirst();
        System.out.println(first.get());
        System.out.println("-------------------------------");

        /*
            findAny操作  找到任何一个就返回
            并行操作时findAny会更快，可能会随机匹配，如果串行一致
            返回任意一个对象，在串行下和返回第一个对象一样，在并行下就是唯一的
         */
        Optional<User> first1 = list.stream().findAny();
        System.out.println(first1.get());
        System.out.println("-------------------------------");
        System.out.println("==========非短路操作==========");

        /*
            max操作：获取最大值
            将用户id分割出来，将值转换为double，返回最大值
         */
        OptionalDouble max = list.stream().mapToDouble(User::getUserId).max();
        System.out.println(max.getAsDouble());
        System.out.println("-------------------------------");

        /*
            min操作:获取最小值
            将用户id分割出来，将值转化为double，返回最小值
        */
        OptionalDouble min = list.stream().mapToDouble(User::getUserId).min();
        System.out.println(min.getAsDouble());
        System.out.println("-------------------------------");

        /*
            count操作  获取元素的个数
         */
        long count = list.stream().count();
        System.out.println(count);
        System.out.println("-------------------------------");

        /*
            collect操作
            将id元素大于2的元素重新写入集合
         */
        List<User> result = list.stream().filter(user -> user.getUserId() > 2)
                .collect(Collectors.toList());
        System.out.println(result.toString());
        System.out.println("-------------------------------");

        /*
            分组收集器
         */
        Map<String, List<User>> group = list.stream()
                .collect(Collectors.groupingBy(User::getUserName));
        Map<String, List<User>> group1 = list.stream().
                collect(Collectors.groupingBy(user -> user.getUserName()));
        System.out.println(group.toString());
        System.out.println("-------------------------------");

        /*
            分区收集器
            区分大于2和小于2两部分
            将元素按照id大于2为标准区分为两个区后进行打印
         */
        Map<Boolean, List<User>> partition = list.stream()
                .collect(Collectors.partitioningBy(user -> user.getUserId() > 2));
        System.out.println(partition);
        System.out.println("-------------------------------");
        System.out.println("==========构建流==========");

        /*
            由数值直接构建流
//         */
//        Stream stream = Stream.of(1, 2, 3, 4, 5);
//        stream.forEach(System.out::println);

        List<String> ss = Arrays.asList("张三", "李四", "王五");
        ss.forEach(System.out::println);
        System.out.println("-------------------------------");

        /*
            由数组构建流
         */
        int[] num = new int[]{1, 2, 3, 4, 5};
        Arrays.stream(num).forEach(System.out::println);
        System.out.println("-------------------------------");

        /*
            文件构建流
         */
//        Stream<String> stream1 = Files.lines(Paths.get("F:/QQ下载/FileServer.java"));
//        stream.forEach(System.out::println);

        /*
            通过函数生成流(无限流)
            https://www.jianshu.com/p/27310d283dda
         */
        Stream<Integer> stream = Stream.iterate(0, n -> n + 2);
        stream.limit(10)
                .forEach(System.out::println);

        //随机生成
        Stream<Double> stream2 = Stream.generate(Math::random);
        stream2.limit(10)
                .forEach(System.out::println);
    }
}

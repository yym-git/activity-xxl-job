package com.bc.test.job.activityxxljob.test;

import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author ym.y
 * @description 测试java8 Lambda表达式的使用:
 * 四大内置核心接口
 * 1、Consumer<T>
 * 2、Supplier<T>
 * 3、Predicate<T>
 * 4、函数式接口
 * @date 18:03 2022/7/23
 */
public class TestLambda {
    @Test
    public void test() {
        happy(20, x -> System.out.println("大保健：" + x));
    }

    @Test
    public void test2() {
        List<Integer> numList = getNumList(10, () -> (int) (Math.random() * 100));
        System.out.println(numList);
    }

    @Test
    public void test3() {
        Integer integer = strChangInt("2", (str) -> Integer.parseInt(str) * 2);
        System.out.println(integer);
    }

    @Test
    public void test4() {
        List lis = Arrays.asList(1, 23, 456, 90, 12);
        List results = filter(lis, x -> x > 60);
        System.out.println(results);
    }


    @Test
    public void test5() {
        List<String> list = Arrays.asList("aaa", "bb", "cc", "dd");
        list.stream().map(x -> x.toUpperCase()).forEach(System.out::println);
    }

    @Test
    public void test6() {
        List<Employee> employees = employees();
        //获取工资最高的员工信息
        Employee employee = employees.stream().collect(Collectors.maxBy(
                (e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
        ).get();
        System.out.println("工资最高的员工：" + employee);
        Long count = employees.stream().collect(Collectors.counting());
        System.out.println("总数：" + count);
        //最低工资
        Optional<Double> collect = employees.stream().map(Employee::getSalary).collect(Collectors.minBy(Double::compare));
        System.out.println("最低工资：" + collect.get());
    }


    //多级分组
    @Test
    public void test7() {
        List<Employee> employees = employees();
        Map<Status, Map<String, List<Employee>>> maps = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((e) -> {
                    if (e.getAge() <= 35) {
                        return "青年";
                    } else if (e.getAge() <= 50) {
                        return "中年";
                    } else {
                        return "老年";
                    }
                })));
        maps.forEach((k, v) -> {
            System.out.println(k);
            System.out.println(v);
        });
    }

    //分区
    @Test
    public void test8() {
        List<Employee> employees = employees();
        Map<Boolean, List<Employee>> map = employees.stream()
                .collect(Collectors.partitioningBy(e -> e.getSalary() > 5000));
        System.out.println(map);
    }

    /**
     * OPtional的操作
     */
    @Test
    public  void  test9(){

        Optional<Object> op = Optional.ofNullable(new Employee());
//        System.out.println(op.get());
        if(op.isPresent()){
            System.out.println("存在："+op.get());
        }
    }

    public void happy(double money, Consumer<Double> con) {
        con.accept(money);
    }

    public List<Integer> getNumList(int num, Supplier<Integer> sup) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(sup.get());
        }
        return list;
    }

    public Integer strChangInt(String str, Function<String, Integer> fun) {
        return fun.apply(str);
    }

    public List<Integer> filter(List<Integer> list, Predicate<Integer> predicate) {
        List<Integer> lists = new ArrayList<>();
        for (Integer n : list) {
            if (predicate.test(n)) {
                lists.add(n);
            }
        }
        return lists;
    }

    public static List<Employee> employees() {
        return Arrays.asList(
                new Employee("詹丹", 20, 1219, Status.BUSY),
                new Employee("李四", 22, 2000, Status.FREE),
                new Employee("无渣", 28, 2789, Status.FREE),
                new Employee("炸死", 30, 7899, Status.BUSY),
                new Employee("武器", 32, 21788, Status.VOCATION)
        );
    }
}

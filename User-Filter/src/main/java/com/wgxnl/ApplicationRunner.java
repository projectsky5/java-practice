package com.wgxnl;

import com.wgxnl.entity.User;

import java.util.*;
import java.util.stream.Collectors;

public class ApplicationRunner {
    public static void main(String[] args) {
        List<User> userList = Arrays.asList(
                new User("Maxim", 20),
                new User("Elena", 17),
                new User("Aleksey", 21),
                new User("Valentina", 22),
                new User("Petr", 21),
                new User("Akim", 16),
                new User("Ilya", 18),
                new User("Irina", 55)
        );

        System.out.println("Основной таск:");

        userList.stream()
                .filter(user -> user.getAge() > 18)
                .forEach(System.out::println);

        System.out.println("Вывод самого молодого:");

        userList.stream()
                .sorted()
                .findFirst()
                .ifPresent(System.out::println);

        System.out.println("Сортировка по возрасту и вывод всего списка:");

        userList.stream()
                .sorted()
                .forEach(System.out::println);


        System.out.println("Список имен взрослых:");

        userList.stream()
                .sorted()
                .filter(user -> user.getAge() > 18)
                .map(User::getName)
                .forEach(System.out::println);

        System.out.println("Группировка по возрасту");

        Map<Integer, List<User>> groupedByAge = userList.stream()
                .collect(Collectors.groupingBy(User::getAge));

        groupedByAge.forEach( (age, name) ->
                System.out.printf("Возраст = %d: %s\n", age, name));

        System.out.println("Сортировка по имени через Компаратор");

        userList.stream()
                .sorted(Comparator.comparing(User::getName))
                .forEach(System.out::println);

        System.out.println("Метод averageGet");

        System.out.println(averageAge2(userList));

    }
    public static int averageAge1(List<User> userList){
        OptionalDouble average = userList.stream()
                .mapToInt(User::getAge)
                .average();

        if(average.isEmpty()){
            return 0;
        }

        double doubleValue = average.getAsDouble();
        return (int) doubleValue;

    }

    public static int averageAge2(List<User> userList){
        return (int) userList.stream()
                .mapToInt(User::getAge)
                .average()
                .orElse(0);
    }
}

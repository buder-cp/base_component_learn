package com.example.libjava;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class JavaStream {
    public static void main(String[] args) {
        testStream();
    }


    public static void testStream() {
//        List<User> users = new ArrayList<>();
//        users.add(new User(13,"aaa"));
//        users.add(new User(13,"aaa"));
//        users.add(new User(14,"bbb"));
//        users.add(new User(15,"ccc"));
//        users.add(new User(16,"ddd"));
//        users.stream()
//                .distinct()
//                .forEach(user -> {
//                    System.out.println(user.name);
//                });

        List<String> users = new ArrayList<>();
        users.add("aaa");
        users.add("aaa");
        users.add("bbb");
        users.add("ccc");
        users.add("eee");
        users.add("ddd");
        users.add("fff");
        users.stream()
                .distinct()
//                .skip(2)
                .sorted()
                .forEach(System.out::println);
    }
}

class User {
    int age;
    String name;

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }
}
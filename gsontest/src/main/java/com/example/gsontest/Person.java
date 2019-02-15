package com.example.gsontest;

import java.util.List;

public class Person {
    private String name;
    private Integer age;
    private List<Address> address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public static class Address {
        private String postid;

        public String getPostid() {
            return postid;
        }

        public void setPostid(String postId) {
            this.postid = postId;
        }
    }

}

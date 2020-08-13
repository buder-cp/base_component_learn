package com.test.opensourceframework.dagger2;

public class Flower {
    private String name;
    private String color;

    public Flower(String name, String color) {
        this.name = name;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Flower{" + "name='" + name + "', color='" + color + "'}";
    }
}

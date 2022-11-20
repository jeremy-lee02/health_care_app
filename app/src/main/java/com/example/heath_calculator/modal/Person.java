package com.example.heath_calculator.modal;

import java.io.Serializable;

public class Person implements Serializable {
    private String name;
    private int age;
    private float weight;
    private float height;
    private String gender;
    private String intensity;
    private String goal;

    public Person() {
    }

    public Person(String name, int age, float weight, float height, String gender, String intensity, String goal) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.gender = gender;
        this.intensity = intensity;
        this.goal = goal;
    }

    public String getName() {
        return name;
    }


    public int getAge() {
        return age;
    }


    public float getWeight() {
        return weight;
    }


    public float getHeight() {
        return height;
    }


    public String getGender() {
        return gender;
    }


    public String getIntensity() {
        return intensity;
    }


    public String getGoal() {
        return goal;
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", height=" + height +
                ", gender='" + gender + '\'' +
                ", intensity='" + intensity + '\'' +
                ", goal='" + goal + '\'' +
                '}';
    }
}

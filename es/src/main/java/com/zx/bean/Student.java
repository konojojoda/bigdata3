package com.zx.bean;

import java.util.Objects;

public class Student {
    private String age;
    private String class_id;
    private String favo;
    private String name;
    private Float score;
    private String sex;


    public Student() {
    }

    public Student(String age, String class_id, String favo, String name, Float score, String sex) {
        this.age = age;
        this.class_id = class_id;
        this.favo = favo;
        this.name = name;
        this.score = score;
        this.sex = sex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(age, student.age) &&
                Objects.equals(class_id, student.class_id) &&
                Objects.equals(favo, student.favo) &&
                Objects.equals(name, student.name) &&
                Objects.equals(score, student.score) &&
                Objects.equals(sex, student.sex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, class_id, favo, name, score, sex);
    }

    @Override
    public String toString() {
        return "Student{" +
                "age='" + age + '\'' +
                ", class_id='" + class_id + '\'' +
                ", favo='" + favo + '\'' +
                ", name='" + name + '\'' +
                ", score=" + score +
                ", sex='" + sex + '\'' +
                '}';
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public String getClass_id() {
        return class_id;
    }

    public String getFavo() {
        return favo;
    }

    public String getName() {
        return name;
    }

    public Float getScore() {
        return score;
    }

    public String getSex() {
        return sex;
    }
}

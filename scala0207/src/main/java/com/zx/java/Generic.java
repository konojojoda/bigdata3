package com.zx.java;
import java.util.*;
public class Generic {
    public static void main(String[] args) {
        List list=new ArrayList();
        list.add(new Emp01());
        List<User01> list1=list;
        System.out.println(list);
        List<User01> list2 = new ArrayList<>();
//        for (User01 user:list1
//             ) {
//            System.out.println(user);
//        }
        test(User01.class);
        //test(Child01.class);
        test(Person01.class);
//        int a=10;
//        System.out.println(a);
//        test1(a);
//        System.out.println(a);


    }
    private static void test1(int a){a+=10;}
    public static void test(Class<? super User01> c){
        System.out.println(c.getName());
    }
}
class Person01{}
class User01 extends Person01{}
class Child01 extends User01{}
class Emp01 extends Person01{}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaranch13;

import java.awt.Point;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Piet
 */

public class LinwoodHayes {
    public static void main(String... args) {
        Map<String, MySupplier<Foo>> map = new HashMap<>();
        map.put("A", A::new);
        map.put("B", B::new);
        map.put("C", C::new);
        Foo a = map.get("A").get("piet", 6.0);
        Foo b = map.get("B").get(new Point(2,2));
        Foo c = map.get("C").get(Arrays.asList("aap", "noot"));
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }
}

//***************************************************************
interface MySupplier<T> {
    T get(Object... c);
}

//***************************************************************
interface Foo {}

//***************************************************************
class A implements Foo {
    String string;
    Double d;
    A(Object... c) {
        string = (String) c[0];
        d = (Double) c[1];
    }
    @Override
    public String toString() {
        return String.format(("Class: %s, String: %s, Double: %f"), this.getClass().getName(), string, d);
    }
}

//***************************************************************
class B implements Foo {
    Point p;
    B(Object... c) {
        this.p = (Point) c[0];
    }
    @Override
    public String toString() {
        return String.format("Class: %s, Point = %s", this.getClass().getName(), p);
    }
}

//***************************************************************
class C implements Foo {
    List<String> list;
    C(Object... c) {
        list = (List<String>) c[0];
    }
    @Override
    public String toString() {
        return String.format("Class: %s, list = %s", this.getClass().getName(), list);
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaranch13;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 *
 * @author Piet
 */
public class EmptyInterface {
    public static void main(String... args) {
        //        I p = () -> {return;};
        // gaat niet, want I is geen functioneel interface
        
        int[] a = {1,2,3,4,5,6,7,8,9,10};
        System.out.println(Arrays.toString(a));
        a = Arrays.stream(a)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .mapToInt(i -> i)
                .toArray()
        ;
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < a.length; i += 2) list.addFirst(a[i]);
        for (int i = 1; i < a.length; i += 2) list.add(a[i]);
        System.out.println(Arrays.toString(list.toArray()));
    }
}

interface I {}

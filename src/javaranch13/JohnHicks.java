/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaranch13;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author Piet
 */
public class JohnHicks {
    public static void main(String... args) {
        int[] a = {1,2,3,4,5,6,7,8,9,10};
        int[] b = Arrays.stream(a).boxed().sorted(Comparator.comparingInt(i -> i%2)).mapToInt(i -> i).toArray();
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
    }
}

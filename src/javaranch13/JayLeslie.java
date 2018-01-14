/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaranch13;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Piet
 */
public class JayLeslie {
    public static void main(String... args) {
        Integer[] a = {10, 11, 12, 9, 9, 8, 8,  14, 15};
        System.out.println(processArray(a));
        String[] s = {"a", "b", "a", "c" };
        System.out.println(processArray(s));
    }
    
    public static <T extends Comparable<T>> List<List<T>> processArray(T[] a) {
        List<List<T>> list = new ArrayList<>();
        List<T> currentList = new ArrayList<>();
        currentList.add(a[0]);
        currentList.add(a[1]);
        Direction currentDir = a[1].compareTo(a[0]) > 0 ? Direction.UP :
                               a[1].compareTo(a[0]) < 0 ? Direction.DOWN :
                                                          Direction.EVEN
        ;
        for (int i = 2; i < a.length; i++) {
            if (a[i].compareTo(a[i - 1]) < 0 && currentDir == Direction.UP) {
                list.add(currentList);
                currentDir = Direction.DOWN;
                currentList = new ArrayList<>();
            }
            else if (a[i].compareTo(a[i - 1]) > 0 && currentDir == Direction.DOWN) {
                list.add(currentList);
                currentDir = Direction.UP;
                currentList = new ArrayList<>();
            }
            currentList.add(a[i]);
        }
        list.add(currentList);
        for (int i = 1; i < list.size(); i++) {
            List<T> previous = list.get(i - 1);
            List<T> current = list.get(i);
            current.add(0, previous.get(previous.size() - 1));
        }
        return list;
    }
    
    enum Direction {
        EVEN, UP, DOWN;
    }
}

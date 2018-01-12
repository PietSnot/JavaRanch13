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
        int[] a = {10, 11, 12, 9, 9, 8, 8,  14, 15};
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> currentList = new ArrayList<>();
        currentList.add(a[0]);
        currentList.add(a[1]);
        Direction currentDir = a[1] > a[0] ? Direction.UP :
                               a[1] < a[0] ? Direction.DOWN :
                                             Direction.EVEN
        ;
        for (int i = 2; i < a.length; i++) {
            if (a[i] < a[i - 1] && currentDir == Direction.UP) {
                list.add(currentList);
                currentDir = Direction.DOWN;
                currentList = new ArrayList<>();
                currentList.add(a[i]);
            }
            else if (a[i] > a[i - 1] && currentDir == Direction.DOWN) {
                list.add(currentList);
                currentDir = Direction.UP;
                currentList = new ArrayList<>();
                currentList.add(a[i]);
            }
            else {
                currentList.add(a[i]);
                if (i == a.length - 1) list.add(currentList);
            }
        }
        for (int i = 1; i < list.size(); i++) list.get(i).add(0, list.get(i - 1).get(list.get(i-1).size() - 1));
        System.out.println(list);
    }
    
    enum Direction {
        EVEN, UP, DOWN;
    }
}

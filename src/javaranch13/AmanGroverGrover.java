/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaranch13;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 *
 * @author Piet
 */
public class AmanGroverGrover {
    public static void main(String... args) {
        Map<Character, Integer> map = new TreeMap<>();
        String string = "aaaaabbcdddeeeee";
        for (char c: string.toCharArray()) map.merge(c, 1, (a, b) -> a + b);
        String s = map.entrySet().stream().map(e -> "" + e.getKey() + e.getValue()).collect(Collectors.joining());
        System.out.println(s);
    }
}

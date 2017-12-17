/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaranch13;

/**
 *
 * @author Piet
 */
public class RayBell {

    public static void main(String... args) {
        int[] ticket = {9, 1, 9, 1};
        int[] drawn = {9, 9, 9, 9};
        int matches = 0;

        for (int i = 0; i < ticket.length; i++) {
            for (int j = matches; j < drawn.length; j++) {
                if (ticket[i] == drawn[j]) {
                    // swap drawn[matches] and drawn[j];
                    int x = drawn[matches];
                    drawn[matches] = drawn[j];
                    drawn[j] = x;
                    matches++;
                    break;
                }
            }
        }
        System.out.println("matches: " + matches);
    }
}

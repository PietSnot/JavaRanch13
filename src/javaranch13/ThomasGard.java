/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaranch13;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @author Piet
 */
public class ThomasGard {
    public static void main(String... args) {
        AtomicBoolean piet = new AtomicBoolean(true);
        System.out.println("piet: " + piet);
        piet.set(!piet.get());
        System.out.println("piet now: " + piet);
        piet.set(!piet.get());
        System.out.println("piet now: " + piet);
        piet.compareAndSet(!piet.compareAndSet(false, true), false);
        System.out.println("piet now: " + piet);
        piet.compareAndSet(!piet.compareAndSet(false, true), false);
        System.out.println("piet now: " + piet);
        piet.compareAndSet(!piet.compareAndSet(false, true), false);
        System.out.println("piet now: " + piet);
    }
}

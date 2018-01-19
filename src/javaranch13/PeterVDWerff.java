/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaranch13;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Piet
 */
public class PeterVDWerff {
    public static void main(String... args) {
        List<Point> list = new ArrayList<>();
        list.add(new Point());
        if (list.contains("aap")) System.out.println("Ja zeg");
        else System.out.println("geen aap");
    }
}

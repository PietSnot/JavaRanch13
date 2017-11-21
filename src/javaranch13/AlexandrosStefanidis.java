/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaranch13;

import static java.lang.Math.cos;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Scanner;
import java.util.function.Function;

/**
 *
 * @author Piet
 */
public class AlexandrosStefanidis {
}


class Trap {

    public static void main(String args[]) {
        double area;      // Store result in area
        double a, b;      // Left and right endpoints
        int n;         // Number of trapezoids
        double h;         // Trapezoid base width

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a: ");
        a = sc.nextDouble();
        System.out.println();
        System.out.print("Enter b: ");
        b = sc.nextDouble();
        System.out.println();
        System.out.print("Enter n: ");
        n = sc.nextInt();
        h = (b - a) / n;
area = trap(a, b, n, h, x -> 8 * Math.cos(x));
System.out.println("With n = " + n + " trapezoids, our estimate");
System.out.print("of the area from " + a + " to " + b);
System.out.println(" = " + area);
System.out.println("**************************");
area = trap(a, b, n, h, x -> Math.exp(-.5 * x * x) / Math.sqrt(2 * Math.PI));
System.out.println("With n = " + n + " trapezoids, our estimate");
System.out.print("of the area from " + a + " to " + b);
System.out.println(" = " + area);
System.out.println("**************************");

    }  // main

    // Method:       trap
    // Purpose:      Estimate area using the trapezoidal rule
    // Input args:   a: left endpoint
    //               b: right endpoint
    //               n: number of trapezoids
    //               h: stepsize = length of base of trapezoids
    // Return val:   Trapezoidal rule estimate of area between x-axis,
    //               x = a, x = b, and graph of f(x)
    // Note:         Need *static* so trap can be called from main without 
    //               instantiating an object
static double trap(double a, double b, int n, double h, Function<Double, Double> f) {
    double area;   // Store result in area 
    double x;
    int i;

    area = (f.apply(a) + f.apply(b)) / 2.0;
    for (i = 1; i <= n - 1; i++) {
        x = a + i * h;
        area = area + f.apply(x);
    }
    area = area     * h;
    return area;
} //  trap  

    // Method:     f
    // Purpose:    Compute value of function being integrated
    // Input arg:  x
    // Note:       Need *static* so f can be called from trap without 
    //             instantiating an object
//    static double f(double x) {
//        double return_val;
//
//        return_val = (8 + 4 * cos(x));
//        return return_val;
//    }  // f

}  // class Trap


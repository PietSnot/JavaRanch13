/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaranch13;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javafx.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Piet
 */
public class JavaRanch13 {

    /**
     * @param args the command line arguments
     */
    
    private int i = 5;
    private static int j = 5;
    public static void main(String[] args) {
//        StringBuilder sb = new StringBuilder("abcde");
//        System.out.println("position of d = " + sb.indexOf("d"));
    }
    
    static class A {
        private static int a;
        private int b;
        A() {
//            a = i;   //  error
            b = j;   //  okay
        }
    }
    
    class B {
        private int b;
        B() {
            b = i;   // okay;
        }
    }

}




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaranch13;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class DanielMorley {
    
}

class TicTacToe {

        private static int[][] winArray = new int[][]{
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, //horizontal wins
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, //vertical wins
            {0, 4, 8}, {2, 4, 6} //diagonal wins
        };
        //Make 9 buttons
        private static JButton buttons[] = new JButton[9];

        public static void main(String[] args) {
            //Start Game
            Panel();
        }

        public static void Panel() {
            JFrame frame = new JFrame("TICTACTOE");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            //creating a panel with a box like a TICTACTOE board
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(3, 3));
            panel.setBorder(BorderFactory.createLineBorder(Color.black, 5));
            panel.setBackground(Color.black);

            for (int i = 0; i <= 8; i++) {

                //placing buttons on the board
                buttons[i] = new MyButton();
                panel.add(buttons[i]);
                buttons[i].setIcon(new ImageIcon("/home/daniel/workspace/TicTacToe6/images/BLANK.png"));
            }

            frame.getContentPane().add(panel);
            frame.pack();
            frame.setVisible(true);
            // set frame size
            frame.setSize(700, 700);
        }

        // used for counting
        public static int xoCount = 0;

        private static class MyButton extends JButton implements ActionListener {

            //creating own button class 
            int playable = 5000;
            //set again at 1000 so we don't make the mistake we can play again
            boolean win = false;
            // there is no win
            String XorO;
            // x or o

            public MyButton() {
                // creating blank grid
                super();
                XorO = " ";
                setFont(new Font("Dialog", 1, 12));
                setText(XorO);
                addActionListener(this);
            }

            public void actionPerformed(ActionEvent e) { // placing x or o's
                if ((xoCount % 2) == 0 && getText().equals(" ") && win == false) {
                    int index = 9;
                    XorO = "X";
                    for (int i = 0; i < 9; i++) {
                        if (e.getSource() == buttons[i]) {
                            index = i;
                        }
                    }

                    buttons[index].setIcon(new ImageIcon("/home/daniel/workspace/TicTacToe6/images/Xes.png"));
                    xoCount = xoCount + 1;
                    System.out.println(XorO + "\n" + xoCount);
                }
                if ((xoCount % 2) == 1 && getText().equals(" ") && win == false) {

                    XorO = "O";
                    buttons[2].doClick();
                    buttons[2].setIcon(new ImageIcon("/home/daniel/workspace/TicTacToe6/images/Oes.png"));
                    xoCount = xoCount + 1;
                    System.out.println(XorO + "\n" + xoCount);
                }

                setText(XorO);
                // place the x or the o on the actual board invisible see font

                for (int i = 0; i < 8; i++) {
                    // check for the winning situations
                    if (buttons[winArray[i][0]].getText().equals(buttons[winArray[i][1]].getText())
                            && buttons[winArray[i][1]].getText().equals(buttons[winArray[i][2]].getText())
                            && buttons[winArray[i][0]].getText() != " ") {
                        //win if true
                        win = true;
                    }
                }

                if (win == true) {
                    //See if to play again
                    playable = JOptionPane.showConfirmDialog(null, XorO + " wins!  Play again?", XorO + "won!", JOptionPane.YES_NO_OPTION);

                } else if (xoCount == 9 && win == false) {

                    //Tie Game play again
                    playable = JOptionPane.showConfirmDialog(null, "Tie!  Play again?", "Tie game!", JOptionPane.YES_NO_OPTION);
                    win = true;
                }

                if (playable == JOptionPane.YES_OPTION && win == true) {
                    //  clear all the button and start over
                    clearButtons();
                    win = false;
                } else if (playable == JOptionPane.NO_OPTION) {
                    System.exit(0);
                    // exit game 
                }

            }

//        @Override
//        public void actionPerformed(java.awt.event.ActionEvent ae) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }

        }

        public static void clearButtons() {

            for (int i = 0; i <= 8; i++) {// clear all 8 buttons
                buttons[i].setText(" ");
                buttons[i].setIcon(new ImageIcon("/home/daniel/workspace/TicTacToe6/images/BLANK.png"));
            }
            xoCount = 0; // reset the count

        }
}


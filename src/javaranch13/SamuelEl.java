/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaranch13;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Piet
 */
public class SamuelEl {

}

class TwoButtons {

    JFrame frame;
    JLabel label;
    MyDrawPanel panel;
    boolean labelPushed = false;

    public static void main(String[] args) {
        TwoButtons gui = new TwoButtons();
        gui.go();

    }

    public void go() {

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton labelButton = new JButton("Change Label");
        labelButton.addActionListener(new LabelListener());

        JButton colorButton = new JButton("Change Circle Color");
        colorButton.addActionListener(new ColorListener());

        label = new JLabel("I'm a Label");
//        MyDrawPanel drawPanel = new MyDrawPanel();
        panel = new MyDrawPanel();

        frame.getContentPane().add(BorderLayout.SOUTH, colorButton);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.getContentPane().add(BorderLayout.EAST, labelButton);
        frame.getContentPane().add(BorderLayout.WEST, label);

        frame.setSize(300, 300);
        frame.setVisible(true);

    }

    class LabelListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            if (!labelPushed) {
                label.setText("OUCH!");
                labelPushed = true;
            } else {
                label.setText("I'm a Label");
                labelPushed = false;
            }
        }

    }

    class ColorListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            panel.colorGenerator();
        }
    }

}

class MyDrawPanel extends JPanel {

    Color colorMeIn;

    public void colorGenerator() {

        int red = (int) (Math.random() * 255);
        int green = (int) (Math.random() * 255);
        int blue = (int) (Math.random() * 255);

        colorMeIn = new Color(red, green, blue);
        this.repaint();
    }

    public void paintComponent(Graphics g) {

        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(colorMeIn);
        g.fillOval(70, 70, 100, 100);
    }
}

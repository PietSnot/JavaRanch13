/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaranch13;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Piet
 */
public class RyanOMara {

}

class DrawExample extends JFrame
        implements ActionListener {

    private JButton button;
    private JPanel panel;
    private JTextField text;
    private String textFromTextfield = "";

    public static void main(String[] args) {
        DrawExample frame = new DrawExample();
        frame.setSize(400, 300);
        frame.createGUI();
        frame.setVisible(true);
    }

    private void createGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawString(textFromTextfield, 30, 30);
            }
        };
        panel.setPreferredSize(new Dimension(300, 200));
        panel.setBackground(Color.white);
        window.add(panel);

        button = new JButton("Press me");
        window.add(button);
        button.addActionListener(this);

        text = new JTextField(5);
        window.add(text);

    }

    public void actionPerformed(ActionEvent event) {
//        textFromTextfield = text.getText();
//        panel.repaint();
          panel.getGraphics().drawString(text.getText(), 30, 30);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaranch13;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Piet
 */
public class MatanBruchim {

}

class Main2 {

    public static void main(String[] args) {
        JFrame obj = new JFrame();
        GamePlay gameplay = new GamePlay();
        obj.setBounds(10, 10, 700, 600);//this is set the bound of the game. 
        obj.setTitle("Breakout Ball");//this is set the title of the game. 
        obj.setResizable(false);//to decide if it resizable or not. 
        obj.setVisible(true);//to set if visible or not. 
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gameplay);

    }
}

class GamePlay extends JPanel implements KeyListener, ActionListener {

    private boolean play = false;
    private int score = 0;
    private int totalBricks = 21;
    private Timer time;
    private int delay = 0;
    private int playerX = 310;//המיקום של השחקן בהתחלה 
    private int ballposX = 120;//המיקום של הכדור באיקס 
    private int ballposY = 350;//המיקום של הכדור בוואי 
    private int ballXdir = 1;//הכיוון של הכדור 
    private int ballYdir = -2;

    public GamePlay() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        time = new Timer(delay, this);
        time.start();
    }

    public void paint(Graphics g) {
        //background 
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);
        //create borders 
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);
        //create the paddle 
        g.setColor(Color.green);
        g.fillRect(playerX, 550, 100, 8);
        //create the ball 
        g.setColor(Color.yellow);
        g.fillOval(ballposX, ballposY, 20, 20);
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        time.start();
        if (play == true) {
            if (new Rectangle(ballposX, ballposY, 20, 20).intersects(playerX, 550, 100, 8)) {
                ballYdir = -ballYdir;
            }
            ballposX = ballposX + ballXdir;
            ballposY = ballposY + ballYdir;
            if (ballposX < 0) {
                ballXdir = -ballXdir;
            }
            if (ballposY < 0) {
                ballYdir = -ballYdir;
            }
            if (ballposX < 670) {
                ballXdir = -ballXdir;
            }
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerX >= 600) {
                playerX = 600;
            } else {
                moveRight();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerX < 10) {
                playerX = 10;
            } else {
                moveLeft();
            }
        }
    }

    public void moveRight() {
        play = true;
        playerX = playerX + 20;
    }

    public void moveLeft() {
        play = true;
        playerX = playerX - 20;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}

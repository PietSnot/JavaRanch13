/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaranch13;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Piet
 */
public class DaffyDuck {

}

class ComboNumber extends JFrame implements ActionListener {

    int one, two, three;
    String inData1, inData2, inData3;
    JButton[] button;
    JLabel secretLabel;
    JLabel doorStatus;
    private String secret = "5689";
    private String guess = "";
    Timer timer;

    public ComboNumber() {
        getContentPane().setLayout(new FlowLayout());
        Container c = getContentPane();
        button = new JButton[10];
        for (int i = 0; i < button.length; ++i) {
            button[i] = new JButton("" + i);
            c.add(button[i]);
            button[i].addActionListener(this);
        }
        secretLabel = new JLabel(secret);
        c.add(secretLabel);
        doorStatus = new JLabel("Door's closed");
        c.add(doorStatus);
        setTitle("Comboination Lock");
        JPanel panel = new JPanel();
        timer = new Timer(20_000, this::processTimerEvent);
        timer.start();
    }

    public void actionPerformed(ActionEvent evt) {
        Object o = evt.getSource();
        if (o instanceof JButton) {
            JButton btn = (JButton) o;
            guess += btn.getText();
            if (guess.equals(secret)) {
                processGuessCorrect();
            }
            else if (guess.length() >= 4) {
                JOptionPane.showMessageDialog(this, "WRONG", "Wrong", JOptionPane.ERROR_MESSAGE);
                guess = "";
            }
        }
    }
    
private void processTimerEvent(ActionEvent e) {
    System.out.println("Time's UP; new secret number...");
    generateNewSecret();
}
    
    private void processGuessCorrect() {
        timer.stop();
        doorStatus.setText("Door is OPEN...");
        guess = "";
        JOptionPane.showMessageDialog(this, "Welcome Overloard Master");
        try {
            Thread.sleep(5_000);
            doorStatus.setText("Door is closed!!!");
        }
        catch (InterruptedException e) {
            System.out.println("Interrupted");
        }

        generateNewSecret();
        timer.start();
    }
    
    private void generateNewSecret() {
        Random r = new Random();
        secret = "";
        for (int i = 1; i <= 4; i++) secret += r.nextInt(10);
        secretLabel.setText(secret);
    }

    public static void main(String[] args) {
        ComboNumber frm = new ComboNumber();
        WindowQuitter wQuit = new WindowQuitter();
        frm.addWindowListener(wQuit);
        frm.setSize(500, 500);
        frm.setVisible(true);
    }
}

class WindowQuitter extends WindowAdapter {

    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
}

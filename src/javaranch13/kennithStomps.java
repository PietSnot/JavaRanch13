/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaranch13;

import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author Piet
 */
public class kennithStomps {
    public static void main(String... args) {
        String s = "";
        Random r = new Random();
        boolean passedLengthTest = r.nextBoolean();
        boolean passedSpecialCharsTest = r.nextBoolean();
        boolean passedDigitsTest = r.nextBoolean();
        if (!passedLengthTest) s = "Length password wrong";
        if (!passedSpecialCharsTest) {
            if (!s.isEmpty()) s += '\n';
            s += "Wrong special chars!!";
        }
        if (!passedDigitsTest) {
            if (!s.isEmpty()) s += '\n';
            s += "Wrong digits!!!";
        }
        if (s.isEmpty()) {
            JOptionPane.showMessageDialog(null, "typed password is okay!!!");
        }
        else {
            JOptionPane.showMessageDialog(null, s, "Wrong!!!!", JOptionPane.ERROR_MESSAGE);
        }
    }
}

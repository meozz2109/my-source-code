/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vnua.k62cnpm.xdptpm.libmanage.main;

import vnua.k62cnpm.xdptpm.libmanage.forward.ForwardFrame;

/**
 *
 * @author Minh
 */
public class Main {
    public static void main(String[] args) {
        ForwardFrame ff = new ForwardFrame();
        ff.pack();
        ff.setLocationRelativeTo(null);
        ff.setVisible(true);
    }
}

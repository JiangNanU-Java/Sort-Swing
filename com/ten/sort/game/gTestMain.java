package com.ten.sort.game;

import javax.swing.*;

public class gTestMain extends JFrame{
    private gTest gTestPanel=new gTest();
    public gTestMain(){
        add(gTestPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame=new gTestMain();
                frame.setVisible(true);
                frame.setSize(500,500);
                frame.pack();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }
}

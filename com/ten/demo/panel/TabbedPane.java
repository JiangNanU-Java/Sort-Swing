package com.ten.demo.panel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

//页签面板
public class TabbedPane extends JFrame {
    private String[] statements = {"hello", "how are you", "are you ok", "nice to meet you"};//页签数组
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JTextField textField = new JTextField(20);

    public TabbedPane() {
        int i = 0;
        //JTabbedPane的addTab方法，添加页签和它对应的面板
        for (String state : statements) {
            tabbedPane.addTab(statements[i], new Button("Tabbed pane " + i++));
        }
        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                textField.setText("Tab selected: " + tabbedPane.getSelectedIndex());
            }
        });
        add(BorderLayout.SOUTH, textField);
        add(tabbedPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new TabbedPane();
                frame.setSize(200, 200);
                frame.setVisible(true);
                frame.pack();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }
}

package com.ten.sort.dialogFrame;

import javax.swing.*;
import java.awt.*;

//自定义弹出对话框
public class ExDialog extends JDialog {
    //第一个参数为拥有者，第二个参数为显示的String内容
    public ExDialog(JFrame owner, String content) {
        super(owner, "Dialog", true);
        add(new JLabel("<html><h1><i>Sort Panel</i></h1><hr>" + content + "</html>"), BorderLayout.CENTER);

        JPanel panel = new JPanel();
        JButton ok = new JButton("OK");
        //点击ok时，关闭对话框
        ok.addActionListener(e -> setVisible(false));
        panel.add(ok);
        add(panel, BorderLayout.SOUTH);
        setSize(150, 250);
        setLocation(500, 500);
        setVisible(true);
    }
}

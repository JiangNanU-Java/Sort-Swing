package com.ten.demo.login;

import javax.swing.*;
import java.awt.*;

/**
 * 一个登录的Panel
 *
 * @author wshten
 * @date 2018/11/11
 */
public class TextComponentFrame extends JFrame {
    private static final int TEXTAREA_ROWS = 8;
    private static final int TEXTAREA_COLUMNS = 20;

    public TextComponentFrame() {
        //用户名 密码
        JTextField textField = new JTextField(10);
        JPasswordField passwordField = new JPasswordField(10);
        //用户名和密码面板
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridBagLayout());
        //添加组件和布局约束
        northPanel.add(new JLabel("User name: ", SwingConstants.RIGHT), getConstraints(0, 0));
        northPanel.add(textField, getConstraints(1, 0));
        northPanel.add(new JLabel("Password: ", SwingConstants.RIGHT), getConstraints(0, 1));
        northPanel.add(passwordField, getConstraints(1, 1));

        add(northPanel, BorderLayout.NORTH);

        //获取文本域+滚动区域
        JTextArea textArea = new JTextArea(TEXTAREA_ROWS, TEXTAREA_COLUMNS);
        textArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(textArea);

        add(scrollPane, BorderLayout.CENTER);

        //添加按钮
        JPanel southPanel = new JPanel();
        JButton insertButton = new JButton("Insert");
        southPanel.add(insertButton);
        //Button的监听事件
        insertButton.addActionListener(event ->
                textArea.append("User name: " + textField.getText() + "\n" + "Password: " + new String(passwordField.getPassword()) + "\n"));

        add(southPanel, BorderLayout.SOUTH);
        pack();
    }

    public static void main(String[] args) {
        //创建窗口
        JFrame frame = new TextComponentFrame();
        frame.setVisible(true);
    }

    //布局约束
    private GridBagConstraints getConstraints(int x, int y) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 100;
        constraints.weighty = 100;
        return constraints;
    }
}

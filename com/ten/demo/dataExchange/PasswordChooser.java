package com.ten.demo.dataExchange;

import javax.swing.*;
import java.awt.*;

//交互面板 extends JPanel
public class PasswordChooser extends JPanel {
    private JTextField username;
    private JPasswordField password;
    private JButton okButton;
    private boolean ok;
    private JDialog dialog;

    //创建面板
    public PasswordChooser() {
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.add(new JLabel("username:"), new GBS(0, 0, 1, 1, 1, 1));
        panel.add(username = new JTextField(""), new GBS(1, 0, 1, 2, 2, 1));
        panel.add(new JLabel("password:"), new GBS(0, 1, 1, 1, 1, 1));
        panel.add(password = new JPasswordField(""), new GBS(1, 1, 1, 2, 2, 1));
        add(panel, BorderLayout.CENTER);
        //Ok按钮事件
        okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            ok = true;
            dialog.setVisible(false);
        });
        //cancel按钮事件
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            dialog.setVisible(false);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    //设置用户名
    public void setUser(User u) {
        username.setText(u.getUserName());
    }

    //获取用户名
    public User getUser() {
        return new User(username.getText(), password.getPassword());
    }

    //返回Ok的值，若点击ok返回true，若点击cancel则返回false
    public boolean showDialog(Component parent, String title) {
        ok = false;

        Frame owner = null;
        //如果传入的框架是frame,那么owner设置为传入的这个窗口
        if (parent instanceof Frame)
            owner = (Frame) parent;
        else//若不是，那么新建一个窗口作为Parent
            owner = (Frame) SwingUtilities.getAncestorOfClass(Frame.class, parent);
        //若交互窗口为空  或者  窗口的owner不是上面的owner
        if (dialog == null || dialog.getOwner() != owner) {
            //新建一个dialog
            dialog = new JDialog(owner, true);
            dialog.add(this);
            dialog.getRootPane().setDefaultButton(okButton);
            dialog.pack();
        }

        dialog.setTitle(title);
        dialog.setVisible(true);
        return ok;
    }

    //布局管理
    private class GBS extends GridBagConstraints {
        private GBS(int x, int y, int heigh, int width, int weightx, int weighty) {
            this.gridx = x;
            this.gridy = y;
            this.gridheight = heigh;
            this.gridwidth = width;
            this.weightx = weightx;
            this.weighty = weighty;
            this.fill = GridBagConstraints.BOTH;
        }
    }
}

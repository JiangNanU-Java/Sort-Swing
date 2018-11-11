package com.ten.demo.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//JPopupMenu弹出式菜单
public class Popup extends JFrame {
    private JPopupMenu popupMenu = new JPopupMenu();
    private JTextField textField = new JTextField(10);

    public Popup() {
        setLayout(new FlowLayout());
        add(textField);
        //标签的点击事件
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText(((JMenuItem) e.getSource()).getText());
            }
        };
        JMenuItem menuItem = new JMenuItem("hither");
        menuItem.addActionListener(al);
        popupMenu.add(menuItem);
        menuItem = new JMenuItem("exit");
        menuItem.addActionListener(al);
        popupMenu.add(menuItem);

        PopupListener pl = new PopupListener();
        addMouseListener(pl);
        textField.addMouseListener(pl);
    }

    //菜单的事件监听类
    class PopupListener extends MouseAdapter {
        //鼠标点击事件
        @Override
        public void mousePressed(MouseEvent e) {
            maybeShowPopup(e);
        }

        //鼠标重新点击事件
        @Override
        public void mouseReleased(MouseEvent e) {
            maybeShowPopup(e);
        }

        //在鼠标点击的位置显示菜单
        private void maybeShowPopup(MouseEvent event) {
            if (event.isPopupTrigger())
                popupMenu.show(event.getComponent(), event.getX(), event.getY());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new Popup();
                frame.setSize(300, 300);
                frame.setVisible(true);
                frame.pack();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }
}

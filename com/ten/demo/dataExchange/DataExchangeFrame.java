package com.ten.demo.dataExchange;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//数据交互对话框
public class DataExchangeFrame extends JFrame {
    public static final int TEXT_ROWS = 20;
    public static final int TEXT_COLUMNS = 40;
    private PasswordChooser dialog = null;
    private JTextArea textArea;

    //构造函数，生成一个面板
    public DataExchangeFrame() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenuItem connectItem = new JMenuItem("Connect");
        connectItem.addActionListener(new ConnectAction());
        fileMenu.add(connectItem);

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        textArea = new JTextArea(TEXT_ROWS, TEXT_COLUMNS);
        add(new JScrollPane((textArea)), BorderLayout.CENTER);
        pack();
    }

    //Connect按钮的事件监听类，能够打开信息交互窗口
    private class ConnectAction implements ActionListener {
        //dialog就是要打开的交互窗口
        @Override
        public void actionPerformed(ActionEvent e) {
            //当交互窗口不存在时，创建并初始化
            if (dialog == null) {
                dialog = new PasswordChooser();
            }
            dialog.setUser(new User("yourname", null));
            //当点击Ok（返回true）时，获取对话框信息，并显示在textArea上
            if (dialog.showDialog(DataExchangeFrame.this, "Connect")) {
                User u = dialog.getUser();
                textArea.append("user name=" + u.getUserName() + ",password=" + (new String(u.getPassword())) + "\n");
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new DataExchangeFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLocation(200, 200);
        frame.pack();
    }

}

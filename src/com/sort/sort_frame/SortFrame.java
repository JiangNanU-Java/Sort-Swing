package com.sort.sort_frame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * JFrame 框架类
 */
public class SortFrame extends JFrame{
    private SortPanel sortPanel;
    private static JMenuBar menuBar;

    //框架初始化，构造函数
    public SortFrame(){
        //设置外观
        UISelect();

        //添加菜单
        init_menu();
        setJMenuBar(menuBar);

        //设置内容面板
        sortPanel=new SortPanel();
        setContentPane(sortPanel);
        setLocation(200,200);
        pack();
    }

    //设置外观 当前系统的外观
    private static void UISelect() {
        try {
            //获取当前系统的默认外观
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //初始化JMenuBar菜单栏
    private static void init_menu() {
        menuBar = new JMenuBar();

        //一级菜单
        JMenu FileMenu = new JMenu("File");
        JMenu EditMenu = new JMenu("Edit");
        JMenu AboutMenu = new JMenu("About");

        //点击事件
        FileMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        EditMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        AboutMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        //添加一级菜单
        menuBar.add(FileMenu);
        menuBar.add(EditMenu);
        menuBar.add(AboutMenu);
    }
}

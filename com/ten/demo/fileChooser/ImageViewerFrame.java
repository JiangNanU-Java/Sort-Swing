package com.ten.demo.fileChooser;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.*;
import java.io.File;

//图片选择对话框
public class ImageViewerFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 400;
    private JLabel label;
    private JFileChooser chooser;

    public static void main(String[] args) {
        JFrame frame = new ImageViewerFrame();
        frame.setVisible(true);
        frame.setLocation(200, 200);
        frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        frame.setTitle("图片选择框");
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public ImageViewerFrame() {
        //设置菜单视图
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("File");
        menuBar.add(menu);

        JMenuItem openItem = new JMenuItem("open");
        menu.add(openItem);
        //"open"按钮打开事件
        openItem.addActionListener(e -> {
            chooser.setCurrentDirectory(new File("."));//设置默认目录->本目录

            int result = chooser.showOpenDialog(ImageViewerFrame.this);//显示对话框

            if (result == JFileChooser.APPROVE_OPTION) ;
            {//如果选择了图片
                String name = chooser.getSelectedFile().getPath();
                label.setIcon(new ImageIcon(name));//预览
                pack();
            }
        });

        JMenuItem exitItem = new JMenuItem("exit");
        menu.add(exitItem);
        exitItem.addActionListener(e -> System.exit(0));

        label = new JLabel();
        add(label);
        //过滤器
        chooser = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png");//过滤器的过滤内容
        chooser.setFileFilter(filter);
        chooser.setAccessory(new ImagePreviewer(chooser));//ImagePreviewer 预览类
        chooser.setFileView(new FileIconView(filter, new ImageIcon("palette.gif")));//FileIconView 文件浏览类
    }
}

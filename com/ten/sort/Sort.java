package com.ten.sort;

import javax.swing.*;
import java.awt.*;

/**
 * 主程序
 * @author wang shihao
 * @version 2017
 */
public class Sort {
    public static void main(String[] args) {
        //EventQueue,invokLater分发Swing事件线程
        EventQueue.invokeLater(()->{
            JFrame frame = new SortFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("算法设计-时间复杂度分析");
            frame.setVisible(true);
        });
    }
}

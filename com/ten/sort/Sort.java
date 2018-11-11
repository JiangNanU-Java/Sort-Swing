package com.ten.sort;

import javax.swing.*;
import java.awt.*;

/**
 * 程序入口
 *
 * @author wshten
 * @date 2018/11/11
 */
public class Sort {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new SortFrame();
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setTitle("算法设计-时间复杂度分析");
            frame.setVisible(true);
        });
    }
}

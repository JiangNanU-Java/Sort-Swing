package com.ten.sort;

import javax.swing.*;
import java.awt.*;

/**
 * 获取文本框组件，并添加事件
 */
public class TextComponent {
    //定义文本框
    public static JTextArea sortText;
    public static JTextArea resultText;

    //定义文本属性
    private static final int TEXT_ROWS = 10;
    private static final int TEXT_COLUMNS = 100;
    private static final Font songFont = new Font("宋体", Font.PLAIN, 16);

    //JText文本框 属性初始化
    public TextComponent() {
    }

    //设置排序显示内容——线程安全
    public static void setSortText(String text) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                sortText.append(text);
                sortText.append("\r\n");
            }
        });
    }

    //设置结果显示内容——线程安全
    public static void setResultText(String text) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                resultText.append(text);
                resultText.append("\r\n");
            }
        });
    }

    //获取排序文本框-滚动窗格
    public static JScrollPane getSortTextPanel() {
        sortText = setText(sortText);
        setSortText("等待排序........");
        return new JScrollPane(sortText);
    }

    //获取排结果文本框-滚动窗格
    public static JScrollPane getResultTextPanel() {
        resultText = setText(resultText);
        setResultText("等待排序........");
        return new JScrollPane(resultText);
    }

    //设置文本框属性
    public static JTextArea setText(JTextArea textArea) {
        //初始化文本框
        textArea = new JTextArea(TEXT_ROWS, TEXT_COLUMNS);

        //设置字体格式
        textArea.setFont(songFont);

        //设置自动换行
        textArea.setLineWrap(true);

        return textArea;
    }
}

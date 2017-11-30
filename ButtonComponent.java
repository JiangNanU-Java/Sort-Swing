package com.Sort;

import com.Sort.Mapping.doMapping;
import com.Sort.SortFile.ReadSortArray;
import com.Sort.SortFile.SaveSortArray;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * 获取按钮组件，并添加响应事件
 */
public class ButtonComponent {
    private static JButton sortButton;
    private static JButton mappingButton;
    private static JButton inputButton;
    private static JButton outputButton;

    private static JComboBox comboBox;

    private static String sortFunction = "Default";

    //对文本框的操作
    private static TextComponent textArea = new TextComponent();

    //初始化
    public ButtonComponent() {
    }

    /**
     * 获取"排序"按钮 并 添加响应事件
     *
     * @return sortButton
     */
    public static JButton getSortButton() {
        sortButton = new JButton("开始排序");
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //开启一个排序线程
                Runnable r = () -> {
                    try {
                        new SortExecutors();
                        Thread.sleep(500);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                };
                Thread thread = new Thread(r);
                thread.start();
            }
        });
        return sortButton;
    }

    /**
     * 获取"绘图"按钮 并 添加响应事件
     *
     * @return mappingButton
     */
    public static JButton getMappingButton() {
        mappingButton = new JButton("绘制图像");
        mappingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Runnable r = () -> {
                    try {
                        new doMapping();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                };
                Thread thread = new Thread(r);
                thread.start();
            }
        });
        return mappingButton;
    }

    /**
     * 获取"读取"按钮 并 添加响应事件
     *
     * @return inputButton
     */
    public static JButton getInputButton() {
        inputButton = new JButton("导入时间复杂度文件");
        inputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Runnable r=()->{
                    try {
                        new ReadSortArray();
                        Thread.sleep(500);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                };
                Thread thread=new Thread(r);
                thread.start();
            }
        });
        return inputButton;
    }

    /**
     * 获取"写入"按钮 并 添加响应事件
     *
     * @return outputButton
     */
    public static JButton getOutputButton() {
        outputButton = new JButton("生成时间复杂度文件");
        outputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Runnable r=()->{
                    //将Swing操作加入到事件队列中
                    textArea.setResultText("正在生成TXT文件................");
                    try {
                        sortFunction=SelectComponent.getComboBoxSelection();
                        new SaveSortArray();
                        Thread.sleep(500);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        textArea.setResultText("文件生成异常！................");
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    textArea.setResultText("已经生成文件！................");
                };
                Thread thread=new Thread(r);
                thread.start();
            }
        });
        return outputButton;
    }
}
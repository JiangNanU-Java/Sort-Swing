package com.sort.mapping_frame;

import com.sort.sort_frame.ButtonComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 右侧：按钮面板
 */
class ButtonPanel extends JPanel {
    //普通按钮，导数按钮，拟合按钮
    private JButton commonButton;
    private JButton derivativeButton;
    private JButton fittingButton;

    public ButtonPanel(){
        commonButton=new JButton("普通曲线");
        derivativeButton=new JButton("一阶导数");
        fittingButton=new JButton("拟合曲线");

        setBorder(BorderFactory.createEtchedBorder());
        setLayout(new GridLayout(4,1,0,10));

        add(new JLabel("功能选择："));

        //添加按钮以及响应事件
        add(commonButton);
        commonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //打开普通画布
                new NewCanvas(10);
            }
        });

        add(derivativeButton);
        derivativeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //打开导数画布
                new NewCanvas(20);
            }
        });
        add(fittingButton);
        fittingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //打开拟合画布
                new NewCanvas(30);
            }
        });
    }
}
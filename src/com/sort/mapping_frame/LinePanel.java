package com.sort.mapping_frame;

import com.sort.sort_frame.SelectComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 右侧：画线面板
 */
class LinePanel extends JPanel {
    //画线起始位置
    private int X_Start;
    private int Y_Start;

    //画线变化量
    private int Y_Change = 10;

    //对应的数组
    private String[] sortType;
    private Color[] lineColor;

    public LinePanel() {
        //初始化
        sortType= SelectComponent.getSortType();
        lineColor= MappingFrame.getLineColor();

        setBorder(BorderFactory.createEtchedBorder());
        setLayout(new GridLayout(16, 1, 0, 10));

        //获取画线位置
        X_Start = this.getX() + 10;
        Y_Start = this.getY() + 10;

        //八次画线
        for (int i = 0; i < 8; i++) {
            //按钮
            JButton lineButton=new JButton(sortType[i]);
            lineButton.setBackground(lineColor[i]);
            lineButton.setBorder(BorderFactory.createEmptyBorder());

            //按照顺序，每个按钮有自己的属性码，通过属性码调用相应的事件
            int statusNumber=i;

            //添加事件
            lineButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new NewCanvas(40,statusNumber);
                }
            });

            add(lineButton);

            //线
            add(new LineCanve(lineColor[i]));
        }
    }

    //线的画布
    class LineCanve extends Canvas {
        private Color color;

        public LineCanve(Color linecoler) {
            //获取颜色
            color = linecoler;
        }

        //画线
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            Graphics2D g2D = (Graphics2D) g;

            //绘图提示-消除锯齿
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // 轴线粗度
            g2D.setStroke(new BasicStroke(Float.parseFloat("2.0F")));

            //对八种排序绘制
            g.setColor(color);
            //绘制直线，通过循环，将所有的点连线
            g2D.drawLine(X_Start, Y_Start, X_Start + 70, Y_Start);
        }
    }
}
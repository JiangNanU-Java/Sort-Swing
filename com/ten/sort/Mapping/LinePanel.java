package com.ten.sort.Mapping;

import com.ten.sort.SelectComponent;

import javax.swing.*;
import java.awt.*;

/**
 * 右侧：画线面板
 */
class LinePanel extends JPanel {
    private int X_Start;
    private int Y_Start;
    private int Y_Change = 10;
    private String[] sortType;
    private Color[] lineColor;

    public LinePanel() {
        //初始化
        sortType = SelectComponent.getSortType();
        lineColor = doMapping.getLineColor();

        setBorder(BorderFactory.createEtchedBorder());
        setLayout(new GridLayout(16, 1, 0, 10));

        //获取画线位置
        X_Start = this.getX() + 10;
        Y_Start = this.getY() + 10;

        //八次排序画线
        for (int i = 0; i < 8; i++) {
            JButton lineButton = new JButton(sortType[i]);
            lineButton.setBackground(lineColor[i]);
            lineButton.setBorder(BorderFactory.createEmptyBorder());
            add(lineButton);
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
            g2D.setStroke(new BasicStroke(Float.parseFloat("2.0F")));// 轴线粗度

            //对八种排序绘制
            g.setColor(color);
            //绘制直线，通过循环，将所有的点连线
            g2D.drawLine(X_Start, Y_Start, X_Start + 80, Y_Start);
        }
    }
}
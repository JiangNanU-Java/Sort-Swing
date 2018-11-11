package com.ten.demo.sin;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

//正弦函数绘制动作
public class SineDraw extends JPanel {
    private static final int SCALEFACTOR = 200;//比例系数
    private int cycles;//所希望的完整的正弦波的个数
    private int points;//要绘制的点的总数
    private double[] sines;//包含了正弦函数的值
    private int[] pts;//包含将要绘制在JPanel上的y的坐标

    public SineDraw() {
        setCycles(5);
    }//默认为五个周期

    //覆盖pointComponent方法（绘制部件、成分、组件）
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int maxWidth = getWidth();//获取面板宽度和高度
        int maxHeight = getHeight();
        double hstep = (double) maxWidth / (double) points;//hstep表示每个点之间的距离

        pts = new int[points];
        for (int i = 0; i < points; i++)
            pts[i] = (int) (sines[i] * maxHeight / 2 * .95 + maxHeight / 2);//获取每个点的y坐标：函数值*高度的一半*0.95+高度的一半-->当函数值为零时，恰好在中央，且会随高度的变化而自动拉伸，0.95确保到边界的距离
        g.setColor(Color.RED);
        for (int i = 1; i < points; i++) {//一共绘制points个点
            int x1 = (int) ((i - 1) * hstep);//起始点x：距离间隔为hstep
            int x2 = (int) (i * hstep);
            int y1 = pts[i - 1];//y去pts中y的坐标
            int y2 = pts[i];
            g.drawLine(x1, y1, x2, y2);
        }
    }

    //获取显示的周期个数
    public void setCycles(int newCycles) {
        cycles = newCycles;

        //points：比例系数*周期数*2
        points = SCALEFACTOR * cycles * 2;
        sines = new double[points];
        for (int i = 0; i < points; i++) {
            //radians：PI除以比例系数表示每隔多少数值取一个sin点，例如PI/200，表示一个PI周期中，取200次值，每隔PI/200取一次
            double radians = (Math.PI / SCALEFACTOR) * i;
            sines[i] = Math.sin(radians);//sines中保存每个点的sin值
        }
        repaint();//没调用一次setCycles 重新绘画
    }
}

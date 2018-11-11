package com.ten.sort.Mapping;

import java.awt.*;

import javax.swing.*;

/**
 * 绘图
 */
public class doMapping extends JFrame {
    //主坐标图
    private MyCanvas trendChartCanvas = new MyCanvas();
    private CommonCanvas commonCanvas = new CommonCanvas();
    private DerivativeCanvas derivativeCanvas = new DerivativeCanvas();
    private FittingCanvas fittingCanvas = new FittingCanvas();

    //辅助栏面板
    private LinePanel line = new LinePanel();
    private ButtonPanel button = new ButtonPanel();
    private JPanel ButtonAndLine = new JPanel();

    //排序线的颜色
    private static final Color[] lineColor = {Color.RED, Color.BLUE, Color.GREEN, Color.CYAN, Color.MAGENTA, Color.ORANGE, Color.BLACK, Color.GRAY};

    //构造函数
    public doMapping() {
        super("排序时间复杂度：");
        this.setBounds(0, 0, 1400, 700);
        this.setLayout(new BorderLayout());

        //添加主画布到中心
        this.add(trendChartCanvas, BorderLayout.CENTER);
        this.add(trendChartCanvas, BorderLayout.CENTER);

        //边侧辅助面板
        ButtonAndLine.setBorder(BorderFactory.createLineBorder(Color.RED));
        ButtonAndLine.setLayout(new BorderLayout(0, 50));
        ButtonAndLine.add(button, BorderLayout.NORTH);
        ButtonAndLine.add(line, BorderLayout.CENTER);
        this.add(ButtonAndLine, BorderLayout.EAST);

        this.setVisible(true);
        pack();
    }

    //获取线的颜色
    public static Color[] getLineColor() {
        return lineColor;
    }
}
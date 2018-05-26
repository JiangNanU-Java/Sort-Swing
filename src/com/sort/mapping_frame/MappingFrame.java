package com.sort.mapping_frame;

import javax.swing.*;
import java.awt.*;

/**
 * 绘图
 */
public class MappingFrame extends JFrame {
    //主坐标图
    private MyCanvas trendChartCanvas = new MyCanvas();

    //普通曲线
    private CommonCanvas commonCanvas=new CommonCanvas();

    //导数曲线
    private DerivativeCanvas derivativeCanvas=new DerivativeCanvas();

    //拟合曲线
    private FittingCanvas fittingCanvas=new FittingCanvas();

    //辅助栏面板
    private LinePanel line=new LinePanel();
    private ButtonPanel button=new ButtonPanel();
    private JPanel ButtonAndLine =new JPanel();

    //排序线的颜色
    private static final Color[] lineColor={Color.RED,Color.BLUE,Color.GREEN,Color.CYAN,Color.MAGENTA,Color.ORANGE,Color.BLACK,Color.GRAY};

    //获取线的颜色
    public static Color[] getLineColor(){
        return lineColor;
    }

    //内容面板
    public MappingFrame(MyCanvas contentCanva){
        super("排序时间：");
        this.setBounds(0, 0, 1400, 700);
        this.setLayout(new BorderLayout());

        //添加主画布到中心
        this.add(contentCanva,BorderLayout.CENTER);

        //边侧辅助面板
        ButtonAndLine.setBorder(BorderFactory.createLineBorder(Color.RED));
        ButtonAndLine.setLayout(new BorderLayout(0,50));
        ButtonAndLine.add(button,BorderLayout.NORTH);
        ButtonAndLine.add(line,BorderLayout.CENTER);
        this.add(ButtonAndLine,BorderLayout.EAST);

        this.setVisible(true);
        pack();
    }
}
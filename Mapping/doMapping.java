package com.Sort.Mapping;

import com.Sort.RandomArray;
import com.Sort.SelectComponent;
import com.Sort.SortFile.SortFile;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.swing.*;

//绘制时间直线
public class doMapping extends JFrame {
    // 定义内部类对象
    private MyCanvas trendChartCanvas = new MyCanvas();

    // 框架起点坐标
    private final int FREAME_X = 50;
    private final int FREAME_Y = 50;
    private final int FREAME_WIDTH = 1000;// 横
    private final int FREAME_HEIGHT = 500;// 纵

    // 原点坐标
    private final int Origin_X = FREAME_X + 50;
    private final int Origin_Y = FREAME_Y + FREAME_HEIGHT;

    // X,Y轴终点坐标
    private final int XAxis_X = FREAME_X + FREAME_WIDTH;
    private final int XAxis_Y = Origin_Y;
    private final int YAxis_X = Origin_X;
    private final int YAxis_Y = FREAME_Y;

    // X轴上的分度值（1分度=50素）
    private final int LENGTH_INTERVAL = 40;

    // Y轴上值
    private final int TIME_INTERVAL = 20;

    //缓存要显示的数组
    private static int[] Select;
    private static int[] Insert;
    private static int[] Bubble;
    private static int[] Heap;
    private static int[] Merge;
    private static int[] Radix;
    private static int[] Shell;
    private static int[] Quick;

    //排序线的颜色
    private static final Color[] lineColor={Color.RED,Color.BLUE,Color.GREEN,Color.CYAN,Color.MAGENTA,Color.ORANGE,Color.WHITE,Color.GRAY};

    //排序总数组
    private static int[][] sortArray;

    //缓存数组
    private int[] tempArray;

    //构造函数
    public doMapping() {
        super("排序时间复杂度：");
        this.setBounds(0, 0, 1200, 700);
        this.add(trendChartCanvas, BorderLayout.CENTER);
        this.setVisible(true);
    }

    // 画布重绘图
    class MyCanvas extends Canvas {

        //获取每个排序的时间（原单位：毫秒）
        public MyCanvas() {
            Select=SortFile.getSelect();
            Insert=SortFile.getInsert();
            Bubble=SortFile.getBubble();
            Heap=SortFile.getHeap();
            Merge=SortFile.getMerge();
            Radix=SortFile.getRadix();
            Shell=SortFile.getShell();
            Quick=SortFile.getQuick();
            sortArray= new int[][]{Select, Insert, Bubble, Heap, Merge, Radix, Shell, Quick};
        }

        //绘图
        public void paint (Graphics g) {
            Graphics2D g2D = (Graphics2D) g;
            //定义颜色
            Color c = new Color(200, 70, 0);
            g.setColor(c);
            //绘图提示-消除锯齿
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // 画坐标轴
            g2D.setStroke(new BasicStroke(Float.parseFloat("2.0F")));// 轴线粗度
            // X轴以及方向箭头
            g.drawLine(Origin_X, Origin_Y, XAxis_X, XAxis_Y);// x轴线的轴线
            g.drawLine(XAxis_X, XAxis_Y, XAxis_X - 5, XAxis_Y - 5);// 上边箭头
            g.drawLine(XAxis_X, XAxis_Y, XAxis_X - 5, XAxis_Y + 5);// 下边箭头
            // Y轴以及方向箭头
            g.drawLine(Origin_X, Origin_Y, YAxis_X, YAxis_Y);
            g.drawLine(YAxis_X, YAxis_Y, YAxis_X - 5, YAxis_Y + 5);
            g.drawLine(YAxis_X, YAxis_Y, YAxis_X + 5, YAxis_Y + 5);
            // 画X轴上的空间刻度（从坐标轴原点起，每隔LENGTH_INTERVAL(容量分度)像素画一时间点，到100万止）
            g.setColor(Color.BLUE);
            g2D.setStroke(new BasicStroke(Float.parseFloat("1.0f")));
            // X轴刻度依次变化情况
            for (int i = Origin_X, j = 0; j <= 100; i += LENGTH_INTERVAL, j += 5) {
                g.drawString(" " + j, i - 10, Origin_Y + 20);
            }
            g.drawString("数组大小/万", XAxis_X - 20, XAxis_Y + 20);
            // 画Y轴上时间刻度（从坐标原点起，每隔10像素画一格，到1000止）
            for (int i = Origin_Y, j = 0; j <= 500; i -= TIME_INTERVAL, j += TIME_INTERVAL) {
                g.drawString(j + " ", Origin_X - 30, i + 3);
            }
            g.drawString("时间/秒", YAxis_X - 5, YAxis_Y - 15);// 时间刻度小箭头值
            // 画网格线
            g.setColor(Color.LIGHT_GRAY);
            // 坐标内部横线
            for (int i = Origin_Y; i >= YAxis_Y; i -= TIME_INTERVAL) {
                g.drawLine(Origin_X, i, XAxis_X, i);
            }
            // 坐标内部竖线
            for (int i = Origin_X; i <= XAxis_X; i += LENGTH_INTERVAL) {
                g.drawLine(i, Origin_Y, i, YAxis_Y);
            }

            g.setColor(c);
            g2D.setStroke(new BasicStroke(Float.parseFloat("2.0F")));// 轴线粗度

            //对八种排序绘制
            for (int j = 0; j < 8; j++) {
                g.setColor(lineColor[j]);
                tempArray=new int[21];
                tempArray=sortArray[j];

                //绘制直线，通过循环，将所有的点连线
                for (int i = 0; i < 20; i++) {
                    g2D.drawLine(Origin_X + i * LENGTH_INTERVAL, Origin_Y - tempArray[i],
                            Origin_X + (i + 1) * LENGTH_INTERVAL, Origin_Y - tempArray[i + 1]);
                }
            }
        }
    }
}
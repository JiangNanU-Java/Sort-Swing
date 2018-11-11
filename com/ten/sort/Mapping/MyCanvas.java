package com.ten.sort.Mapping;

import com.ten.sort.SelectComponent;
import com.ten.sort.SortFile.SortFile;

import java.awt.*;

// 主画布
public class MyCanvas extends Canvas {
    // 画布框架起点坐标
    private final int FREAME_X = 50;
    private final int FREAME_Y = 50;
    private final int FREAME_WIDTH = 1000;// 横
    private final int FREAME_HEIGHT = 500;// 纵

    // 画布原点坐标
    private final int Origin_X = FREAME_X + 50;
    private final int Origin_Y = FREAME_Y + FREAME_HEIGHT;

    // 画布X,Y轴终点坐标
    private final int XAxis_X = FREAME_X + FREAME_WIDTH;
    private final int XAxis_Y = Origin_Y;
    private final int YAxis_X = Origin_X;
    private final int YAxis_Y = FREAME_Y;

    // 画布X，Y轴上的分度值（1分度=像素）
    private final int LENGTH_INTERVAL = 40;
    private final int TIME_INTERVAL = 20;

    private Color[] lineColor;

    //缓存要显示的数组
    private static int[] Select;
    private static int[] Insert;
    private static int[] Bubble;
    private static int[] Heap;
    private static int[] Merge;
    private static int[] Radix;
    private static int[] Shell;
    private static int[] Quick;

    //排序总数组
    private static int[][] sortArray;

    //每次画线时的缓存数组
    private int[] tempArray;

    //画线的的名称
    private String[] lineName;

    //获取每个排序的时间（原单位：毫秒）
    public MyCanvas() {
        //获取画线颜色
        lineColor = doMapping.getLineColor();

        //获取画线的名称
        lineName = SelectComponent.getSortType();

        //为排序数组获取数值
        Select = SortFile.getSelect();
        Insert = SortFile.getInsert();
        Bubble = SortFile.getBubble();
        Heap = SortFile.getHeap();
        Merge = SortFile.getMerge();
        Radix = SortFile.getRadix();
        Shell = SortFile.getShell();
        Quick = SortFile.getQuick();
        sortArray = new int[][]{Select, Bubble, Quick, Shell, Insert, Heap, Radix, Merge};
    }

    //绘图
    public void paint(Graphics g) {
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

        //八种排序绘制
        for (int j = 0; j < 8; j++) {
            g.setColor(lineColor[j]);
            if (sortArray[j] != null) {
                tempArray = new int[sortArray[j].length];
                tempArray = sortArray[j];
                System.out.println(j);
            } else
                continue;
            //绘制直线，通过循环，将所有的点连线
            for (int i = 0; i < 20; i++) {
                g2D.drawLine(Origin_X + i * LENGTH_INTERVAL, Origin_Y - tempArray[i],
                        Origin_X + (i + 1) * LENGTH_INTERVAL, Origin_Y - tempArray[i + 1]);
                if (i == 19) {
                    g2D.drawString(lineName[j], Origin_X + (i + 1) * LENGTH_INTERVAL + 10, Origin_Y - tempArray[i + 1]);
                }
            }
        }
    }
}
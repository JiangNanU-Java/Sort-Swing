package com.sort.mapping_frame;

import com.sort.sort_frame.SelectComponent;
import com.sort.sort_file.*;

import java.awt.*;

/**
 * 坐标图
 */
public class MyCanvas extends Canvas {
    // 画布框架起点坐标
    private static final int FREAME_X = 50;
    private static final int FREAME_Y = 50;
    private static final int FREAME_WIDTH = 1000;// 横
    private static final int FREAME_HEIGHT = 500;// 纵

    // 画布原点坐标
    protected static final int Origin_X = FREAME_X + 50;
    protected static final int Origin_Y = FREAME_Y + FREAME_HEIGHT;

    // 画布X,Y轴终点坐标
    private static final int XAxis_X = FREAME_X + FREAME_WIDTH;
    private static final int XAxis_Y = Origin_Y;
    private static final int YAxis_X = Origin_X;
    private static final int YAxis_Y = FREAME_Y;

    // 画布X，Y轴上的分度值（1分度=像素）
    protected static final int LENGTH_INTERVAL = 40;
    private static final int TIME_INTERVAL = 20;

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
    protected static int[][] sortArray;

    //画线的颜色
    protected static Color[] lineColor;

    //每次画线时的缓存数组
    protected int[] tempArray;

    //画线的的名称
    protected static String[] lineName;

    //线的单位
    protected static String srotUnit;

    //不同的状态对应的对象
    private CommonCanvas common;
    private DerivativeCanvas derivative;
    private FittingCanvas fitting;
    private LineCanvas lineCanvas;

    //对应不同状态的图像
    private int status=0;
    public void setStatus(int number){
        status=number;
    }
    //获取排序数据总数组
    public static int[][] getSortArray(){
        return sortArray;
    }

    //获取每个排序的时间（原单位：毫秒）
    public MyCanvas() {
        //获取画线颜色
        lineColor = MappingFrame.getLineColor();

        //获取画线的名称
        lineName = SelectComponent.getSortType();

        //为排序数组获取数值
        Select = SortResult.getSelect();
        Insert = SortResult.getInsert();
        Bubble = SortResult.getBubble();
        Heap = SortResult.getHeap();
        Merge = SortResult.getMerge();
        Radix = SortResult.getRadix();
        Shell = SortResult.getShell();
        Quick = SortResult.getQuick();

        //与排序顺序相同
        sortArray = new int[][]{Select, Bubble, Quick, Shell, Insert, Heap, Radix, Merge};
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

        buttonEventCanvas(g,status);
    }

    //在坐标图上根据选择填充不同的内容
    private void buttonEventCanvas(Graphics g,int status){
        switch (status){
            //初始化空面板
            case 0:

                break;
            //普通曲线状态 1
            case 10:
                common=new CommonCanvas();
                common.paintLine(g);
                break;
            //一阶导数状态 2
            case 20:
                derivative=new DerivativeCanvas();
                derivative.paintLine(g);
                break;
            //拟合曲线状态 3
            case 30:
                fitting=new FittingCanvas();
                fitting.paintLines(g);
                break;
            //单线按钮状态 4
            case 40:
                lineCanvas=new LineCanvas(40);
                lineCanvas.paintLine(g);
                break;
            case 41:
                lineCanvas=new LineCanvas(41);
                lineCanvas.paintLine(g);
                break;
            case 42:
                lineCanvas=new LineCanvas(42);
                lineCanvas.paintLine(g);
                break;
            case 43:
                lineCanvas=new LineCanvas(43);
                lineCanvas.paintLine(g);
                break;
            case 44:
                lineCanvas=new LineCanvas(44);
                lineCanvas.paintLine(g);
                break;
            case 45:
                lineCanvas=new LineCanvas(45);
                lineCanvas.paintLine(g);
                break;
            case 46:
                lineCanvas=new LineCanvas(46);
                lineCanvas.paintLine(g);
                break;
            case 47:
                lineCanvas=new LineCanvas(47);
                lineCanvas.paintLine(g);
                break;
            default:
                break;
        }
    }
}
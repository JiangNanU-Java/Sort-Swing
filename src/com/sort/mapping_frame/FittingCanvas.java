package com.sort.mapping_frame;

import com.sort.sort_file.SortResult;
import com.sort.sort_function.MakeFitting;
import com.sort.sort_function.PaintFitLine;

import java.awt.*;

/**
 * 画拟合曲线
 */
public class FittingCanvas extends MyCanvas {
    //获取曲线参数数组
    private static double[][] paras;

    //参数个数
    private int para_Length;

    public static double[][] getParas(){
        return paras;
    }

    //进行拟合
    public FittingCanvas(){
        MakeFitting.make();
    }

    //画多条线
    public void paintLines(Graphics g) {
        //获取参数数组
        paras = MakeFitting.getParaNumber();

        //获取参数长度
        para_Length = paras.length;
        System.out.println("最小二乘法拟合数组参数个数为 " + para_Length);

        //八种排序绘制
        for (int j = 0; j < 8; j++) {
            if (paras[j]==null)
                continue;

            g.setColor(lineColor[j]);
            Graphics2D g2D = (Graphics2D) g;

            //根据单位获取粗度
            srotUnit = SortResult.getUnit(lineName[j]);
            if (srotUnit == "s") {
                g2D.setStroke(new BasicStroke(Float.parseFloat("4.0F")));// 轴线粗度
            } else if (srotUnit == "ms") {
                g2D.setStroke(new BasicStroke(Float.parseFloat("2.0F")));// 轴线粗度
            } else
                g2D.setStroke(new BasicStroke(Float.parseFloat("1.0F")));// 轴线粗度

            //画拟合曲线
            PaintFitLine.paintFitLine(j,g);
        }
    }
}

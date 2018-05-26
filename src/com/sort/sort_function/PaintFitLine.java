package com.sort.sort_function;

import com.sort.mapping_frame.DerivativeCanvas;
import com.sort.mapping_frame.FittingCanvas;
import com.sort.mapping_frame.MyCanvas;

import java.awt.*;

/**
 * 画单条的拟合曲线
 */
public class PaintFitLine extends MyCanvas {
    //分度值，画100个点
    private static final int X_NUMBER=100;

    private static double[][] paras;

    //单个按钮触发的简单类型的拟合曲线
    public static void paintFitLine(int j, Graphics g2D, boolean status){
        if (status) {
            paras = FittingCanvas.getParas();

            g2D.setColor(Color.WHITE);

            //i相当于x的值
            for (int i = 0; i < X_NUMBER; i++) {
                //X轴上每个x值间隔8像素
                g2D.drawLine(Origin_X + i * 8, Origin_Y - getYvalue(j, i),
                        Origin_X + (i + 1) * 8, Origin_Y - getYvalue(j, i + 1));
            }
        }
    }

    /**
     * 画单条拟合曲线
     * @param j 线的状态码
     * @param g2D 线的面板对象
     */
    public static void paintFitLine(int j, Graphics g2D){
        paras=FittingCanvas.getParas();

        //i相当于x的值
        for (int i = 0; i < X_NUMBER; i++) {
            //X轴上每个x值间隔8像素
            g2D.drawLine(Origin_X + i * 8, Origin_Y - getYvalue(j,i),
                    Origin_X + (i + 1) * 8, Origin_Y - getYvalue(j,i + 1));

            //保存导数,每隔1保存
                double y_value_double = (getValue_double(j, i + 1) - getValue_double(j,i));
                System.out.println("本次导数的值" + y_value_double);
                DerivativeCanvas.setDerivative(j, i, y_value_double);

            //在线末尾加上名字
            if (i == X_NUMBER-1) {
                g2D.drawString(lineName[0] + ":" + srotUnit, Origin_X + (i + 1) * 8 + 10, Origin_Y - getYvalue(j,i));
            }
        }
    }

    //获取拟合计算后的Y值，并转换成int类型
    private static int getYvalue(int sortType, int i){
        int j=sortType;
        //6次拟合求y值
        int value= (int) (paras[j][0]+paras[j][1]*i+paras[j][2]*i*i+paras[j][3]*i*i*i+paras[j][4]*i*i*i*i+paras[j][5]*i*i*i*i*i);
        return value;
    }

    //求double类型值，用于导数计算
    private static double getValue_double(int sortType,int i){
        int j=sortType;
        //6次拟合求y值
        double value_double= (paras[j][0]+paras[j][1]*i+paras[j][2]*i*i+paras[j][3]*i*i*i+paras[j][4]*i*i*i*i+paras[j][5]*i*i*i*i*i);
        return value_double;
    }
}

package com.sort.sort_function;

import com.sort.mapping_frame.DerivativeCanvas;
import com.sort.mapping_frame.FittingCanvas;
import com.sort.mapping_frame.MyCanvas;

import java.awt.*;

public class PaintDerivative extends MyCanvas{

    private static double[][] y_derivative=new double[8][100];

    /**
     * 画单挑线的导数
     * @param j
     * @param g2D
     * @param status
     */
    public static void paintDerLine(int j,Graphics2D g2D,boolean status){
        y_derivative = DerivativeCanvas.getDerivative();
        if (y_derivative[j] != null) {
            //画基准线
            g2D.setStroke(new BasicStroke(Float.parseFloat("1.0")));
            g2D.setColor(Color.BLACK);
            g2D.drawLine(Origin_X , Origin_Y -100,
                    Origin_X + (100) * 8, Origin_Y - 100);
            //画提示语
            g2D.drawString("导数值为：1 ",Origin_X+100*8+10,Origin_Y-100);

            //i相当于x的值
            for (int i = 0; i < 99; i++) {
                //X轴上每个x值间隔8像素
                g2D.drawLine(Origin_X + i * 8, Origin_Y - getDerValue(j,i),
                        Origin_X + (i + 1) * 8, Origin_Y - getDerValue(j,i+1));
                System.out.println(getDerValue(j,i)+y_derivative[j][i]);
                //在线末尾加上名字
                if (i == 98) {
                    g2D.drawString(lineName[j] + ":", Origin_X + (i + 1) * 8 + 10, Origin_Y - getDerValue(j, i+1));
                }
            }
        }
    }

    /**
     * 导数
     * @param j 线的状态码
     * @param g2D 线的面板对象
     */
    public static void paintDerLine(int j, Graphics g2D) {
        y_derivative = DerivativeCanvas.getDerivative();
        if (y_derivative[j] != null) {
            //i相当于x的值
            for (int i = 0; i < 99; i++) {
                //X轴上每个x值间隔8像素
                g2D.drawLine(Origin_X + i * 8, Origin_Y - getDerValue(j,i),
                        Origin_X + (i + 1) * 8, Origin_Y - getDerValue(j,i+1));
                System.out.println(getDerValue(j,i)+y_derivative[j][i]);
                //在线末尾加上名字
                if (i == 98) {
                    g2D.drawString(lineName[j] + ":", Origin_X + (i + 1) * 8 + 10, Origin_Y - getDerValue(j, i+1));
                }
            }
        }
    }

    //获取拟合计算后的Y值，并转换成int类型
    private static int getDerValue(int sortType, int i){
        int j=sortType;
        //6次拟合求y值
        int value= (int) (y_derivative[j][i]*100);
        return value;
    }
}

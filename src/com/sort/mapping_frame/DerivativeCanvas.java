package com.sort.mapping_frame;

import com.sort.sort_file.SortResult;
import com.sort.sort_function.PaintDerivative;
import com.sort.sort_function.PaintFitLine;

import javax.swing.*;
import java.awt.*;

public class DerivativeCanvas extends MyCanvas {
    private static double[][] derivative=new double[8][100];

    public DerivativeCanvas(){
    }

    public void paintLine(Graphics g){
        //八种排序绘制
        for (int j = 0; j < 8; j++) {
            if (derivative[j]==null)
                continue;

            Graphics2D g2D = (Graphics2D) g;

            //画基准线
            g2D.setStroke(new BasicStroke(Float.parseFloat("4.0")));
            g2D.setColor(Color.BLACK);
            g2D.drawLine(Origin_X , Origin_Y -100,
                    Origin_X + (100) * 8, Origin_Y - 100);
            //画提示语
            g2D.drawString("导数值为：1 ",Origin_X+100*8+10,Origin_Y-100);

            g2D.setColor(lineColor[j]);
            g2D.setStroke(new BasicStroke(Float.parseFloat("2.0F")));// 轴线粗度
            //画导数曲线
            PaintDerivative.paintDerLine(j,g);
        }
    }

    //在拟合曲线的方法中设置导数值
    public static void setDerivative(int sortIndex,int x_value,double y_value){
        derivative[sortIndex][x_value]=y_value;
    }

    public static double[][] getDerivative(){
        return derivative;
    }

}

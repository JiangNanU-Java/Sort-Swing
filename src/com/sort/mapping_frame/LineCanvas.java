package com.sort.mapping_frame;

import com.sort.sort_file.SortResult;
import com.sort.sort_function.PaintDerivative;
import com.sort.sort_function.PaintFitLine;

import java.awt.*;

public class LineCanvas extends MyCanvas {
    private int lineStatus;
    public LineCanvas(int status){
        lineStatus=status;
    }
    public void paintLine(Graphics g){
        //获取状态码
        int j=lineStatus-40;

        //若为读取到值，报错
        if (sortArray[j]!=null) {
            g.setColor(lineColor[j]);
            Graphics2D g2D = (Graphics2D) g;

            tempArray = sortArray[j];

            //根据单位获取粗度
            srotUnit = SortResult.getUnit(lineName[j]);
            if (srotUnit == "s") {
                g2D.setStroke(new BasicStroke(Float.parseFloat("4.0F")));// 轴线粗度
            } else if (srotUnit == "ms") {
                g2D.setStroke(new BasicStroke(Float.parseFloat("2.0F")));// 轴线粗度
            } else
                g2D.setStroke(new BasicStroke(Float.parseFloat("1.0F")));// 轴线粗度

            //绘制直线，通过循环，将所有的点连线
            for (int i = 0; i < 20; i++) {
                g2D.drawLine(Origin_X + i * LENGTH_INTERVAL, Origin_Y - tempArray[i],
                        Origin_X + (i + 1) * LENGTH_INTERVAL, Origin_Y - tempArray[i + 1]);

                //在线末尾加上名字
                if (i == 19) {
                    g2D.drawString(lineName[j] + ":" + srotUnit, Origin_X + (i + 1) * LENGTH_INTERVAL + 10, Origin_Y - tempArray[i + 1]);
                }
            }

            //画拟合曲线 导数曲线
            PaintFitLine.paintFitLine(j, g2D, true);
            PaintDerivative.paintDerLine(j,g2D,true);
        }else
            System.out.println("未导入 "+lineName[j]+" 数值");
    }
}

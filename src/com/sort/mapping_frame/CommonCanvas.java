package com.sort.mapping_frame;

import com.sort.sort_file.SortResult;

import java.awt.*;

public class CommonCanvas extends MyCanvas{
    public CommonCanvas(){
    }

    public void paintLine(Graphics g){
        //八种排序绘制
        for (int j = 0; j < 8; j++) {
            g.setColor(lineColor[j]);
            Graphics2D g2D = (Graphics2D) g;

            //若对应的数组不为空，保存到缓存数组中
            if (sortArray[j] != null) {
                tempArray = new int[sortArray[j].length];
                tempArray = sortArray[j];
            } else
                continue;

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
        }
    }
}

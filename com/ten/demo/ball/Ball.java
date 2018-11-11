package com.ten.demo.ball;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
//Ball 小球对象类
public class Ball {
    //小球尺寸
    private static final int XSIZE=15;
    private static final int YSIZE=15;
    //小球位置信息
    private double x=0;
    private double y=0;
    //小球移动的距离d
    private double dx=1;
    private double dy=1;
    //move移动方法
    public void move(Rectangle2D bounds){
        //位置d距离
        x+=dx;
        y+=dy;
        //碰触左边缘
        if (x<bounds.getMinX()){
            x=bounds.getMinX();
            dx=-dx;
        }//右边缘
        if (x+XSIZE>=bounds.getMaxX()){
            x=bounds.getMaxX()-XSIZE;
            dx=-dx;
        }
        //上边缘
        if (y<bounds.getMinY()){
            y=bounds.getMinY();
            dy=-dy;
        }
        //下边缘
        if (y+YSIZE>=bounds.getMaxY()){
            y=bounds.getMaxY()-YSIZE;
            dy=-dy;
        }
    }
    //椭圆，获取小球的Shape
    public Ellipse2D getShape(){
        return new Ellipse2D.Double(x,y,XSIZE,YSIZE);
    }
}

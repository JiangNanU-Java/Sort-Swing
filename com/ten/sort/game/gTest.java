package com.ten.sort.game;

import com.ten.sort.sortMethod.Select_Sort;

import javax.swing.*;
import java.awt.*;

public class gTest extends JPanel {
    private static final int ARRAY_MAXLENGTH = 100000;//最大为十万
    private static int points = 1000;//每隔100 一共画1000个点
    private long[] sort_y_value;
    private int[] sory_y_coordinate;
    private static int sort_size = 100;
    private doMySort mySort=new doMySort();
    private int ok=0;
    private long maxLength=mySort.getSortTime(ARRAY_MAXLENGTH);
    public gTest(){setSort(ok);}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int maxWidth = getWidth();
        int maxHeight = getHeight();
        double hstep=(double)maxWidth/(double)points;


        sory_y_coordinate=new int[points];
        for (int i=0;i<points;i++){
            sory_y_coordinate[i]= (int) ((double)(1/((int)(maxLength/sort_y_value[i])))*maxHeight*0.8);
        }
        g.setColor(Color.RED);
        for (int i=1;i<points;i++){
            int x1= (int) ((i-1)*hstep);
            int x2= (int) (i*hstep);
            int y1=sory_y_coordinate[i-1];
            int y2=sory_y_coordinate[i];
            g.drawLine(x1,y1,x2,y2);
        }
    }

    public void setSort(int ok) {
        if (ok==0){
        sort_y_value=new long[points];
        for (int i = 0; i < points; i++) {
            sort_y_value[i]=mySort.getSortTime(sort_size);
            sort_size += 100;
            repaint();}
        }
        ok=1;
    }

    class doMySort {
        private int number;
        private int[] array;
        public long getSortTime(int length) {
            array=new int[length];
            for (int i = 0; i < length; i++) {
                number = (int) (Math.random() * 1000000);
                array[i] = number;
            }
            long startTime=System.currentTimeMillis();   //获取开始时间
            Select_Sort.sort(array);
            long endTime=System.currentTimeMillis(); //获取结束时间
            return (endTime-startTime);
        }
    }
}

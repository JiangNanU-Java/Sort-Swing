package com.sort.sort_executors;

import com.sort.dialog_frame.ExDialog;
import com.sort.sort_file.SortResult;
import com.sort.sort_frame.ProgressComponent;
import com.sort.sort_frame.TextComponent;
import com.sort.sort_method.*;
/**
 * 每次排序都生成一个doSort对象
 * 选择具体的排序方法
 * 计算排序的时间
 */
public class doSort {
    //记录排序用时
    private long startTime;
    private long endTime;

    //数组开始时初始排序的数组大小
    private int length=0;

    //每次排序都重新生成对应大小的随机数组
    private int[] array;

    //每个对象对应的排序用时数组
    private int[] times;

    //每个doSort对象对应的排序方式
    private String sortType;

    //文本框对象的操作
    private TextComponent textArea;

    //进度条的操作
    private ProgressComponent progress;

    public doSort(String sortType){
        //初始化
        this.sortType=sortType;
        times=new int[21];
        textArea=new TextComponent();
        progress=new ProgressComponent();

        textArea.setSortText("---------------"+sortType+"---------------已经开始---------------");
        textArea.setResultText("---------------"+sortType+"---------------已经开始---------------");
        progress.setProgress(0);

        //开始排序
        try {
            this.sort();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("在sort方法中遇到异常");
        }


        textArea.setSortText("---------------"+sortType+"---------------排序完成---------------");
        textArea.setResultText("---------------"+sortType+"---------------排序完成---------------");

        //弹出对话框提示
        new ExDialog(null, sortType + " 排序完成");
    }

    //排序方法的选择
    private void sort(){
        //从0开始，每次五万，直到100万
        for (int i=0;i<21;i++) {
            //生成随机数组array
            array=new int[length];
            new Make_RandomArray(array,length);

            //记录一次排序的开始时间
            startTime=System.currentTimeMillis();

            if (i>0) {
                //排序选择器
                switch (sortType) {
                    case "选择排序":
                        Select_Sort.sort(array);
                        break;
                    case "冒泡排序":
                        Bubble_Sort.sort(array);
                        break;
                    case "快速排序":
                        Quick_Sort.quick(array);
                        break;
                    case "希尔排序":
                        Shell_Sort.sort(array);
                        break;
                    case "插入排序":
                        Insert_Sort.sort(array);
                        break;
                    case "堆排序":
                        Heap_Sort.sort(array);
                        break;
                    case "基数排序":
                        Radix_Sort.sort(array);
                        break;
                    case "合并排序":
                        Merge_Sort.sort(array);
                        break;
                    default:
                        break;
                }
            }

            //记录一次排序的结束时间
            endTime=System.currentTimeMillis();

            //记录本次排序所用时间 ms
            this.setTimes(i, (int) (endTime-startTime));

            //更新进度条
            if (i>0) {
                progress.setProgress(length/1000);
            }

            //排序完成后  将结果保存在SortFile中，（排序类型，排序时间）
            if (i==20) {
                SortResult.setSortTypeArray(sortType,getTimes());
            }

            length+=50000;
        }
    }

    //记录每次排序所用时间 ms
    private void setTimes(int index,int sorttime){
        times[index]=sorttime;

        //将一次的排序时间打印在排序文本框
        textArea.setSortText(sortType+"--数组大小:"+(index*50000)+"--用时:"+(endTime-startTime)+"ms"+"->"+(endTime-startTime)/1000+"s");
    }

    //获取某个排序的 全部排序用时的记录
    public int[] getTimes(){
        return times;
    }
}

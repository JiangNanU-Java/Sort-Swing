package com.ten.sort;

import com.ten.sort.SortFile.SortFile;

/**
 * 排序线程池
 * 点击一次排序按钮，则开启一个排序线程
 * 排序完成后会自动把结果存储在SortFile对应数组中
 */
public class SortExecutors {
    private String sortType;
    private doSort newSortThread;

    private TextComponent textArea;

    public SortExecutors() {
        //获取排序类型
        sortType = SelectComponent.getComboBoxSelection();
        textArea = new TextComponent();

        try {
            System.out.println("选择的排序方式为：" + sortType);

            //将文本框切换到排序显示模式
            TextComponent.setSortText("--------开始排序--------");
            TextComponent.setResultText("--------开始排序--------");

            newSortThread = new doSort(sortType);

            //将结果保存在SortFile中
            SortFile.setSortTypeArray(sortType, newSortThread.getTimes());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

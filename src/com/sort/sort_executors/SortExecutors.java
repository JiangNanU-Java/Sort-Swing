package com.sort.sort_executors;

import com.sort.sort_file.*;
import com.sort.sort_frame.SelectComponent;
import com.sort.sort_frame.TextComponent;

/**
 * 排序线程
 * 点击一次排序按钮，则开启一个排序线程
 * 排序完成后会自动把结果存储在SortFile对应数组中
 */
public class SortExecutors {
    //排序类型
    private String sortType;

    //新的排序线程
    private doSort newSortThread;

    //文本框操作
    private TextComponent textArea;

    public SortExecutors() {
        textArea = new TextComponent();

        //获取排序类型
        sortType = SelectComponent.getComboBoxSelection();

        System.out.println("选择的排序方式为：" + sortType);

        try {
            newSortThread = new doSort(sortType);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

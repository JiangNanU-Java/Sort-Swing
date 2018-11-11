package com.ten.sort.SortFile;

import com.ten.sort.TextComponent;

import java.io.File;

/**
 * 文件读写
 */
public class SortFile {
    //读写文件操作的文件名
    protected File file;

    //获取复选框选择
    protected String[] filenames;

    //字符构建
    protected StringBuilder stringBuilder;

    //对文本框的操作
    protected TextComponent textComponent;

    //TODO 默认文件和默认目录
    protected static final String DEFAULT_PATH = "C:\\Users\\59682\\Desktop\\Java\\SortArray\\Default.txt";
    protected static final String DEFAULT_DIRECTORY = "C:\\Users\\59682\\Desktop\\Java\\SortArray\\";

    //保存每个排序方法的结果的数组
    private static int[] Select;
    private static int[] Insert;
    private static int[] Bubble;
    private static int[] Heap;
    private static int[] Merge;
    private static int[] Radix;
    private static int[] Shell;
    private static int[] Quick;

    //获取参数对应的 排序数组
    public static int[] getSortTypeArray(String sortType) {
        int[] arrays = new int[21];

        //选择器
        switch (sortType) {
            case "选择排序":
                arrays = Select;
                break;
            case "冒泡排序":
                arrays = Bubble;
                break;
            case "快速排序":
                arrays = Quick;
                break;
            case "希尔排序":
                arrays = Shell;
                break;
            case "插入排序":
                arrays = Insert;
                break;
            case "堆排序":
                arrays = Heap;
                break;
            case "基数排序":
                arrays = Radix;
                break;
            case "合并排序":
                arrays = Merge;
                break;
            default:
                break;
        }
        return arrays;
    }

    //将参数对应的排序结果，写入排序数组
    public static void setSortTypeArray(String arrayType, int[] array) {
        //排序选择器
        switch (arrayType) {
            case "选择排序":
                Select = new int[array.length];
                Select = array;
                break;
            case "冒泡排序":
                Bubble = new int[array.length];
                Bubble = array;
                break;
            case "快速排序":
                Quick = new int[array.length];
                Quick = array;
                break;
            case "希尔排序":
                Shell = new int[array.length];
                Shell = array;
                break;
            case "插入排序":
                Insert = new int[array.length];
                Insert = array;
                break;
            case "堆排序":
                Heap = new int[array.length];
                Heap = array;
                break;
            case "基数排序":
                Radix = new int[array.length];
                Radix = array;
                break;
            case "合并排序":
                Merge = new int[array.length];
                Merge = array;
                break;
            default:
                break;
        }
    }


    public static int[] getSelect() {
        return Select;
    }

    public static int[] getInsert() {
        return Insert;
    }

    public static int[] getBubble() {
        return Bubble;
    }

    public static int[] getHeap() {
        return Heap;
    }

    public static int[] getMerge() {
        return Merge;
    }

    public static int[] getRadix() {
        return Radix;
    }

    public static int[] getShell() {
        return Shell;
    }

    public static int[] getQuick() {
        return Quick;
    }
}

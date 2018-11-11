package com.ten.sort.sortmethod;

//排序模板类：升序 从小到大
public abstract class Example {
    public static void sort(int[] array) {
        //具体排序方法
    }

    //less判断是否小于
    public static boolean less(int x, int y) {
        return x < y;
    }

    //swap交换两元素
    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    //show打印数组
    public static void show(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    //isSorted是否排序：从小到大
    public static boolean isSorted(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (less(array[i], array[i - 1])) return false;
        }
        return true;
    }
}

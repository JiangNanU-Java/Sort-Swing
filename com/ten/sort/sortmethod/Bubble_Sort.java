package com.ten.sort.sortmethod;

/**
 * 冒泡排序
 */
public class Bubble_Sort extends Example {
    public static void sort(int[] array) {
        int tmp;
        for (int i = 1; i < array.length; i++) {
            // 判断相邻两个数据的大小，并把较大的数往后冒泡
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
    }
}

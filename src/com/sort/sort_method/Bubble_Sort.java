package com.sort.sort_method;
/**
 * 冒泡排序：从小到大
 * 时间复杂度：N^2
 * 数据移动：N^2次交换
 * 运行时间：与输入无关
 */
public class Bubble_Sort extends Example {
    public static void sort(int[] array){
        int tmp;
        //外循环：经过了一个冒的过程，可以使一个最小的元素冒出来，如果数组里面有 n 个元素，就得冒 n-1 次。
        for(int i = 1; i < array.length; i++) {
            //内循环：判断相邻两个数据的大小，把较小的元素冒泡到前方
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

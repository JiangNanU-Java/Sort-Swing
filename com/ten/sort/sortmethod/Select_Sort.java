package com.ten.sort.sortmethod;

//选择排序
public class Select_Sort extends Example {
    public static void sort(int[] array) {
        int N = array.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++)
                if (less(array[j], array[min])) min = j;
            swap(array, i, min);
        }
    }
}

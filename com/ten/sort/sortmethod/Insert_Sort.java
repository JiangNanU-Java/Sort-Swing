package com.ten.sort.sortmethod;

//插入排序
public class Insert_Sort extends Example {
    public static void sort(int[] array) {
        int N = array.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && less(array[j], array[j - 1]); j--)
                swap(array, j, j - 1);
        }
    }
}

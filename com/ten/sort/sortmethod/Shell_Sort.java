package com.ten.sort.sortmethod;

public class Shell_Sort extends Example {

    public static void sort(int[] array) {
        int temp;
        int j;
        int x = 0;

        for (int r = array.length / 2; r >= 1; r /= 2) {
            for (int i = r; i < array.length; i++) {
                temp = array[i];
                j = i - r;
                while (j >= 0 && temp < array[j]) {
                    array[j + r] = array[j];
                    j -= r;
                }
                array[j + r] = temp;
            }
            x++;
        }
    }
}

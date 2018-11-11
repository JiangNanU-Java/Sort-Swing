package com.ten.sort;

public class RandomArray {
    //数组长度，数组，默认长度
    private int number;

    public RandomArray(int[] array, int length) {
        System.out.println("数组的容量为:" + length);
        try {
            for (int i = 0; i < length; i++) {
                number = (int) (Math.random() * 1000000);
                array[i] = number;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("创建数组失败");
        }
    }
}

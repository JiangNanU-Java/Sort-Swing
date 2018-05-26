package com.sort.sort_method;

/**
 * 生成随机数组
 */
public class Make_RandomArray {
    //数组长度，数组，默认长度
    private int number;

    public Make_RandomArray(int[] array, int length) {
        try {
            for (int i = 0; i < length; i++) {
                number = (int) (Math.random() * 1000000);
                array[i] = number;
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("创建数组失败");
        }
    }
}

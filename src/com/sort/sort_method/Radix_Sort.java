package com.sort.sort_method;

/**
 * 基数排序：数组实现
 * 1、获取位数
 * 2、从低位到高位，进行计算取余获取本位的十进制数字
 * 3、进行排序，并按顺序保存到对应桶中
 * 4、将本位排序好的数组，重新放回原数组中，得到这一步的有序数组
 * 5、对下一位进行排序，循环
 * 6、直到全部位数都有序，这时候数组有序
 */
public class Radix_Sort extends Example {
    public static void sort(int[] array) {
        //获取最大元素
        int tmp = getMax(array);
        //计算位数loop
        int loop = 0;
         while ((tmp = tmp / 10) > 0){
             loop++;
         }
        int count = 1, k, lsd;
        //排序桶用于保存每次排序后的结果，这一位上排序结果相同的数字放在同一个桶里
        int[][] bubble = new int[10][array.length];
        //用于保存每个桶里有多少个数字
        int[] order = new int[10];

        //从“个”位开始循环
        tmp = 1;
        while (count <= loop) {
            for (int i = 0; i < array.length; i++) {
                //求得本位的数字
                lsd = (array[i] / tmp) % 10;
                //[对应数字的桶] [第order[对应桶]个元素]
                bubble[lsd][order[lsd]] = array[i];
                order[lsd]++;
            }
            //对每一个“位”排序完成后，按顺序重新放回array数组中
            k = 0;
            for (int i = 0; i < 10; i++) {
                if (order[i] != 0) {
                    for (int j = 0; j < order[i]; j++) {
                        array[k] = bubble[i][j];
                        k++;
                    }
                }
                order[i] = 0;
            }
            //然后由得到的数组，开始对下一位的排序
            tmp *= 10;
            count++;
        }
    }

    /**
     * 获取基数排序的最大元素，从而求得位数k
     * @param arr 数组
     * @return 最大元素
     */
    private static int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i])
                max = arr[i];
        }
        return max;
    }

    //判断是否为空
    private static boolean isEmpty(int[] num) {
        return num == null || num.length == 0;
    }
}

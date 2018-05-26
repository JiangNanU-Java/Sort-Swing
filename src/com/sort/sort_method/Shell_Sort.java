package com.sort.sort_method;
/**
 * 希尔排序：从小到大
 * 时间复杂度：不稳定
 * 数据移动：不稳定
 * 运行时间：随机性大
 * 使数组h局部有序，然后应用插入排序的原理
 */
public class Shell_Sort extends Example {
    public static void sort(int[] array){
        int N=array.length;
        int h=1;
        //h：1、4、13、40、121、364、1093...
        while (h<N/3)
            h=h*3+1;
        //判断，h逐渐缩小进行排序
        while (h>=1){
            //外循环：从第二组首位开始逐渐向右遍历
            for (int i=h;i<N;i++){
                //内循环：从右向左，与同组元素冒泡比较，直到排完第一组和第二组结束
                for (int j=i;j>=h;j-=h)
                    if (array[j]<array[j-h])
                        swap(array,j,j-h);
            }
            h=h/3;
        }
    }
}

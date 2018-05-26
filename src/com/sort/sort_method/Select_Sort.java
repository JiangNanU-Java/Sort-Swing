package com.sort.sort_method;
/**
 * 选择排序：从小到大
 * 时间复杂度：N^2/2
 * 数据移动：N次交换（最少）
 * 运行时间：与输入无关
 */
public class Select_Sort extends Example {
    public static void sort(int[] array){
        int N=array.length;
        //外循环：进行N次，每次把最小的元素放在前端
        for (int i=0;i<N;i++){
            int min=i;
            //内循环：每次选取最小的一个元素
            for (int j=i+1;j<N;j++)
                if (less(array[j],array[min]))min=j;
            swap(array,i,min);
        }
    }
}

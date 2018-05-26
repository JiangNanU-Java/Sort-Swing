package com.sort.sort_method;

/**
 * 插入排序：从小到大
 * 时间复杂度：N^2
 * 数据移动：平均需要各N^2/4次比较和交换
 * 运行时间：与输入有关，初始输入越有序越快
 * 适用部分有序的数组以及小规模数组
 */
public class Insert_Sort extends Example {
    public static void sort(int[] array){
        int N=array.length;
        //外循环：索引，从左到右移动，保证索引左侧的数组是有序的
        for (int i=1;i<N;i++){
            //内循环：将当前索引右侧的第一个元素，通过不断的与左侧元素比较，向前冒泡到合适位置
            for (int j=i;j>0;j--)
                if(less(array[j],array[j-1]))
                    swap(array,j,j-1);
        }
    }
}

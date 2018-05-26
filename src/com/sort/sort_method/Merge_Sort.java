package com.sort.sort_method;

/**
 * 合并排序：从小到大
 * 时间复杂度：NlgN
 * 数据移动：1/2*NlgN至NlgN次比较
 * 运行时间：与输入无关
 * 适用小数组
 */
public class Merge_Sort extends Example {
    private static int[] aux;

    public static void sort(int[] a) {
        //一次性分配临时数组
        aux = new int[a.length];
        Sort(a, 0, a.length - 1);
        Sort(a);
    }

    /**
     * 自底向上的归并排序
     * @param a
     */
    private static void Sort(int[] a) {
        int N=a.length;
        aux=new int[N];
        for (int size=1;size<N;size+=size)
            for (int low=0;low<N-size;low+=size)
                merge(a,low,low+size-1,Math.min(low+size+size-1,N-1));
    }

    /**
     * 自顶向下的归并排序
     * @param a
     * @param left
     * @param right
     */
    private static void Sort(int[] a, int left, int right) {
        if(left>=right)
            return;

        int mid = (left + right) / 2;
        //二路归并排序里面有两个Sort，多路归并排序里面写多个Sort就可以了
        Sort(a, left, mid);
        Sort(a, mid + 1, right);
        merge(a, left, mid, right);
    }

    //合并算法
    private static void merge(int[] a, int left, int mid, int right) {
        //从left位置开始复制（不能从0开始）
        for (int k=left;k<=right;k++)
            aux[k]=a[k];
        //定义左侧索引i，右侧索引j
        int i=left;
        int j = mid + 1;
        //通过归并：将临时数组的值回到原数组中（按顺序复制）
        for (int k=left;k<=right;k++){
            if (i>mid) a[k]= aux[j++];
            else if (j>right) a[k]= aux[i++];
            else if (a[j]<a[i]) a[k]= aux[j++];
            else a[k]= aux[i++];
        }
    }
}

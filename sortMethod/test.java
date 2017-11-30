package com.Sort.sortMethod;

public class test {

    public static void merge(int[]a,int low,int mid,int high){//对两组已经排序的数组进行合并
        int[]b=new int[high-low+1]; //临时数组，存储个数为high - low + 1个数据
        int s=low;
        int t=mid+1;
        int k=0;
        while(s<=mid&&t<=high){   //直至前半部或后半部数据完全录入暂存
            if(a[s]<=a[t])        //如果前半部的数据小于后半部的，前半部数据暂存
                b[k++]=a[s++];
            else                   //否则后半部数据暂存，并下标自加
                b[k++]=a[t++];
        }
        while(s<=mid)
            b[k++]=a[s++];
        while(t<=high)
            b[k++]=a[t++];
        for(int i=0;i<b.length;i++){     //将暂存的数据重新填充至array[low]--array[high]中
            a[low+i]=b[i];
        }
    }
    public static void mergesort(int a[],int low,int high){//对数组进行递归排序
        int mid;
        if(low<high){
            mid=(low+high)/2;
            mergesort(a,low,mid);
            mergesort(a,mid+1,high);
            merge(a,low,mid,high);
        }
    }
    public int[] go(int[] array){
        System.out.println("排序前数组为：");
        for(int i=0;i<array.length;i++){
            System.out.print(array[i]+" "); }
        mergesort(array,0,array.length-1);
        System.out.println("\n排序后数组为：");
        for(int i=0;i<array.length;i++){
            System.out.print(array[i]+" ");}
        return array;
    }

}
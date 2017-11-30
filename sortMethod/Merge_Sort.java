package com.Sort.sortMethod;

public class Merge_Sort extends Example {
    //
    private static void mergeSort(int[] a) {
        int tmpArray[]= new int[a.length-1];
        mergeSort(a,tmpArray,0,a.length-1);
    }

    private static void mergeSort(int[] a,int[] tmpArray, int low, int high){
        if (low<high){
            int mid=(low+high)/2;
            mergeSort(a,tmpArray,low,mid);
            mergeSort(a,tmpArray,mid+1,high);
            merge(a,tmpArray,low,mid+1,high);
        }
    }

    private static void merge(int[] a,int[] tmpArray,int leftPos,int rightPos,int rightEnd) {
        int leftEnd=rightPos-1;
        int tmpPos=leftPos;
        int numElements=rightEnd-leftPos+1;

        while (leftPos<=leftEnd&&rightPos<=rightEnd){
            if (a[leftPos]<=a[rightPos])
                tmpArray[tmpPos++]=a[leftPos];
            else
                tmpArray[tmpPos++]=a[rightPos];
        }
        while (leftPos<=leftEnd)
            tmpArray[tmpPos++]=a[leftPos];
        while (rightPos<=rightEnd)
            tmpArray[tmpPos++]=a[rightPos];

        for (int i=0;i<numElements;i++,rightEnd--)
            a[rightEnd]=tmpArray[rightEnd];
    }

    public static void sort(int[] array){
        mergeSort(array);
    }
}

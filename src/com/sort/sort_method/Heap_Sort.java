package com.sort.sort_method;

/**
 * 堆排序：最大堆
 * 下标从0开始
 * 对i节点，左儿子为2*i+1，右儿子为2*i+2
 */
public class Heap_Sort extends Example {
    private static int size;

    public static void sort(int[] array) {
        heapSort(array);
    }

    /**
     * 堆排序
     * @param array 数组（0开头）
     */
    public static void heapSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        //构建成最大堆
        buildMaxHeap(array);
        //将根与末尾元素交换，然后对n-1的堆继续循环
        for (int i = array.length - 1; i >= 1; i--) {
            swap(array, 0, i);
            maxHeap(array, i, 0);
        }
    }

    /**
     * 构建最大堆
     * @param array 堆数组
     */
    private static void buildMaxHeap(int[] array) {
        int half = array.length / 2;
        for (int i = half; i >= 0; i--) {
            maxHeap(array, array.length, i);
        }
    }

    /**
     * 将当前索引为根的堆构建为最大堆
     * @param array 堆数组（0开始）
     * @param heapSize 堆大小
     * @param index 当前索引
     */
    private static void maxHeap(int[] array, int heapSize, int index) {
        //左儿子2*i+1，右儿子2*i+2
        int left = index * 2 + 1;
        int right = index * 2 + 2;

        //获取父节点
        int largest = index;
        //若左儿子存在且左儿子大于父节点
        if (left < heapSize && array[left] > array[index]) {
            largest = left;
        }
        //若右儿子存在且右儿子大于左儿子
        if (right < heapSize && array[right] > array[largest]) {
            largest = right;
        }
        //交换父节点和更大的儿子节点
        if (index != largest) {
            swap(array, index, largest);
            //向下递归排序
            maxHeap(array, heapSize, largest);
        }
    }

/**
 * C程序堆排序
 */
    /**
     * 堆排序：不需要额外空间，就地排序
     * 1、构建堆
     * 2、将根节点（最小元素）与末尾n元素交换：则A[n]=A[1]为最小元素
     * 3、此时A[1]=A[n]，进行下移操作，构建堆，n=n-1
     * 4、循环2、3
     * @param A 需要排序的数组
     * @param n 数组大小
     */
    private static void heap_sort(int[] A,int n){
        make_heap(A,n);
        for (int i = n; i > 1; i--) {
            swap(A,i,1);
            sift_down(A,i-1,1);
        }
    }

    /**
     * 建造堆的第一种算法：使用insert逐个将元素插入堆中
     * @param A 需要构建堆的数组
     * @param H 堆数组
     * @param n 数组大小
     */
    private static void make_heap(int A[],int[] H,int n){
        int m=0;
        for (int i = 0; i < n; i++) {
            insert(H,m,A[i]);
        }
    }

    /**
     * ☆建造堆的第二种算法：把数组本身构造成一个堆
     * 每个叶子节点都可以看成一个堆（最后一个叶子节点的父节点是最后一个非叶子节点）
     * 从i=n/2开始，i--，遍历每个非叶子节点，因为他们的子节点是堆，所以进行下移操作
     */
    private static void make_heap(int[] A,int n){
        A[n]=A[0];
        size=1;
        for (int i = n/2; i >= 1; i--) {
            int size= heap_size(A,n,i);
            sift_down(A,size,i);
        }
    }

    /**
     * 元素上移操作
     * @param H 数组
     * @param i 被上移的元素下标
     */
    private static void sift_up(int[] H,int i){
        //若为根节点，无需上移，结束
        if (i==1)return;
        //当不为根节点时
        while (i!=1) {
            if (H[i] < H[i / 2]) swap(H, i, i / 2);
            else break;
        }
    }

    /**
     * 元素下移操作
     * @param H 数组
     * @param n 数组的元素个数
     * @param i 被下移的元素下标
     */
    private static void sift_down(int[] H,int n,int i){
        //当当前节点存在左儿子时
        if (i*2<n){
            //循环：只要存在左儿子，就将索引指向左儿子
            while ((i=2*i)<n){
                //若存在右兄弟，通过比较，使索引指向更小的一个兄弟
                if (i+1<n&&H[i]>H[i+1]){
                    i=i+1;
                }
                //若其比父节点小，那么交换，并继续循环
                if (H[i/2]>H[i])
                    swap(H,i/2,i);
                //若不满足条件，不能再移动，那么停止程序
                else
                    break;
            }
        }
    }

    /**
     * 元素插入操作：把堆的大小增1，把x插入到末尾，然后进行上移操作
     * @param H 数组
     * @param n 数组插入前大小
     * @param x 插入的数据
     */
    private static void insert(int[] H,int n,int x){
        n=n+1;
        H[n]=x;
        sift_up(H,n);
    }

    /**
     * 元素删除操作：用堆中最后一个元素取代H[i]，然后根据被删除元素和取代它的元素的大小关系，进行上移或者下移
     * @param H 数组
     * @param n 数组删除前大小
     * @param i 被删除元素的下标
     */
    private static void delete(int[] H,int n,int i){
        H[i]=H[n];
        n=n-1;
        if (H[i]<H[n])
            sift_down(H,n,i);
        else
            sift_up(H,i);
    }

    /**
     * 删除关键字最小的元素（根节点）
     * @param H 数组
     * @param n 删除前的大小
     * @return 被删除的元素值
     */
    private static int delete_min(int[] H,int n){
        int x=H[1];
        delete(H,n,1);
        return x;
    }

    /**
     * 建造堆的第二种方法：对未知n的堆数组，获取堆数组中，以i为根的堆结构的大小n
     * @param H 数组
     * @param n 堆数组H的大小
     * @param i 当前元素的下标
     * @return 以i为根的堆结构的大小size
     */
    private static int heap_size(int[] H, int n, int i){
        //若左儿子存在，自顶向下：递归遍历左子树
        if ((i=i*2)<=n){
            size++;
            heap_size(H,n,i);
        }
        //遍历完成，自底向上：递归遍历右子树
        if (i+1<=n){
            size++;
            heap_size(H,n,i+1);
        }
        return size;
    }
}

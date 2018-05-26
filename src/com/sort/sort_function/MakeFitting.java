package com.sort.sort_function;

import com.sort.mapping_frame.MappingFrame;
import com.sort.mapping_frame.MyCanvas;
import com.sort.sort_file.SortResult;

/**
 * 最小二乘法求拟合曲线
 */
public class MakeFitting {
    //提供拟合参数X，Y的数组
    private static double[] xx=new double[]{0,5,10,15,20,25,30,35,40,45,50,55,60,65,70,75,80,85,90,95,100};
    private static double[] yy;

    //拟合后的方程参数数组
    private static double[][] paraNumber;

    //获取排序数据的总数组
    private static int[][] sortArray;

    //存放将要拟合的数组的缓存数组
    private static int[] tempArray;

    /**
     * 拟合y=a0+a1*x+a2*x^2+……+apoly_n*x^poly_n
     * @param n 数据个数
     * @param x x数据值
     * @param y y数据值
     * @param poly_n 多项式项数
     * @param p 保存拟合参数数组
     * @return 返回a0,a1,a2,……a[poly_n]，系数比项数多一（常数项）
     */
    public static void polyfit(int n, double x[], double y[], int poly_n, double p[])
    {
        int i,j;
        double[] tempx=new double[n];
        double[] sumxx=new double[(poly_n*2+1)];
        double[] tempy=new double[n];
        double[] sumxy=new double[(poly_n+1)];
        double[] ata=new double[(poly_n+1)*(poly_n+1)];

        for (i=0;i<n;i++)
        {
            tempx[i]=1;
            tempy[i]=y[i];
        }
        for (i=0;i<2*poly_n+1;i++)
        {
            for (sumxx[i]=0,j=0;j<n;j++)
            {
                sumxx[i]+=tempx[j];
                tempx[j]*=x[j];
            }
        }

        for (i=0;i<poly_n+1;i++)
        {
            for (sumxy[i]=0,j=0;j<n;j++)
            {
                sumxy[i]+=tempy[j];
                tempy[j]*=x[j];
            }
        }

        for (i=0;i<poly_n+1;i++)
        {
            for (j=0;j<poly_n+1;j++)
            {
                ata[i*(poly_n+1)+j]=sumxx[i+j];
            }
        }
        gauss_solve(poly_n+1,ata,p,sumxy);
    }

    /*============================================================*/
////    高斯消元法计算得到   n 次多项式的系数
////    n: 系数的个数
////    ata: 线性矩阵
////    sumxy: 线性方程组的Y值
////    p: 返回拟合的结果
/*============================================================*/
    public static void gauss_solve(int n, double A[], double x[], double b[])
    {
        int i,j,k,r;
        double max;
        for (k=0;k<n-1;k++)
        {
            max=fabs(A[k*n+k]);                 // find maxmum
            r=k;
            for (i=k+1;i<n-1;i++)
            {
                if (max<fabs(A[i*n+i]))
                {
                    max=fabs(A[i*n+i]);
                    r=i;
                }
            }
            if (r!=k)
            {
                for (i=0;i<n;i++)        //change array:A[k]&A[r]
                {
                    max=A[k*n+i];
                    A[k*n+i]=A[r*n+i];
                    A[r*n+i]=max;
                }

                max=b[k];                    //change array:b[k]&b[r]
                b[k]=b[r];
                b[r]=max;
            }

            for (i=k+1;i<n;i++)
            {
                for (j=k+1;j<n;j++)
                    A[i*n+j]-=A[i*n+k]*A[k*n+j]/A[k*n+k];
                b[i]-=A[i*n+k]*b[k]/A[k*n+k];
            }
        }

        for (i=n-1;i>=0;x[i]/=A[i*n+i],i--)
        {
            for (j=i+1,x[i]=b[i];j<n;j++)
                x[i]-=A[i*n+j]*x[j];
        }

    }

    private static double fabs(double v) {
        if (v<0) return -v;
        else return v;
    }

   //程序入口
    public static void make() {
        int sizenum;
        int dimension = 5;  //5次多项式拟合
        paraNumber=new double[8][];

        //
        sortArray=MyCanvas.getSortArray();

        //获取八个数组的拟合曲线
        for (int j=0;j<8;j++) {
            //保存每次的拟合结果
            double[] Paras=new double[6];

            //保存准备拟合的数据
            yy=new double[21];

            //若对应的数组不为空，保存到缓存数组中
            if (sortArray[j] != null) {
                tempArray = new int[sortArray[j].length];
                tempArray = sortArray[j];
            } else
                continue;

            //  将要拟合的数据转换为double类型，并保存
            for (int i = 0; i < yy.length; i++) {
                yy[i] = (double) tempArray[i];
            }

            //  拟合数据的维数
            sizenum = xx.length;

            //进行拟合，并将结果保存在Paras数组中
            polyfit(sizenum, xx, yy, dimension, Paras);

            System.out.println("拟合系数, 按升序排列如下:");
            for (int i=0;i<dimension+1;i++)              //升序排列
            {
                System.out.println("para["+i+"]"+Paras[i]);
            }

            paraNumber[j]=Paras;
        }
    }

    //获取8个排序的所有的拟合参数数组
    public static double[][] getParaNumber(){
        return paraNumber;
    }
}

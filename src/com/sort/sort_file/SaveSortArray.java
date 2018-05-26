package com.sort.sort_file;

import com.sort.sort_frame.SelectComponent;
import com.sort.sort_frame.TextComponent;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * "生成"按钮触发，将排序好的结果存储起来，如果已存在那么更新
 */
public class SaveSortArray extends SortResult {
    //输出流
    private BufferedWriter bufferedWriter;

    //数组缓存
    private int[] timeArray;

    //构造函数
    public SaveSortArray() throws IOException {
        textComponent=new TextComponent();

        //获取复选框中选择的项
        filenames = SelectComponent.getCheckBoxSelection();

        //遍历每一个复选框对象
        for (String filename : filenames) {
            //若选择项不为null,则写入文件
            if (filename != null) {
                //生成文件
                file = new File(DEFAULT_DIRECTORY + filename + ".txt");

                //若文件已存在，选择是否覆盖
                if (file.exists()){
                    try {
                        int choose=JOptionPane.showConfirmDialog(null,"是否覆盖原文件","请选择",JOptionPane.YES_NO_OPTION);
                        //是 0
                        if (choose==0){
                            file.delete();
                            textComponent.setResultText("覆盖：原文件已删除");
                        }
                        //否 1
                        else if (choose==1){
                            textComponent.setResultText("选择：保留原文件");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        textComponent.setResultText("操作失败");
                    }
                }

                //若文件不存在，创建try
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (Exception e) {
                        System.out.println("创建失败");
                    }
                }

                //获取数据源,参数为排序类型 String类型
                String s = this.getDataSource(filename);

                //打印显示出来
                this.setResultText();

                //写入文件
                bufferedWriter = new BufferedWriter(new FileWriter(file));
                bufferedWriter.write(s);
                bufferedWriter.close();
            }
        }
    }

    //获取数据源
    private String getDataSource(String sortType){
        //获取对应排序类型的数组
        timeArray=getSortTypeArray(sortType);

        //构建字符串
        stringBuilder = new StringBuilder();
        stringBuilder.append(String.join("|","数组大小","所用时间"));
        stringBuilder.append("\r\n");

        //length每次递增5万，时间与之对应
        for(int i = 0,length=0; i <timeArray.length; i++,length+=50000){
            stringBuilder.append(length).append("|").append(timeArray[i]);
            stringBuilder.append("\r\n");
        }

        stringBuilder.deleteCharAt(stringBuilder.length() -1);
        return stringBuilder.toString();
    }

    //将数据源在result上显示出来
    private void setResultText(){
        for (int i=0,length=0;i<timeArray.length;i++,length+=50000){
            textComponent.setResultText(length+"|"+timeArray[i]+"ms");
        }
    }
}

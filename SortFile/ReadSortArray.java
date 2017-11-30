package com.Sort.SortFile;

import com.Sort.SelectComponent;
import com.Sort.TextComponent;
import sun.dc.pr.PRError;

import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * "读取"按钮触发，从本地txt文件中读入选择项的文件，获取已经保存的时间数组
 */
public class ReadSortArray extends SortFile{
    //输入流
    private BufferedReader bufferedReader;

    //字符串缓存
    private String arrayString;

    //结果数组缓存
    private int[] sortResult;

    //构造函数
    public ReadSortArray() throws IOException {
        //获取复选框数据
        filenames = SelectComponent.getCheckBoxSelection();

        //对每个选择项进行操作
        for (String filename : filenames) {
            //若选择项不为null,则读取文件
            if (filename != null) {
                //生成文件
                file = new File(DEFAULT_DIRECTORY + filename + ".txt");

                //若文件不存在，读取默认文件
                if (!file.exists()) {
                    try {
                        file = new File(DEFAULT_PATH);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                //将读取到的便于阅读的字符串存储到arratString中
                arrayString=new String();
                arrayString=this.getContent(file);

                //在sortText中显示
                textComponent.setSortText(arrayString);

                //获取对应的数组

                //用提取到的数据修改对应的排序时间数组
                //setSortTypeArray(filename,sortResult);
            }
        }

    }

    //获取文件的内容，生成一个字符串
    private String getContent(File file) throws IOException {
        //初始化输入流
        bufferedReader=new BufferedReader(new FileReader(this.file));
        stringBuilder=new StringBuilder();

        String content=" ";
        //若输入流不为空，则继续读取下一行
        while (content!=null){
            content=bufferedReader.readLine();

            //若读到null，则读取结束
            if (content==null)break;

            //将读取的字符串加到StringBuilder中
            stringBuilder.append(content.trim());
            stringBuilder.append("\r\n");
        }

        //关闭输入流
        bufferedReader.close();

        //将读取的文件显示在result中
        textComponent=new TextComponent();
        textComponent.setSortText(stringBuilder.toString());

        //返回读取到的字符串
        return stringBuilder.toString();
    }
}

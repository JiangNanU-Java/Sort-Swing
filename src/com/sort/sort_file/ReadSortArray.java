package com.sort.sort_file;

import com.sort.sort_frame.SelectComponent;
import com.sort.sort_frame.TextComponent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * "读取"按钮触发，从本地txt文件中读入选择项的文件，获取已经保存的时间数组
 */
public class ReadSortArray extends SortResult {
    //输入流
    private BufferedReader bufferedReader;

    //字符串缓存
    private String arrayString;

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

                //若文件不存在
                if (!file.exists()) {
                    textComponent.setSortText("此文件不存在");
                    continue;
                }

                //当文件存在，那么读取字符串
                else {
                    //将读取到的便于阅读的字符串存储到arratString中
                    arrayString = new String();

                    //获取结果字符串，并显示
                    arrayString = this.getContent(file);

                    //获取对应的数组
                    String[] textString = arrayString.split("\r\n");
                    int[] textInt = new int[textString.length];

                    for (int i = 1; i < textString.length; i++) {
                        //快速：保留ms
                        if (filename=="希尔排序"||filename=="堆排序"||filename=="快速排序"||filename=="基数排序") {
                            textInt[i] = Integer.parseInt(textString[i]);
                        }
                        //慢速：转换成s
                        else {
                            textInt[i] = (Integer.parseInt(textString[i]))/1000;
                        }
                    }

                    //用提取到的数据修改对应的排序时间数组
                    setSortTypeArray(filename,textInt);
                }
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

            //正则表达式分割字符串
            String[] contentsplit=content.split("\\|");

            //将读取的时间值加到StringBuilder中
            stringBuilder.append(contentsplit[1].trim());
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

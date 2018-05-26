package com.sort.sort_frame;

import javax.swing.*;
import java.awt.*;

/**
 * 进度条设置
 */
public class ProgressComponent {
    private static JProgressBar progressBar;

    //初始化
    public ProgressComponent(){
    }

    //获取进度条设置
    public static JProgressBar getProgressBar(){
        progressBar =new JProgressBar();
        progressBar.setMinimum(0);
        progressBar.setMaximum(1000);

        //计算某项操作的百分比，并显示出来
        progressBar.setStringPainted(true);
        progressBar.setBackground(Color.GREEN);
        return progressBar;
    }

    //更新进度条——线程安全
    public void setProgress(int progress) {
        if (progress >= 0 && progress <= 1000) {
            SwingUtilities.invokeLater(()->{
                progressBar.setValue(progress);
            });
        }
    }
}

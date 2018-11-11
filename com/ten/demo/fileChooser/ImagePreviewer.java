package com.ten.demo.fileChooser;

import javax.swing.*;
import java.awt.*;
import java.io.File;

//预览附件，右侧图片预览模块
public class ImagePreviewer extends JLabel {
    public ImagePreviewer(JFileChooser chooser) {
        setPreferredSize(new Dimension(100, 100));//设置显示区域尺寸
        setBorder(BorderFactory.createEtchedBorder());//边框

        chooser.addPropertyChangeListener(e -> {//changeListener 当点击发生变化时，执行
            //当点击的图片名称==图片的名称时
            if (e.getPropertyName() == JFileChooser.SELECTED_FILE_CHANGED_PROPERTY) {
                File f = (File) e.getNewValue();
                if (f == null) {
                    setIcon(null);
                    return;
                }

                ImageIcon icon = new ImageIcon(f.getPath());
                //若图片尺寸过大，则缩小
                if (icon.getIconWidth() > getWidth()) {
                    icon = new ImageIcon(icon.getImage().getScaledInstance(getWidth(), -1, Image.SCALE_DEFAULT));//尺寸重置
                }
                setIcon(icon);
            }
        });
    }
}

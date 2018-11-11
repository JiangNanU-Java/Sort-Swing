package com.ten.demo.fileChooser;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileView;
import java.io.File;

//文件选择类
public class FileIconView extends FileView {
    private FileFilter filter;
    private Icon icon;

    //设置过滤器和文件的图标
    public FileIconView(FileFilter aFilter, Icon anIcon) {
        filter = aFilter;
        icon = anIcon;
    }

    //当点击的文件1、不是目录2、满足过滤器的accept方法时，返回图片预览图
    public Icon getIcon(File f) {
        if (!f.isDirectory() && filter.accept(f)) return icon;
        else return null;
    }
}

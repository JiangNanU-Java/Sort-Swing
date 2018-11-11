package com.ten.sort.dialogFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

//工具条
public class TooBarFrame {
    //JToolBar工具条
    public TooBarFrame() {
        JToolBar toolBar = new JToolBar();
        Action colorAction = new ToolAction("颜色", Color.RED, "更换背景颜色");
        Action functionAction = new ToolAction("功能", Color.BLUE, "功能按钮");
    }

    //工具条对应的内部类
    class ToolAction extends AbstractAction {
        //初始化构造函数
        public ToolAction(String name, Color color, String function) {
            //putValue方法赋值键值对
            putValue(Action.NAME, name);
            putValue(Action.SHORT_DESCRIPTION, name + "按钮");
            putValue("color", color);
            putValue("function", function);
        }

        //事件响应
        @Override
        public void actionPerformed(ActionEvent e) {
            Color c = (Color) getValue("color");
            String fun = (String) getValue("function");
            System.out.println("颜色" + c + " 功能" + fun);
        }
    }
}

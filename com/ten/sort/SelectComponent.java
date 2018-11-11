package com.ten.sort;

import javax.swing.*;

/**
 * 获取选择部件，并添加响应事件
 */
public class SelectComponent {
    //排序方法
    private static String[] sortType = {"选择排序", "冒泡排序", "快速排序", "希尔排序", "插入排序", "堆排序", "基数排序", "合并排序"};
    //下拉组合框
    private static JComboBox comboBox;
    //复选框面板
    private static JCheckBox[] checkBoxes;
    private static JPanel checkBoxPanel;

    //获取JComboBox组合框
    public static JComboBox getComboBox() {
        comboBox = new JComboBox();
        comboBox.setEditable(true);

        //为组合框添加Item
        for (int i = 0; i < sortType.length; i++) {
            comboBox.addItem(sortType[i]);
        }
        return comboBox;
    }

    //获取JCheckBox复选框
    public static JPanel getCheckBox() {
        checkBoxes = new JCheckBox[sortType.length];
        checkBoxPanel = new JPanel();

        //为复选框添加Item
        for (int i = 0; i < sortType.length; i++) {
            checkBoxes[i] = new JCheckBox(sortType[i]);
            checkBoxPanel.add(checkBoxes[i]);
        }
        return checkBoxPanel;
    }

    //获取comboBox选择-单选
    public static String getComboBoxSelection() {
        String select = new String();

        //获取单选框选择的Item
        return select = (String) comboBox.getSelectedItem();
    }

    //获取checkBox选择-多选
    public static String[] getCheckBoxSelection() {
        String[] selects = new String[sortType.length];

        //获取复选框选择情况
        for (int i = 0; i < sortType.length; i++) {
            if (checkBoxes[i].isSelected()) selects[i] = sortType[i];
            else selects[i] = null;
        }
        return selects;
    }

    //获取排序数组名称
    public static String[] getSortType() {
        return sortType;
    }
}

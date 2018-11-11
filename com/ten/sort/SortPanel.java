package com.ten.sort;

import javax.swing.*;
import java.awt.*;

/**
 * Panel
 *
 * @author wshten
 * @date 2018/11/11
 * @see SortFrame
 */
class SortPanel extends JPanel {
    /**
     * 一个主程序对应一个面板内容，所以用静态域
     */
    private static JPanel jpanelMenu;
    private static JPanel jpanelSort;
    private static JPanel jpanelResult;
    private static JPanel jpanelCheckBox;

    private static JLabel jlableSort;
    private static JLabel jlabelResult;

    private static JScrollPane jtextSort;
    private static JScrollPane jtextResult;

    private static JComboBox jcomboSelect;

    private static JButton jbuttonSort;
    private static JButton jbuttonInput;
    private static JButton jbuttonOutput;
    private static JButton jbuttonMapping;

    private static JProgressBar jprogressSort;

    public SortPanel() {
        // 按钮初始化
        jbuttonSort = ButtonComponent.getSortButton();
        jbuttonMapping = ButtonComponent.getMappingButton();
        jbuttonInput = ButtonComponent.getInputButton();
        jbuttonOutput = ButtonComponent.getOutputButton();

        // 选择项初始化
        jcomboSelect = SelectComponent.getComboBox();
        jpanelCheckBox = SelectComponent.getCheckBox();

        // 标签初始化
        jlableSort = new JLabel("排序进程：");
        jlabelResult = new JLabel("排序结果：");

        // 文本框初始化
        jtextSort = TextComponent.getSortTextPanel();
        jtextResult = TextComponent.getResultTextPanel();

        // 进度条初始化
        jprogressSort = ProgressComponent.getProgressBar();

        // 设置面板
        init_panel();
        init_frame();
    }

    //JPanel面板初始化
    private static void init_panel() {
        jpanelMenu = new JPanel();
        jpanelSort = new JPanel();
        jpanelResult = new JPanel();

        //设置面板边框
        jpanelSort.setBorder(BorderFactory.createEtchedBorder());
        jpanelResult.setBorder(BorderFactory.createEtchedBorder());
        jpanelMenu.setBorder(BorderFactory.createEtchedBorder());
    }

    //frame初始化
    private void init_frame() {
        //添加布局
        setLayout(new GridBagLayout());
        add(jpanelMenu, setConstraints(0, 0, 1, 3, 1, 1));
        add(jpanelSort, setConstraints(0, 1, 2, 3, 1, 1));
        add(jpanelResult, setConstraints(0, 3, 2, 3, 1, 1));
        //面板布局
        //菜单面板
        jpanelMenu.setLayout(new GridBagLayout());
        jpanelMenu.add(jcomboSelect, setConstraints(0, 0, 1, 1, 1, 1));
        jpanelMenu.add(jbuttonSort, setConstraints(1, 0, 1, 1, 1, 1));
        jpanelMenu.add(jbuttonMapping, setConstraints(2, 0, 1, 1, 1, 1));
        jpanelMenu.add(jpanelCheckBox, setConstraints(0, 1, 1, 3, 1, 1));
        jpanelMenu.add(jprogressSort, setConstraints(0, 2, 1, 3, 1, 1));
        //排序面板
        jpanelSort.setLayout(new GridBagLayout());
        jpanelSort.add(jlableSort, setConstraints(0, 0, 1, 2, 1, 1));
        jpanelSort.add(jbuttonInput, setConstraints(2, 0, 1, 2, 1, 1));
        jpanelSort.add(jtextSort, setConstraints(0, 2, 2, 4, 0, 2));
        //结果面板
        jpanelResult.setLayout(new GridBagLayout());
        jpanelResult.add(jlabelResult, setConstraints(0, 0, 1, 2, 1, 1));
        jpanelResult.add(jbuttonOutput, setConstraints(2, 0, 1, 2, 1, 1));
        jpanelResult.add(jtextResult, setConstraints(0, 2, 2, 4, 0, 2));
    }

    //设置布局管理器
    private static GridBagConstraints setConstraints(int gx, int gy, int gh, int gw, int wx, int wy) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = gx;
        constraints.gridy = gy;
        constraints.gridheight = gh;
        constraints.gridwidth = gw;
        constraints.weightx = wx;
        constraints.weighty = wy;
        constraints.fill = GridBagConstraints.BOTH;
        return constraints;
    }
}

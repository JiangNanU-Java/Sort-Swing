package com.Sort;

import javax.swing.*;
import java.awt.*;

/**
 * 程序内容面板
 */
public class SortPanel extends JPanel {
    //一个主程序对应一个面板内容，所以用静态域
    private static JPanel jpanel_menu;
    private static JPanel jpanel_sort;
    private static JPanel jpanel_result;
    private static JPanel jpanel_checkBox;

    private static JLabel jlable_sort;
    private static JLabel jlabel_result;

    private static JScrollPane jtext_sort;
    private static JScrollPane jtext_result;

    private static JComboBox jcombo_select;

    private static JButton jbutton_sort;
    private static JButton jbutton_input;
    private static JButton jbutton_output;
    private static JButton jbutton_mapping;

    private static JProgressBar jprogress_sort;

    //初始化
    public SortPanel(){
        //按钮初始化
        jbutton_sort=ButtonComponent.getSortButton();
        jbutton_mapping=ButtonComponent.getMappingButton();
        jbutton_input=ButtonComponent.getInputButton();
        jbutton_output=ButtonComponent.getOutputButton();

        //选择项初始化
        jcombo_select=SelectComponent.getComboBox();
        jpanel_checkBox=SelectComponent.getCheckBox();

        //标签初始化
        jlable_sort = new JLabel("排序进程：");
        jlabel_result = new JLabel("排序结果：");

        //文本框初始化
        jtext_sort=TextComponent.getSortTextPanel();
        jtext_result=TextComponent.getResultTextPanel();

        //进度条初始化
        jprogress_sort=ProgressComponent.getProgressBar();

        //设置面板
        init_panel();
        init_frame();
    }

    //JPanel面板初始化
    private static void init_panel(){
        jpanel_menu= new JPanel();
        jpanel_sort = new JPanel();
        jpanel_result=new JPanel();

        //设置面板边框
        jpanel_sort.setBorder(BorderFactory.createEtchedBorder());
        jpanel_result.setBorder(BorderFactory.createEtchedBorder());
        jpanel_menu.setBorder(BorderFactory.createEtchedBorder());
    }

    //frame初始化
    private void init_frame(){
        //添加布局
        setLayout(new GridBagLayout());
        add(jpanel_menu,setConstraints(0,0,1,3,1,1));
        add(jpanel_sort,setConstraints(0,1,2,3,1,1));
        add(jpanel_result,setConstraints(0,3,2,3,1,1));
        //面板布局
        //菜单面板
        jpanel_menu.setLayout(new GridBagLayout());
        jpanel_menu.add(jcombo_select,setConstraints(0,0,1,1,1,1));
        jpanel_menu.add(jbutton_sort,setConstraints(1,0,1,1,1,1));
        jpanel_menu.add(jbutton_mapping,setConstraints(2,0,1,1,1,1));
        jpanel_menu.add(jpanel_checkBox,setConstraints(0,1,1,3,1,1));
        jpanel_menu.add(jprogress_sort,setConstraints(0,2,1,3,1,1));
        //排序面板
        jpanel_sort.setLayout(new GridBagLayout());
        jpanel_sort.add(jlable_sort,setConstraints(0,0,1,2,1,1));
        jpanel_sort.add(jbutton_input,setConstraints(2,0,1,2,1,1));
        jpanel_sort.add(jtext_sort,setConstraints(0,2,2,4,0,2));
        //结果面板
        jpanel_result.setLayout(new GridBagLayout());
        jpanel_result.add(jlabel_result,setConstraints(0,0,1,2,1,1));
        jpanel_result.add(jbutton_output,setConstraints(2,0,1,2,1,1));
        jpanel_result.add(jtext_result,setConstraints(0,2,2,4,0,2));
    }

    //设置布局管理器
    private static GridBagConstraints setConstraints(int gx,int gy,int gh,int gw,int wx,int wy){
        GridBagConstraints constraints=new GridBagConstraints();
        constraints.gridx=gx;
        constraints.gridy=gy;
        constraints.gridheight=gh;
        constraints.gridwidth=gw;
        constraints.weightx=wx;
        constraints.weighty=wy;
        constraints.fill=GridBagConstraints.BOTH;
        return constraints;
    }
}

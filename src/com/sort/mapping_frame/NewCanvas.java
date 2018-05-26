package com.sort.mapping_frame;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class NewCanvas {
    private MyCanvas newCanva;

    //通过响应的状态码创建新的绘图面板
    private MappingFrame newMapping;
    public NewCanvas(int status){
        newCanva=new MyCanvas();
        newCanva.setStatus(status);
        newMapping=new MappingFrame(newCanva);
        newMapping.repaint();
    }

    //右侧按钮对应的绘图面板
    public NewCanvas(int status,int buttonNumber) {
        newCanva = new MyCanvas();
        switch (buttonNumber) {
            case 0:newCanva.setStatus(40);break;
            case 1:newCanva.setStatus(41);break;
            case 2:newCanva.setStatus(42);break;
            case 3:newCanva.setStatus(43);break;
            case 4:newCanva.setStatus(44);break;
            case 5:newCanva.setStatus(45);break;
            case 6:newCanva.setStatus(46);break;
            case 7:newCanva.setStatus(47);break;
            default:break;
        }
        newMapping=new MappingFrame(newCanva);
        newMapping.repaint();
    }
}

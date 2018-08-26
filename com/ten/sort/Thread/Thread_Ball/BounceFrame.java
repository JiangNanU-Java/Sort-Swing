package com.ten.sort.Thread.Thread_Ball;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BounceFrame extends JFrame {
    private BallComponent component;
    public static final int STEPS=1000;
    public static final int DELAY=5;

    public BounceFrame(){
        component=new BallComponent();
        add(component, BorderLayout.CENTER);
        JPanel buttonPanel=new JPanel();
        addButton(buttonPanel,"Start",e -> addBall());
        addButton(buttonPanel,"End",e -> System.exit(0));
        add(buttonPanel,BorderLayout.SOUTH);
        pack();
    }
    public void addButton(Container c, String title, ActionListener listener){
        JButton button=new JButton(title);
        c.add(button);
        button.addActionListener(listener);
    }
    private void addBall() {
        Ball ball=new Ball();
        component.add(ball);
        Runnable r=()->{
          try{
              for (int i=1;;i++){
                  ball.move(component.getBounds());
                  component.repaint();
                  Thread.sleep(DELAY);
              }
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
        };
        Thread t=new Thread(r);
        t.start();
    }
}

package com.ten.demo.chart;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 * 血压变化趋势图，包括坐标轴，高压、低压基准线，动态高压、低压线，分度值等
 *
 * @author Freedoman
 * @Time 2014-1-1
 */
public class BloodPresure extends JFrame {

    private static final long serialVersionUID = 1L;
    private Image iBuffer;
    private MyCanvas trendChartCanvas = new MyCanvas();
    private JTextField highPressText, lowPressText;

    // 框架起点坐标、宽高
    private final int FRAME_X = 100;
    private final int FRAME_Y = 100;
    private final int FRAME_WIDTH = 700;
    private final int FRAME_HEIGHT = 250;

    // 原点坐标
    private final int Origin_X = FRAME_X + 40;
    private final int Origin_Y = FRAME_Y + FRAME_HEIGHT - 30;

    // X轴、Y轴终点坐标
    private final int XAxis_X = FRAME_X + FRAME_WIDTH - 30;
    private final int XAxis_Y = Origin_Y;
    private final int YAxis_X = Origin_X;
    private final int YAxis_Y = FRAME_Y + 30;

    // X轴上的时间分度值(1分度=2天=40像素)
    private final int TIME_INTERVAL = 40;

    // y轴上血压分度值
    private final int PRESS_INTERVAL = 10;

    // 保存当前测量高压和低压值数组,数组长度计数器
    private int[] CurrentHighPressInput = {150, 150, 150, 150, 150, 150, 150,
            150, 150, 150, 150, 150, 150, 150, 150, 150};
    private int[] CurrentLowPressInput = {75, 75, 75, 75, 75, 75, 75, 75, 75,
            75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75};
    private int CurrentPressInputLength = 1;

    public BloodPresure() {

        super("自定义血压趋势坐标图-FreeDoman");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(300, 100, 900, 600);

        // 添加控制到框架北部区
        JPanel topPanel = new JPanel();
        this.add(topPanel, BorderLayout.NORTH);

        // 高压值与文本框
        highPressText = new JTextField(5);
        topPanel.add(new JLabel("高压值", JLabel.CENTER));
        topPanel.add(highPressText);

        // 低压值与文本框
        lowPressText = new JTextField(5);
        topPanel.add(new JLabel("低压值", JLabel.CENTER));
        topPanel.add(lowPressText);

        // 打点按钮与事件
        JButton pressButton = new JButton("打点");
        pressButton.addActionListener(new ActionListener() {

            // 把当前输入的高压和低压值
            @Override
            public void actionPerformed(ActionEvent arg0) {

                // 绘制当前输入的高压趋势
                int InputHighPress = Integer.parseInt(highPressText.getText());
                int InputLowPress = Integer.parseInt(lowPressText.getText());
                CurrentHighPressInput[CurrentPressInputLength] = InputHighPress;
                CurrentLowPressInput[CurrentPressInputLength] = InputLowPress;
                CurrentPressInputLength++;
                trendChartCanvas.repaint();
                // 输入文本框清零
                highPressText.setText("");
                lowPressText.setText("");
            }
        });

        topPanel.add(pressButton);

        // 添加画布到中央区
        this.add(trendChartCanvas, BorderLayout.CENTER);
        this.setResizable(false);
        this.setVisible(true);
    }

    /**
     * 画布重绘血压趋势图
     */
    class MyCanvas extends Canvas {

        private static final long serialVersionUID = 1L;

        public void paint(Graphics g) {
            Graphics2D g2D = (Graphics2D) g;

            // 画边框
            g.setColor(Color.BLACK);
            g.draw3DRect(FRAME_X, FRAME_Y, FRAME_WIDTH, FRAME_HEIGHT, true);

            // 画坐标轴
            g.setColor(Color.BLACK);
            g2D.setStroke(new BasicStroke(Float.parseFloat("2.0f")));
            // X轴及方向箭头
            g.drawLine(Origin_X, Origin_Y, XAxis_X, XAxis_Y);
            g.drawLine(XAxis_X, XAxis_Y, XAxis_X - 5, XAxis_Y - 5);
            g.drawLine(XAxis_X, XAxis_Y, XAxis_X - 5, XAxis_Y + 5);
            // Y轴及方向箭头
            g.drawLine(Origin_X, Origin_Y, YAxis_X, YAxis_Y);
            g.drawLine(YAxis_X, YAxis_Y, YAxis_X - 5, YAxis_Y + 5);
            g.drawLine(YAxis_X, YAxis_Y, YAxis_X + 5, YAxis_Y + 5);

            // 画X轴上时间刻度(从坐标原点起，每隔TIME_INTERVAL(时间分度)像素画一时间点，到X轴终点至)
            g.setColor(Color.BLUE);
            g2D.setStroke(new BasicStroke(Float.parseFloat("1.0f")));
            for (int i = Origin_X, j = 0; i < XAxis_X; i += TIME_INTERVAL, j += 2) {
                g.drawString("1-" + j, i - 10, Origin_Y + 20);
            }
            g.drawString("日期", XAxis_X + 5, XAxis_Y + 5);

            // 画Y轴上血压刻度(从坐标原点起，每隔10像素画一压力值，到Y轴终点至)
            for (int i = Origin_Y, j = 0; i > YAxis_Y; i -= PRESS_INTERVAL, j += 10) {
                g.drawString(j + "", Origin_X - 30, i + 3);
            }
            g.drawString("血压/mmgh", YAxis_X - 5, YAxis_Y - 5);

            // 画网格线
            g.setColor(Color.BLACK);
            // 横线
            for (int i = Origin_Y - PRESS_INTERVAL; i > YAxis_Y; i -= PRESS_INTERVAL) {
                g.drawLine(Origin_X, i, Origin_X + 15 * TIME_INTERVAL, i);
            }
            // 竖线
            for (int i = Origin_X + TIME_INTERVAL; i < XAxis_X; i += TIME_INTERVAL) {
                g.drawLine(i, Origin_Y, i, Origin_Y - 18 * PRESS_INTERVAL);

            }

            // 理想血压基准线
            // 高压
            g.setColor(Color.MAGENTA);
            g2D.setStroke(new BasicStroke(Float.parseFloat("2.0f")));
            g.drawLine(Origin_X, Origin_Y - 150, Origin_X + 15 * TIME_INTERVAL,
                    Origin_Y - 150);
            g2D.setStroke(new BasicStroke(Float.parseFloat("1.0f")));
            g.drawString("理想高压线", Origin_X + 15 * TIME_INTERVAL + 3,
                    Origin_Y - 150 + 3);
            // 低压
            g.setColor(Color.CYAN);
            g2D.setStroke(new BasicStroke(Float.parseFloat("2.0f")));
            g.drawLine(Origin_X, Origin_Y - 75, Origin_X + 15 * TIME_INTERVAL,
                    Origin_Y - 75);
            g2D.setStroke(new BasicStroke(Float.parseFloat("1.0f")));
            g.drawString("理想低压线", Origin_X + 15 * TIME_INTERVAL + 3,
                    Origin_Y - 75 + 3);

            // 当前测量高压线,循环画出高压数组CurrentHighPressInput[]中保存的线段
            g.setColor(Color.ORANGE);
            g2D.setStroke(new BasicStroke(Float.parseFloat("3.0f")));

            // 当前高压起点横坐标为CurrentHighPressStart_X，纵坐标为数组第一个，当前终点横坐标为CurrentHighPressStart_X
            // + TIMA_INTERVAL，纵坐标为数组第二个，依次类推画出各线段
            for (int i = 1, CurrentHighPressStart_X = Origin_X; i < CurrentPressInputLength; i++, CurrentHighPressStart_X += TIME_INTERVAL) {
                g.drawLine(CurrentHighPressStart_X, Origin_Y
                        - CurrentHighPressInput[i - 1], CurrentHighPressStart_X
                        + TIME_INTERVAL, Origin_Y - CurrentHighPressInput[i]);
            }

            // 当前测量低压线,循环画出低压数组CurrentLowPressInput[]中保存的线段
            g.setColor(Color.PINK);
            g2D.setStroke(new BasicStroke(Float.parseFloat("3.0f")));
            // 当前低压起点横坐标为CurrentHighPressStart_X，纵坐标为数组第一个，当前终点横坐标为CurrentLowPressStart_X
            // + TIMA_INTERVAL，纵坐标为数组第二个，依次类推画出各线段
            for (int i = 1, CurrentLowPressStart_X = Origin_X; i < CurrentPressInputLength; i++, CurrentLowPressStart_X += TIME_INTERVAL) {
                g.drawLine(CurrentLowPressStart_X, Origin_Y
                        - CurrentLowPressInput[i - 1], CurrentLowPressStart_X
                        + TIME_INTERVAL, Origin_Y - CurrentLowPressInput[i]);
            }
        }

        // 双缓冲技术解决图像显示问题
        public void update(Graphics g) {
            if (iBuffer == null) {
                iBuffer = createImage(this.getSize().width,
                        this.getSize().height);

            }
            Graphics gBuffer = iBuffer.getGraphics();
            gBuffer.setColor(getBackground());
            gBuffer.fillRect(0, 0, this.getSize().width, this.getSize().height);
            paint(gBuffer);
            gBuffer.dispose();
            g.drawImage(iBuffer, 0, 0, this);
        }
    }

    public static void main(String[] args) {

        // 设置界面的外观，为系统外观
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        new BloodPresure();
    }
}
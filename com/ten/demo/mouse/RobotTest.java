package com.ten.demo.mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;

public class RobotTest {
    public static void main(String[] args) throws AWTException {

        EventQueue.invokeLater(() -> {
            ButtonFrame frame = new ButtonFrame();
            frame.setLocation(0, 0);
            frame.setSize(400, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });

        GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice graphicsDevice = environment.getDefaultScreenDevice();

        Robot robot = new Robot(graphicsDevice);
        int i = 0;
        while (i++ < 100) {
            robot.keyPress(' ');
            robot.keyRelease(' ');

            robot.mouseMove(100 + i, 100 + i);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);

            Rectangle rectangle = new Rectangle(i, i, 200 + i, 200 + i);
            BufferedImage image = robot.createScreenCapture(rectangle);
            ImageFrame frame = new ImageFrame(image);
            robot.delay(100);
        }

    }

    static class ButtonFrame extends JFrame {
        public ButtonFrame() {
            add(new JButton("hello11"));
            add(new JButton("hello22"));
            add(new JButton("hello33"));
            add(new JButton("hello444"));

            this.setLayout(new FlowLayout());
        }
    }

    static class ImageFrame extends JFrame {
        private static final int DEFAULT_WIDTH = 250;
        private static final int DEFAULT_HEIGHT = 250;

        public ImageFrame(Image image) {
            setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
            setTitle("Image");
            JLabel label = new JLabel(new ImageIcon(image));
            add(label);
        }
    }

}
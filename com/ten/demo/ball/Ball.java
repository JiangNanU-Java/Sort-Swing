package com.ten.demo.ball;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * Ball Object
 */
class Ball {
    /**
     * Ball Size
     */
    private static final int XSIZE = 15;
    private static final int YSIZE = 15;
    /**
     * Ball Location
     */
    private double x = 0;
    private double y = 0;
    /**
     * Ball Moving Distance
     */
    private double dx = 1;
    private double dy = 1;

    /**
     * Move Method, If ball touch the edge, it will be rebound
     */
    void move(Rectangle2D bounds) {
        // 位置d距离
        x += dx;
        y += dy;
        // 碰触左边缘
        if (x < bounds.getMinX()) {
            x = bounds.getMinX();
            dx = -dx;
        }
        // 右边缘
        if (x + XSIZE >= bounds.getMaxX()) {
            x = bounds.getMaxX() - XSIZE;
            dx = -dx;
        }
        // 上边缘
        if (y < bounds.getMinY()) {
            y = bounds.getMinY();
            dy = -dy;
        }
        // 下边缘
        if (y + YSIZE >= bounds.getMaxY()) {
            y = bounds.getMaxY() - YSIZE;
            dy = -dy;
        }
    }

    /**
     * @return Ball Region
     */
    Ellipse2D getShape() {
        return new Ellipse2D.Double(x, y, XSIZE, YSIZE);
    }
}

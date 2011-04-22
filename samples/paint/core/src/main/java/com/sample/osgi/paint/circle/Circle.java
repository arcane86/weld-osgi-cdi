package com.sample.osgi.paint.circle;

import com.sample.osgi.paint.api.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Circle implements Shape {

    private Icon icon;

    public Circle() {
        icon = new ImageIcon(getClass().getResource("circle.png"));
    }

    @Override
    public String getName() {
        return "Circle";
    }

    @Override
    public Icon getIcon() {
        return icon;
    }

    @Override
    public void draw(Graphics2D g2, Point p) {
        int x = p.x - 25;
        int y = p.y - 25;
        GradientPaint gradient = new GradientPaint(x, y, Color.RED, x + 50, y, Color.WHITE);
        g2.setPaint(gradient);
        g2.fill(new Ellipse2D.Double(x, y, 50, 50));
        BasicStroke wideStroke = new BasicStroke(2.0f);
        g2.setColor(Color.black);
        g2.setStroke(wideStroke);
        g2.draw(new Ellipse2D.Double(x, y, 50, 50));
    }
}

package pro1.swingComponents;

import pro1.drawingModel.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {
    private DisplayPanel displayPanel;
    private List<Point> points = new ArrayList<>();
    private int radius = 20;
    private boolean linesVisible = true;

    public MainFrame() {
        this.setTitle("PRO1 Drawing - Zápočet");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.displayPanel = new DisplayPanel();
        this.add(this.displayPanel, BorderLayout.CENTER);

        JPanel leftPanel = new OptionsPanel(this);
        this.add(leftPanel, BorderLayout.WEST);

        this.displayPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                points.add(e.getPoint());
                refreshDrawing();
            }
        });

        this.setVisible(true);
    }

    public void refreshDrawing() {
        this.displayPanel.setDrawable(generateScene());
    }

    private Drawable generateScene() {
        List<Drawable> items = new ArrayList<>();

        if (linesVisible && points.size() > 1) {
            for (int i = 0; i < points.size(); i++) {
                for (int j = i + 1; j < points.size(); j++) {
                    Point p1 = points.get(i);
                    Point p2 = points.get(j);
                    items.add(new Line(p1.x, p1.y, p2.x, p2.y, 1, "#AAAAAA"));
                }
            }
        }

        for (Point p : points) {
            items.add(new Ellipse(p.x - radius, p.y - radius, radius * 2, radius * 2, "#FF0000"));
        }

        return new Group(items.toArray(new Drawable[0]), 0, 0, 0, 1, 1);
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
    public void setLinesVisible(boolean visible) {
        this.linesVisible = visible;
    }
    public void reset() {
        this.points.clear();
        refreshDrawing();
    }
}
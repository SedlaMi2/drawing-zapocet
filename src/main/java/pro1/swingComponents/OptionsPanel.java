package pro1.swingComponents;

import javax.swing.*;
import java.awt.*;

public class OptionsPanel extends JPanel {
    public OptionsPanel(MainFrame parent) {
        this.setPreferredSize(new Dimension(200, 0));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.GRAY));

        this.add(new JLabel("Poloměr koleček:"));
        JSlider radiusSlider = new JSlider(5, 100, 20);
        this.add(radiusSlider);
        radiusSlider.addChangeListener(e -> {
            parent.setRadius(radiusSlider.getValue());
            parent.refreshDrawing();
        });

        JCheckBox hideLines = new JCheckBox("Zobrazit úsečky", true);
        this.add(hideLines);
        hideLines.addActionListener(e -> {
            parent.setLinesVisible(hideLines.isSelected());
            parent.refreshDrawing();
        });

        JButton resetBtn = new JButton("Resetovat");
        this.add(resetBtn);
        resetBtn.addActionListener(e -> parent.reset());
    }
}
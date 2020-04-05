package com.chernokoz;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StartFrame extends JFrame {

    private JButton button = new JButton("Run reading!");
    private JTextField filePath = new JTextField("Enter file path...", 5);
    private JTextField pythonPath = new JTextField("Enter python interpreter path...", 5);
    private JCheckBox isNeededToReadHeader = new JCheckBox("Read first row as header", false);
    private CSVReader csvReader;

    public StartFrame() {
        super("Welcome to csvReader v1.0");
        this.setBounds(239, 239, 500, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(4, 1 , 3, 3));
        container.add(filePath);

        container.add(pythonPath);

        container.add(isNeededToReadHeader);
        button.addActionListener(new ButtonEventListener());
        container.add(button);

        csvReader = new CSVReader();
    }

    private class ButtonEventListener implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            csvReader.solve(filePath.getText(), pythonPath.getText(), isNeededToReadHeader.isSelected());
            csvReader.setVisible(true);
        }
    }
}

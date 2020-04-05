package com.chernokoz;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class DataTable extends JFrame
{
    public DataTable(Object [][] data, Object [] columnsNames) {
        super("csvReader v1.0 by @chernokoz");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTable tableRow = new JTable(data, columnsNames);

        JTableHeader header = tableRow.getTableHeader();
        header.setBackground(Color.darkGray);
        header.setForeground(Color.orange);

        tableRow.setShowGrid(true);
        tableRow.setGridColor(Color.LIGHT_GRAY);

        tableRow.setBackground(Color.DARK_GRAY);
        tableRow.setForeground(Color.LIGHT_GRAY);

        tableRow.setSelectionBackground(Color.LIGHT_GRAY);
        tableRow.setSelectionForeground(Color.GRAY);

        tableRow.setFont(new Font("Arial", Font.BOLD, 14));

        tableRow.setAutoCreateRowSorter(true);
        tableRow.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        Box contents = new Box(BoxLayout.Y_AXIS);
        contents.setBackground(Color.black);
        contents.setForeground(Color.black);
        contents.add(new JScrollPane(tableRow));
        setContentPane(contents);

        setSize(1024, 768);
        setVisible(true);

    }
}
package com.chernokoz;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class CSVReader extends JFrame {

    Object [] columnsNames;
    Object [][] data;
    Process process;

    public CSVReader(){}

    public void solve(String filePath, String pythonPath, boolean isNeededToReadHeader) {
        try {

            String prg = "import sys\n" +
                    "import pandas as pd\n" +
                    "data = pd.read_csv(\"" +
                    filePath +
                    "\", delimiter=\",\"" +
                    (isNeededToReadHeader ? "" : ", header=None") +
                    ")\n" +
                    "rowsCount = data.shape[0]\n" +
                    "columnsCount = data.shape[1]\n" +
                    "print(rowsCount)\n" +
                    "print(columnsCount)\n" +
                    "columnsNames = dict()\n" +
                    "for i in range(columnsCount):\n" +
                    "    columnsNames.setdefault(i, data.columns[i])\n" +
                    "    print(data.columns[i])\n" +
                    "for i in range(rowsCount):\n" +
                    "    for j in range(columnsCount):\n" +
                    "        print(data[columnsNames[j]][i])\n";

            BufferedWriter out = new BufferedWriter(new FileWriter("pythonCode.py"));
            out.write(prg);
            out.close();

            try {
                ProcessBuilder pb = new ProcessBuilder("python3", "pythonCode.py");
                process = pb.start();
            } catch (Exception e) {
                ProcessBuilder pb = new ProcessBuilder(pythonPath, "pythonCode.py");
                process = pb.start();
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));

            int rowsCount = Integer.parseInt(in.readLine());
            int columnsCount = Integer.parseInt(in.readLine());

            String tmp;

            columnsNames = new String[columnsCount];

            for (int column = 0; column < columnsCount; column++) {
                tmp = in.readLine();
                columnsNames[column] = tmp;
            }

            data = new String[rowsCount][columnsCount];

            for (int row = 0; row < rowsCount; row++) {
                for (int column = 0; column < columnsCount; column++) {
                    tmp = in.readLine();
                    data[row][column] = tmp;

                }
            }

        } catch(Exception e) {System.out.println(e);}

        new DataTable(data, columnsNames);
    }
}

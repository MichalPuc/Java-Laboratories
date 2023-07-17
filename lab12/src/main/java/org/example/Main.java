package org.example;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {

    public static void main(String[] args) {
        openFileChooser();
    }

    private static void openFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(null);

        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            loadScript(selectedFile.getAbsolutePath());
        }
    }

    private static void loadScript(String scriptFile) {
        ScriptEngine engine = loadScriptEngine(scriptFile);
        if (engine != null) {
            Invocable invocableEngine = (Invocable) engine;

            int[][] board = new int[100][100];
            int rows = 20;
            int cols = 20;

            invokeFunction(invocableEngine, "initialize", board, rows, cols);

            GameOfLifePanel gameOfLifePanel = new GameOfLifePanel(board, rows, cols);

            JFrame frame = new JFrame("Game of Life");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(gameOfLifePanel);
            frame.pack();
            frame.setVisible(true);

            for (int i = 0; i < 100; i++) {
                invokeFunction(invocableEngine, "update", board, rows, cols);
                gameOfLifePanel.repaint();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            unloadScriptEngine(engine);
        } else {
            System.out.println("ScriptEngine is null");
        }
    }

    private static ScriptEngine loadScriptEngine(String scriptFile) {
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("nashorn");
            if (engine == null) {
                System.out.println("Nashorn ScriptEngine is not available.");
                return null;
            }

            engine.eval(new FileReader(scriptFile));

            return engine;
        } catch (ScriptException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static void invokeFunction(Invocable engine, String functionName, Object... args) {
        try {
            engine.invokeFunction(functionName, args);
        } catch (ScriptException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private static void unloadScriptEngine(ScriptEngine engine) {
        engine = null;
        System.gc();
    }

    static class GameOfLifePanel extends JPanel {

        private int[][] board;
        private int rows;
        private int cols;
        private int cellSize = 20;

        public GameOfLifePanel(int[][] board, int rows, int cols) {
            this.board = board;
            this.rows = rows;
            this.cols = cols;
            setPreferredSize(new Dimension(cols * cellSize, rows * cellSize));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (board[i][j] == 1) {
                        g.setColor(Color.BLACK);
                    } else {
                        g.setColor(Color.WHITE);
                    }

                    g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                    g.setColor(Color.GRAY);
                    g.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);
                }
            }
        }
    }
}

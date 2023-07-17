package Interface;

import javax.swing.*;

public class GUI extends JFrame{
    private JPanel panel;
    private JButton checkButton;
    private JButton chooseButton;
    private JButton generateButton;
    private JTextField directoryField;
    private JTextPane naPoczątkuMusimyWybraćTextPane;
    private JTextPane wybranyFolderTextPane;
    private JTextPane generujemySumyKontrolneDoTextPane;
    private JTextPane porównujemyDoWygenerowanychSumTextPane;

    public GUI()
    {
        super("Aplikacja");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public JButton getCheckButton() {
        return checkButton;
    }

    public JButton getChooseButton() {
        return chooseButton;
    }

    public JButton getGenerateButton() {
        return generateButton;
    }

    public JTextField getDirectoryField() {
        return directoryField;
    }
}

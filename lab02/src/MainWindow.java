import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.WeakHashMap;
public class MainWindow extends JFrame {

    private JPanel fileChooserPanel;
    private JPanel fileContentPanel;
    private JButton chooseFileButton;
    private JLabel selectedFileLabel;
    private JTextArea fileContentTextArea;
    private JFileChooser fileChooser;
    private DataProcessor dataProcessor;
    public MainWindow() {
        super("Przeglądarka danych pomiarowych");
        DataProcessor dataProcessor = new DataProcessor();
        // Ustawienia ramki
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));

        // Panel wyboru pliku
        fileChooserPanel = new JPanel(new FlowLayout());
        chooseFileButton = new JButton("Wybierz plik");
        chooseFileButton.addActionListener(e -> {
            if (fileChooser == null) {
                fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Pliki CSV", "csv"));
                fileChooser.setMultiSelectionEnabled(false);
            }
            int result = fileChooser.showOpenDialog(MainWindow.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                selectedFileLabel.setText(selectedFile.getName());
                try {
                    String filePath = selectedFile.getPath();
                    dataProcessor.processData(filePath);
                    List<DataPoint> fileContent = dataProcessor.getData(filePath);
                    fileContentTextArea.setText("");

                    // Dodajemy nagłówki kolumn
                    fileContentTextArea.append(String.format("%-20s%-20s%-25s%s%n", "Ciśnienie [hPa]", "Temperatura [℃]", "Wilgotność [%]", "Czas"));
                    fileContentTextArea.append(String.format("%-25s%-25s%-25s%s%n", "---------------------", "---------------------", "---------------------", "---------------------"));

                    // Wyświetlamy dane w oddzielnych kolumnach
                    for (DataPoint dataPoint : fileContent) {
                        String time = dataPoint.getTime();
                        double pressure = dataPoint.getPressure();
                        double temperature = dataPoint.getTemperature();
                        double humidity = dataPoint.getHumidity();
                        fileContentTextArea.append(String.format("%-30.2f%-30.2f%-30.2f%s%n", pressure, temperature, humidity, time));
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(MainWindow.this, "Wystąpił błąd podczas odczytu pliku", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        selectedFileLabel = new JLabel("Wybierz plik");
        fileChooserPanel.add(chooseFileButton);
        fileChooserPanel.add(selectedFileLabel);
        add(fileChooserPanel, BorderLayout.NORTH);

        // Panel zawartości pliku
        fileContentPanel = new JPanel(new BorderLayout());
        fileContentTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(fileContentTextArea);
        fileContentPanel.add(scrollPane, BorderLayout.CENTER);
        add(fileContentPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

package gui;

import classLoader.Class_Loader;
import processor.MyStatusListener;
import processor.Processor;
import processor.Status;
import processor.StatusListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GUI implements StatusListener {
    private JPanel jpanel;
    private JComboBox comboBox1;
    private JButton addClassButton;
    private JButton choosenClassMakesTaskButton;
    private JTextField task;
    private JTextArea textArea1;
    private JButton deleteClassButton;
    private JList taskList;
    private JLabel statusLabel;

    private DefaultListModel<String> listModel;

    // Funkcja przykładowa
    public String someFunction(String path, String task)
    {
        // Zwracamy tekst składający się z wybranej ścieżki oraz tekstu z pola task
        return "Wybrana ścieżka: " + path + "\nZadanie: " + task;
    }
    public GUI()
    {
        Class_Loader loader = new Class_Loader();
        JFrame frame = new JFrame("GUI");
        frame.setContentPane(jpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        addClassButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(jpanel);

            if (option == JFileChooser.APPROVE_OPTION) {
                String path = fileChooser.getSelectedFile().getPath();
                comboBox1.addItem(path);
                try {
                    loader.loadClassData(path);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        deleteClassButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedPath = (String) comboBox1.getSelectedItem();
                if (selectedPath != null) {
                    comboBox1.removeItem(selectedPath);
                    loader.removeClass(selectedPath);
                }
            }
        });

        choosenClassMakesTaskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedPath = (String) comboBox1.getSelectedItem();
                String taskString = task.getText();

                // Załadowanie klasy, która implementuje interfejs Processor

                Class<?> loadedClass = loader.getLoadedClass("processor."+loader.getClassnameFromPath(selectedPath));
                if (loadedClass == null) {
                    throw new RuntimeException("Nie znaleziono klasy: " + "processor."+loader.getClassnameFromPath(selectedPath));
                }

                try {
                    // Utworzenie instancji klasy i wykonanie zadania
                    Processor processor = (Processor) loadedClass.getDeclaredConstructor().newInstance();
                    MyStatusListener statusListener = new MyStatusListener();
                    processor.submitTask(taskString,statusListener);
                    String result = processor.getResult(); // pass "this" as the StatusListener

                    // Wyświetlenie wyniku w textArea1
                    textArea1.setText(result);
                } catch (Exception ex) {
                    throw new RuntimeException("Błąd podczas wykonywania zadania: " + ex.getMessage());
                }
            }
        });
    }

    @Override
    public void statusChanged(Status s) {
        // Sprawdzenie, czy zadanie o danym taskId znajduje się na liście
        int index = listModel.indexOf("Task " + s.getTaskId());
        if (index != -1) {
            // Aktualizacja postępu zadania na liście
            String taskString = "Task " + s.getTaskId() + " - " + s.getProgress() + "%";
            listModel.set(index, taskString);

            // Aktualizacja statusu w labelu
            statusLabel.setText("Task " + s.getTaskId() + ": " + s.getProgress() + "%");
        }
    }
}


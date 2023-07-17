import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.crypto.SecretKey;

public class FileEncryptionApp {
    private JFrame frame;
    private JButton encryptButton;
    private JButton decryptButton;

    private JButton outputFileButton;
    private JButton privateKeyFileButton;
    private JButton publicKeyFileButton;
    private JFileChooser fileChooser;
    private JTextField outputFileField;
    private JComboBox<String> algorithmComboBox;
    private JTextField privateKeyFileField;
    private JTextField publicKeyFileField;

    public FileEncryptionApp() {
        frame = new JFrame("File Encryption App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Inicjalizacja komponentów GUI
        encryptButton = new JButton("Encrypt");
        decryptButton = new JButton("Decrypt");
        outputFileButton = new JButton("Browse");
        privateKeyFileButton = new JButton("Browse");
        publicKeyFileButton = new JButton("Browse");
        outputFileField = new JTextField(20);
        algorithmComboBox = new JComboBox<>();
        privateKeyFileField = new JTextField(20);
        publicKeyFileField = new JTextField(20);
        fileChooser = new JFileChooser();

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        constraints.gridy = 1;
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("Output File: "), constraints);

        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.LINE_START;
        panel.add(outputFileField, constraints);

        constraints.gridx = 2;
        constraints.anchor = GridBagConstraints.LINE_START;
        panel.add(outputFileButton, constraints);

        constraints.gridy = 2;
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("Algorithm: "), constraints);

        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.LINE_START;
        panel.add(algorithmComboBox, constraints);

        constraints.gridy = 3;
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("Private Key File: "), constraints);

        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.LINE_START;
        panel.add(privateKeyFileField, constraints);

        constraints.gridx = 2;
        constraints.anchor = GridBagConstraints.LINE_START;
        panel.add(privateKeyFileButton, constraints);

        constraints.gridy = 4;
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("Public Key File: "), constraints);

        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.LINE_START;
        panel.add(publicKeyFileField, constraints);

        constraints.gridx = 2;
        constraints.anchor = GridBagConstraints.LINE_START;
        panel.add(publicKeyFileButton, constraints);

        constraints.gridy = 5;
        constraints.gridx = 0;
        constraints.gridwidth = 3;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(encryptButton, constraints);

        constraints.gridy = 6;
        panel.add(decryptButton, constraints);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);


        outputFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chooseOutputFile();
            }
        });

        privateKeyFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                choosePrivateKeyFile();
            }
        });

        publicKeyFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                choosePublicKeyFile();
            }
        });

        encryptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                encryptFile();
            }
        });

        decryptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                decryptFile();
            }
        });
    }


    private void chooseOutputFile() {
        int returnValue = fileChooser.showSaveDialog(frame);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            outputFileField.setText(selectedFile.getAbsolutePath());
        }
    }

    private void choosePrivateKeyFile() {
        int returnValue = fileChooser.showOpenDialog(frame);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            privateKeyFileField.setText(selectedFile.getAbsolutePath());
        }
    }

    private void choosePublicKeyFile() {
        int returnValue = fileChooser.showOpenDialog(frame);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            publicKeyFileField.setText(selectedFile.getAbsolutePath());
        }
    }

    private void encryptFile() {
        int returnValue = fileChooser.showOpenDialog(frame);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File inputFile = fileChooser.getSelectedFile();
            String outputPath = outputFileField.getText();
            //String algorithm = algorithmComboBox.getSelectedItem().toString();
            String privateKeyFilePath = privateKeyFileField.getText();

            try {
                File outputFile = new File(outputPath);

                // Wczytanie klucza prywatnego
                SecretKey privateKey = KeyManager.SecretKey(privateKeyFilePath);

                // Wywołanie metody szyfrowania z biblioteki
                FileEncryptor.encryptFile(inputFile, outputFile, privateKey);

                JOptionPane.showMessageDialog(frame, "Encryption successful!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, "Error encrypting file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println(e.getMessage());
            }
        }
    }

    private void decryptFile() {
        int returnValue = fileChooser.showOpenDialog(frame);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File inputFile = fileChooser.getSelectedFile();
            String outputPath = outputFileField.getText();
            //String algorithm = algorithmComboBox.getSelectedItem().toString();
            String publicKeyFilePath = privateKeyFileField.getText();

            try {

                File outputFile = new File(outputPath);

                // Wczytanie klucza prywatnego
                SecretKey publicKey = KeyManager.SecretKey(publicKeyFilePath);

                // Wywołanie metody deszyfrowania z biblioteki
                FileEncryptor.decryptFile(inputFile, outputFile, publicKey);

                JOptionPane.showMessageDialog(frame, "Decryption successful!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, "Error decrypting file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FileEncryptionApp();
            }
        });
    }
}

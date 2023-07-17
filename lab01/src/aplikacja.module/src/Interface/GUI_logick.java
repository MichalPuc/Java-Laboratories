package Interface;


import biblioteka.Encoder;
import Interface.GUI;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class GUI_logick {
    Encoder encoder = new Encoder();
    GUI gui = new GUI();
    public void init() {
        gui.getCheckButton().addActionListener(e-> {
            try {
                checkChanges();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                noSuchAlgorithmException.printStackTrace();
            }
        });
        gui.getChooseButton().addActionListener(e->chooseFile());
        gui.getGenerateButton().addActionListener(e-> {
            try {
                generateHash();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                noSuchAlgorithmException.printStackTrace();
            }
        });
    }

    private void generateHash() throws IOException, NoSuchAlgorithmException {
        encoder.createHash(gui.getDirectoryField().getText());

    }

    private void chooseFile() {
        gui.getDirectoryField().setText(encoder.chooseFile());
    }

    private void checkChanges() throws IOException, NoSuchAlgorithmException {
        encoder.checkHash(gui.getDirectoryField().getText());
    }

}
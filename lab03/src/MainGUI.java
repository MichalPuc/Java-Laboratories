import org.json.JSONException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

public class MainGUI {
    private JPanel jpanel;
    private JTextArea pytanieTextArea;
    private JTextArea odpowiedzTextArea;
    private JTextField odpowiedz;
    private JTextPane wyborJezykaTextPane;
    private JButton polskiButton;
    private JButton englishButton;
    private JButton sprawdzButton;
    private JTextPane informacja;
    private JButton nastepnePytanieButton;
    private JTextPane chooseLanguageTextPane;
    private JTextPane pytanie_tresc;

    private Locale locale;
    private List<String> recordingIds;
    private boolean checked=false,answer =true;
    private String file = "language",utwor="aa",wykonawca="bb",id="1",user_answer;

    private int index;

    public MainGUI() throws JSONException, IOException {
        Language language = new Language();
        MusicBrainzAPI api = new MusicBrainzAPI();
        recordingIds = api.getRandomMusic("rock");

        locale = language.getCurrentLocale();
        JFrame frame = new JFrame("MainGUI");
        frame.setContentPane(jpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        // ustawienie listenerów dla przycisków
        polskiButton.addActionListener(e -> {
            language.setCurrentLocale(language.getPolishLocale());
            file = "language";
            updateLanguageComponents();
        });

        englishButton.addActionListener(e -> {
            language.setCurrentLocale(language.getEnglishLocale());
            file = "language_en";
            updateLanguageComponents();
        });

        sprawdzButton.addActionListener(e -> {
            checked = true;
            try {
                wykonawca=api.getAuthor(recordingIds.get(index));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (JSONException ex) {
                throw new RuntimeException(ex);
            }
            ResourceBundle bundle = ResourceBundle.getBundle(file,locale);
            user_answer=odpowiedz.getText();
            user_answer=user_answer.replaceAll("[^a-zA-Z]+", "");
            user_answer=user_answer.replaceAll("\\s", "");
            String wykonawca2=wykonawca.replaceAll("[^a-zA-Z]+", "");
            wykonawca2=wykonawca.replaceAll("\\s", "");
            answer=user_answer.equalsIgnoreCase(wykonawca2);
            if(answer)
            {
                informacja.setText(bundle.getString("Dobrze"));
            }
            else informacja.setText(bundle.getString("Zle")+"\n"+wykonawca);
        });

        nastepnePytanieButton.addActionListener(e -> {
            checked=false;
            Random rand = new Random();
            index = rand.nextInt(recordingIds.size());
            try {
                utwor=api.getMusicTitle(recordingIds.get(index));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (JSONException ex) {
                throw new RuntimeException(ex);
            }
            ResourceBundle bundle = ResourceBundle.getBundle(file,locale);
            pytanie_tresc.setText(bundle.getString("Pytanie")+"\n"+utwor);
            odpowiedz.setText("");
            informacja.setText("");
            pytanie_tresc.setVisible(true);
        });
    }
    private void updateLanguageComponents() {

        ResourceBundle bundle = ResourceBundle.getBundle(file,locale);
        // zmiana zawartości dla pytanieTextArea
        pytanieTextArea.setText(bundle.getString("pytanie"));
        pytanie_tresc.setText(bundle.getString("Pytanie")+"\n"+utwor);
        // zmiana zawartości dla odpowiedzTextArea
        odpowiedzTextArea.setText(bundle.getString("odpowiedz"));

        // zmiana zawartości dla sprawdzButton
        String sprawdzButtonText = bundle.getString("sprawdz");
        sprawdzButton.setText(sprawdzButtonText);

        // zmiana zawartości dla nastepnePytanieButton
        String nastepnePytanieButtonText = bundle.getString("nastepne_pytanie");
        nastepnePytanieButton.setText(nastepnePytanieButtonText);
        if(checked) {
            if (answer) {
                informacja.setText(bundle.getString("Dobrze"));
            } else informacja.setText(bundle.getString("Zle") + "\n" + wykonawca);
        }
    }
}

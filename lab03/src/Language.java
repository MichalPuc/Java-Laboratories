import java.util.Locale;

public class Language {
    private Locale currentLocale;
    private final Locale POLISH_LOCALE = new Locale("pl", "PL");
    private final Locale ENGLISH_LOCALE = new Locale("en", "US");

    public Language() {
        currentLocale = POLISH_LOCALE;
    }

    public Locale getPolishLocale() {
        return POLISH_LOCALE;
    }

    public Locale getEnglishLocale() {
        return ENGLISH_LOCALE;
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }

    public void setCurrentLocale(Locale locale) {
        currentLocale = locale;
    }
}
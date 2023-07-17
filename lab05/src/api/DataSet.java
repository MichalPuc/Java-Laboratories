package ex.api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Klasa reprezentująca zbiór danych w postaci tabelarycznej.
 * Przechowuje nagłówek (jednowymiarowa tablica z nazwami kolumn)
 * oraz dane (dwuwymiarowa tablica, której wiersze reprezentują wektory danych).
 * Zakładamy, że będą zawsze istnieć przynajmniej dwie kolumny o nazwach:
 * "RecordId" - w kolumnie tej przechowywane są identyfikatory rekordów danych;
 * "CategoryId" - w kolumnie tej przechowywane są identyfikatory kadegorii rekordów danych (wynik analizy skupień).
 *
 * @author tkubik
 *
 */
public class DataSet {
    private String[] header = {};
    private String[][] data = {{}};

    private <T> T[][] deepCopy(T[][] matrix) {
        return Arrays.stream(matrix).map(el -> el.clone()).toArray(i -> matrix.clone());
    }

    public String[] getHeader() {
        return header;
    }
    public void setHeader(String[] header) {
        this.header = header.clone();
    }
    public String[][] getData() {
        return data;
    }
    public void setData(String[][] data) {
        this.data = deepCopy(data);
    }

    public void loadFromCsv(String csvPath) throws IOException {
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(csvPath));
        List<String[]> lines = new ArrayList<>();

        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(",");
            lines.add(fields);
        }
        reader.close();

        String[][] dataArray = lines.toArray(new String[lines.size()][]);
        data = deepCopy(dataArray);
        header = data[0];
        String[][] dataWithoutHeader = Arrays.copyOfRange(data, 1, data.length);
        data = deepCopy(dataWithoutHeader);
    }
}
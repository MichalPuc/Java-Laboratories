import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

public class DataProcessor {
    private WeakHashMap<String, WeakReference<List<DataPoint>>> dataMap;
    private String loadedFilePath;
    private double pressureSum = 0.0;
    private double temperatureSum = 0.0;
    private double humiditySum = 0.0;

    public DataProcessor() {
        dataMap = new WeakHashMap<>();
    }
    public void processData(String filePath) throws IOException {
        WeakReference<List<DataPoint>> dataListReference = dataMap.get(filePath);
        System.out.println(dataMap.get(filePath));
        if (dataListReference != null && dataListReference.get() != null) {
            // If the list exists, use the existing data
            System.out.println("Using existing data for file: " + filePath);
            return;
        }
        List<DataPoint> dataList = new ArrayList<>();
        loadedFilePath = filePath;
        String fileContent = FileManager.readFile(filePath);
        String[] lines = fileContent.split("\\r?\\n");

        int numLines = 0;
        for (String line : lines) {
            try{
                String[] values = line.split(";");
                String time = values[0];
                double pressure = Double.parseDouble(values[1]);
                double temperature = Double.parseDouble(values[2]);
                double humidity = Double.parseDouble(values[3]);
                pressureSum += pressure;
                temperatureSum += temperature;
                humiditySum += humidity;
                numLines++;

                DataPoint dataPoint = new DataPoint(time, pressure, temperature, humidity);
                dataList.add(dataPoint);
            } catch (NumberFormatException e) {
                System.out.println("Nieprawidłowy format danych wejściowych: " + e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Nieprawidłowy format danych wejściowych: brak wymaganych wartości.");
            }
        }
        pressureSum /= numLines;
        temperatureSum /= numLines;
        humiditySum /= numLines;
        DataPoint dataPoint = new DataPoint("Srednie", pressureSum, temperatureSum, humiditySum);
        dataList.add(0, dataPoint);
        dataMap.put(filePath, new WeakReference<>(dataList));
    }

    public List<DataPoint> getData(String filePath) throws IOException {
        WeakReference<List<DataPoint>> dataListReference = dataMap.get(filePath);
        if (dataListReference != null && dataListReference.get() != null) {
            return dataListReference.get();
        }

        processData(filePath);
        return dataMap.get(filePath).get();
    }

}
package ex.api;

import java.util.Arrays;

public class PrecisionAnalysisService implements AnalysisService {

    private String[] options;
    private DataSet ds;
    private String name = "PrecisionAnalysisService";
    private double[][] confusionMatrix;
    private int dataLength;
    private String[][] data;

    @Override
    public void setOptions(String[] options) throws AnalysisException {
        // tu można dodać logikę ustawiania opcji algorytmu, jeśli takowe są potrzebne
        this.options = options;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void submit(DataSet ds) throws AnalysisException {
        if (this.ds != null) {
            throw new AnalysisException("Data processing is already in progress");
        }
        this.ds = ds;
        this.data = ds.getData();
        this.dataLength = data.length;
        this.confusionMatrix = calculateConfusionMatrix(data);
    }

    @Override
    public DataSet retrieve(boolean clear) throws AnalysisException {
        if (ds == null || confusionMatrix == null) {
            return null;
        }
        DataSet result = new DataSet();
        result.setHeader(new String[]{"precision"});
        result.setData(new String[][]{{String.valueOf(calculatePrecision(confusionMatrix))}});
        if (clear) {
            ds = null;
            confusionMatrix = null;
        }
        return result;
    }

    private int getNumExamples() {
        return data.length;
    }

    private double[][] calculateConfusionMatrix(String[][] data) {
        int n = data.length;
        int m = data[0].length;
        double[][] matrix = new double[m][m];
        for (int i = 1; i < n; i++) {
            int r = Integer.parseInt(data[i][0]);
            int c = Integer.parseInt(data[i][1]);
            matrix[r][c]++;
        }
        return matrix;
    }

    private double calculatePrecision(double[][] confusionMatrix) {
        int n = confusionMatrix.length;
        double precisionSum = 0;
        for (int i = 0; i < n; i++) {
            final int colIndex = i;
            double colSum = Arrays.stream(confusionMatrix).mapToDouble(row -> row[colIndex]).sum();
            double truePositive = confusionMatrix[i][i];
            double precision = truePositive / colSum;
            precisionSum += precision;
        }
        return precisionSum / n;
    }
}

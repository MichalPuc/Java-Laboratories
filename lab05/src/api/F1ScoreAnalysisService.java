package ex.api;

import java.util.Arrays;

public class F1ScoreAnalysisService implements AnalysisService {

    private String[] options;
    private DataSet ds;
    private String name = "F1ScoreAnalysisService";
    private double[][] confusionMatrix;
    private int data_length;
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
        this.data_length = data.length;
        this.confusionMatrix = calculateConfusionMatrix(data);
    }

    @Override
    public DataSet retrieve(boolean clear) throws AnalysisException {
        if (ds == null || confusionMatrix == null) {
            return null;
        }
        DataSet result = new DataSet();
        result.setHeader(new String[]{"f1_score"});
        result.setData(new String[][]{{String.valueOf(calculateF1Score(confusionMatrix))}});
        if (clear) {
            ds = null;
            confusionMatrix = null;
        }
        return result;
    }

    private double calculateF1Score(double[][] confusionMatrix) {
        int n = confusionMatrix.length;
        double[] precision = new double[n];
        double[] recall = new double[n];

        double f1ScoreSum = 0;
        for (int i = 0; i < n; i++) {
            double truePositives = confusionMatrix[i][i];
            double falsePositives = Arrays.stream(confusionMatrix[i]).sum() - truePositives;
            final int colIndex = i;
            double falseNegatives = Arrays.stream(confusionMatrix).mapToDouble(row -> row[colIndex]).sum() - truePositives;

            precision[i] = truePositives / (truePositives + falsePositives);
            recall[i] = truePositives / (truePositives + falseNegatives);

            double f1Score = 2 * precision[i] * recall[i] / (precision[i] + recall[i]);
            f1ScoreSum += f1Score;
        }

        return f1ScoreSum / n;
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
}

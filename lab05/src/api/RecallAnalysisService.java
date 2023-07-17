package ex.api;

public class RecallAnalysisService implements AnalysisService {

    private String[] options;
    private DataSet ds;
    private String name = "RecallAnalysisService";
    private double recall;

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
        String[][] data = ds.getData();
        int n = data.length;
        if (n <= 1) {
            throw new AnalysisException("Data set is too small");
        }
        int tp = 0, fn = 0;
        for (int i = 1; i < n; i++) {
            int predicted = Integer.parseInt(data[i][0]);
            int actual = Integer.parseInt(data[i][1]);
            if (predicted == actual) {
                tp++;
            } else {
                fn++;
            }
        }
        recall = (double) tp / (tp + fn);
    }

    @Override
    public DataSet retrieve(boolean clear) throws AnalysisException {
        if (ds == null) {
            return null;
        }
        DataSet result = new DataSet();
        result.setHeader(new String[]{"recall"});
        result.setData(new String[][]{{String.valueOf(recall)}});
        if (clear) {
            ds = null;
            recall = 0;
        }
        return result;
    }
}

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileManager {
    private static final String FILE_NOT_FOUND_MESSAGE = "Nie znaleziono pliku: ";

    public static String readFile(String filePath) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(filePath));
        return new String(encoded);
    }

    public static boolean fileExists(String filePath) {
        return Files.exists(Paths.get(filePath));
    }

    public static void printFileNotFoundError(String filePath) {
        System.out.println(FILE_NOT_FOUND_MESSAGE + filePath);
    }
}
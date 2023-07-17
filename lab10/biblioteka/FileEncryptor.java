import javax.crypto.*;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


public class FileEncryptor {
    private static final String ALGORITHM = "AES";

    public static void encryptFile(File inputFile, File outputFile, SecretKey privateKey) throws IOException {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);

            try (InputStream inputStream = new FileInputStream(inputFile);
                 OutputStream outputStream = new FileOutputStream(outputFile)) {
                CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, cipher);

                byte[] buffer = new byte[8192];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    cipherOutputStream.write(buffer, 0, bytesRead);
                }

                cipherOutputStream.close();
            }
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            e.printStackTrace();
            throw new IOException("Error encrypting file: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Error reading/writing file: " + e.getMessage());
        }
    }

    public static void decryptFile(File inputFile, File outputFile, SecretKey publicKey ) throws IOException {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, publicKey);

            try (InputStream inputStream = new FileInputStream(inputFile);
                 OutputStream outputStream = new FileOutputStream(outputFile)) {
                CipherInputStream cipherInputStream = new CipherInputStream(inputStream, cipher);

                byte[] buffer = new byte[8192];
                int bytesRead;
                while ((bytesRead = cipherInputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                cipherInputStream.close();
            }
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            e.printStackTrace();
            throw new IOException("Error decrypting file: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Error reading/writing file: " + e.getMessage());
        }
    }
}

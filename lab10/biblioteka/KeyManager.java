
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;


public class KeyManager {
    private static final String ALGORITHM = "AES";
    static int count = 77777;
    static int length = 256;
    static byte[] rand = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    static SecretKeySpec SecretKey(String password) throws Exception {
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), rand, count, length);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        SecretKey secretKey = factory.generateSecret(spec);
        return new SecretKeySpec(secretKey.getEncoded(), ALGORITHM);
    }

}

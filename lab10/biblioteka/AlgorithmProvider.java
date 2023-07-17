import javax.crypto.Cipher;
import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AlgorithmProvider {
    public static List<String> getAvailableAlgorithms() {
        List<String> algorithms = new ArrayList<>();
        Provider[] providers = Security.getProviders();

        for (Provider provider : providers) {
            Set<Provider.Service> services = provider.getServices();

            for (Provider.Service service : services) {
                if (service.getType().equals("Cipher")) {
                    algorithms.add(service.getAlgorithm());
                }
            }
        }

        return algorithms;
    }

    public static boolean isAlgorithmAvailable(String algorithm) {
        List<String> algorithms = getAvailableAlgorithms();
        return algorithms.contains(algorithm);
    }
}

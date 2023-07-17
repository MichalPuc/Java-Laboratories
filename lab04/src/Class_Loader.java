package classLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Class_Loader extends ClassLoader {

    private Map<String, Class<?>> loadedClasses = new HashMap<>();

    public Class<?> loadClassData(String classPath) throws IOException, ClassNotFoundException {
        // Wyodrębnienie nazwy klasy
        String className = getClassnameFromPath(classPath);

        // Sprawdzenie, czy klasa już została wcześniej załadowana
        Class<?> loadedClass = loadedClasses.get(className);
        className="processor."+className;
        if (loadedClass != null) {
            return loadedClass;
        }

        // Wczytanie pliku z klasą
        byte[] classBytes = Files.readAllBytes(Paths.get(classPath));

        // Załadowanie klasy
        Class<?> loaded = defineClass(className, classBytes, 0, classBytes.length);
        loadedClasses.put(className, loaded);
        return loaded;
    }

    public void removeClass(String classPath) {
        String className = getClassnameFromPath(classPath);

        Class<?> loadedClass = loadedClasses.get(className);
        if (loadedClass != null) {
            loadedClasses.remove("processor."+className);
        } else {
            System.out.println("Class " + className + " was not loaded");
        }

        String fullClassPath = classPath;
    }

    public String getClassnameFromPath(String classPath) {
        return Paths.get(classPath).getFileName().toString().replace(".class", "");
    }

    public Class<?> getLoadedClass(String className) {
        return loadedClasses.get(className);
    }
}

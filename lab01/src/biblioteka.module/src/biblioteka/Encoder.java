package biblioteka;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;
import javax.swing.*;
public class Encoder {
    static Scanner scan = new Scanner(System.in);
    static ArrayList<String> checkList = new ArrayList<>();
    static ArrayList<String> oldCheckList = new ArrayList<>();

    public static void createHash(String folder) throws NoSuchAlgorithmException, IOException {

        Path mainPath = Paths.get(folder);
        int countDir = mainPath.getNameCount();
        String newDirName = String.valueOf(mainPath.getName(countDir-1));
        if(Files.isDirectory(Path.of("./"+newDirName))){
        }
        else{
            Files.createDirectory(Path.of("./" + newDirName));}
        List<Path>  patches = Files.walk(Paths.get(folder))
                .filter(Files::isRegularFile)
                .collect(Collectors.toList());




        for (Path patch: patches
        ) {

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(Files.readAllBytes(Paths.get(String.valueOf(patch))));
            byte[] digest = md.digest();
            String hash = encodeMD5(digest);
            String newFileName = String.valueOf(patch.getFileName());

            Files.write(Path.of("./"+ newDirName +"/" + newFileName + ".txt"),hash.getBytes(StandardCharsets.UTF_8));
        }

    }
    private static String encodeMD5(byte[] bytes) {
        return String.format("%032x", new BigInteger(1, bytes));
    }


    public static void checkHash(String folder) throws IOException, NoSuchAlgorithmException {
        boolean changed=false;
        Path mainPath = Paths.get(folder);
        int countDir = mainPath.getNameCount();
        String newDirName = String.valueOf(mainPath.getName(countDir-1));
        List<Path>  patches = Files.walk(Paths.get(folder))
                .filter(Files::isRegularFile)
                .collect(Collectors.toList());
        if((Files.exists(Path.of("./"+newDirName)))==false){
            JFrame frame = new JFrame("Message");
            JOptionPane.showMessageDialog(frame,
                    "Ten folder nie został zapisany");
        }else {
            for (Path patch: patches
            ) {

                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(Files.readAllBytes(Paths.get(String.valueOf(patch))));
                byte[] digest = md.digest();
                String hash = encodeMD5(digest);
                String newFileName = String.valueOf(patch.getFileName());
                byte[] readBytes;
                String check = Files.readString(Path.of("./"+ newDirName +"/" + newFileName + ".txt"));

                if(check.equals(hash)==false){
                    changed=true;
                }
            }
            if(changed==true){
                JFrame frame = new JFrame("Message");
                JOptionPane.showMessageDialog(frame,
                        "Pliki w folderze są zmienione");
            }else{
                JFrame frame = new JFrame("Message");
                JOptionPane.showMessageDialog(frame,
                        "Pliki w folderze są niezmienione");
            }
        }
    }

    public static String chooseFile(){
        String result;
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("choosertitle");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

            result = String.valueOf(chooser.getSelectedFile());

        }else result = null;
        return result;
    }
}

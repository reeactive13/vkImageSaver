package com.company;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static List<String> paths = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\User\\Desktop\\dumpIra.txt";
        fileContainsWord(path);
        save();
    }

    private static void fileContainsWord(String path) throws IOException {
        byte[] content = Files.readAllBytes(Paths.get(path));
        String contentString = Files.readString(Paths.get(path));
        String text = contentString.replaceAll("\\s+", "");
        String imagePath = "";
        for (int i = 0; i <= text.length() - 10; i++) {
            if (text.charAt(i) == 'a' && text.charAt(i + 1) == 't' && text.charAt(i + 2) == 't' && text.charAt(i + 3) == '_' && text.charAt(i + 4) == 'p') {
                for (int j = i + 40; text.charAt(j) != '"'; j++) {
                    System.out.print(text.charAt(j));
                    imagePath += text.charAt(j);
                }
                paths.add(imagePath);
                imagePath = "";
                System.out.println("\n");
            }
        }
    }

    private static void save() throws IOException {

        for (int i = 0; i < paths.size(); i++) {
            URLConnection conn;
            URL url = new URL(paths.get(i));
            conn = url.openConnection();
            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
            FileOutputStream fos = new FileOutputStream(new File(String.format("C:\\Users\\User\\Desktop\\dumpPhotos\\photo%d.jpg", i)));
            int ch;
            while ((ch = bis.read()) != -1) {
                fos.write(ch);
            }
            bis.close();
            fos.flush();
            fos.close();
        }
    }
}

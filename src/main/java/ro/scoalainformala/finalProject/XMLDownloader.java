package ro.scoalainformala.finalProject;


import java.io.*;
import java.net.*;

public class XMLDownloader {

    private static final String URL_STRING = "https://www.bnr.ro/nbrfxrates.xml";
    private static final String FILE_PATH = "bnr_rates.xml";

    public static void downloadXML() throws IOException {
        URL url = new URL(URL_STRING);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try (InputStream in = connection.getInputStream();
             FileOutputStream out = new FileOutputStream(FILE_PATH)) {
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        } finally {
            connection.disconnect();
        }
    }

    public static void main(String[] args) {
        System.setProperty("javax.net.ssl.trustStore","./src/main/resources/keystore.jks");
        System.setProperty("javax.net.ssl.trustStorePassword","mypass");
        try {
            downloadXML();
            System.out.println("XML downloaded successfully.");
        } catch (IOException e) {
            System.out.println("Error downloading XML: " + e.getMessage());
        }
    }
}

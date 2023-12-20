package ro.scoalainformala.finalProject;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.util.HashMap;
import java.util.Map;
import java.io.File;

public class XMLParser {

    private static final String FILE_PATH = "bnr_rates.xml";

    public static Map<String, Double> parseXML() {
        Map<String, Double> currencyRates = new HashMap<>();
        try {
            File inputFile = new File(FILE_PATH);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("Rate");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String currency = eElement.getAttribute("currency");
                    double rate = Double.parseDouble(eElement.getTextContent());
                    currencyRates.put(currency, rate);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currencyRates;
    }

    public static void main(String[] args) {
        Map<String, Double> rates = parseXML();
        rates.forEach((key, value) -> System.out.println("RON/" + key + "\nRata de schimb: " + value));
    }
}


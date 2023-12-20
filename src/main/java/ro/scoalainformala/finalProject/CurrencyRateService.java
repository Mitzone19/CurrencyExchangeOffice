package ro.scoalainformala.finalProject;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;


@Service
public class CurrencyRateService {

    public Map<String, Double> getCurrencyRates() {
        // Call XMLDownloader to download the file
        try {
            XMLDownloader.downloadXML();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // Then parse the XML and return the rates
        return XMLParser.parseXML();
    }
}

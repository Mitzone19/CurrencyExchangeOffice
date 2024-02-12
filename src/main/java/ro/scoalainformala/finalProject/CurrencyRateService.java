package ro.scoalainformala.finalProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CurrencyRateService {

    @Autowired
    private CurrencyRateRepository currencyRateRepository;

    // This method is called after the bean creation to ensure the database has the latest currency rates
    @PostConstruct
    public void init() {
        try {
            updateCurrencyRatesFromXml();
        } catch (IOException e) {
            e.printStackTrace(); // Consider proper logging
        }
    }

    // Updates the currency rates in the database from the XML
    public void updateCurrencyRatesFromXml() throws IOException {
        // Ensuring XML is downloaded before parsing and updating
        XMLDownloader.downloadXML();
        Map<String, Double> rates = XMLParser.parseXML();
        rates.forEach((currency, rate) -> {
            CurrencyRate currencyRate = new CurrencyRate();
            currencyRate.setCurrency(currency);
            currencyRate.setRate(rate);
            currencyRateRepository.save(currencyRate);
        });
    }

    // Fetches the latest currency rates from the database
    public Map<String, Double> getCurrencyRates() {
        List<CurrencyRate> rates = currencyRateRepository.findAll();
        Map<String, Double> ratesMap = new HashMap<>();
        rates.forEach(rate -> ratesMap.put(rate.getCurrency(), rate.getRate()));
        return ratesMap;
    }
}

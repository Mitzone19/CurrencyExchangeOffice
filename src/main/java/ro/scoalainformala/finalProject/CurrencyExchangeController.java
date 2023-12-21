package ro.scoalainformala.finalProject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;

@Controller
public class CurrencyExchangeController {

    @Autowired
    private CurrencyRateService currencyRateService;

    @GetMapping("/")
    public String showExchangeForm(Model model) {
        model.addAttribute("rates", currencyRateService.getCurrencyRates());
        return "exchangeForm";
    }
    @PostMapping("/convert")
    public String convertCurrency(@RequestParam double amount,
                                  @RequestParam String fromCurrency,
                                  @RequestParam String toCurrency,
                                  Model model) {
        Map<String, Double> rates = currencyRateService.getCurrencyRates();

        // Calculate the amount in RON first, then convert to the target currency
        double amountInRon = fromCurrency.equals("RON") ? amount : (amount / rates.getOrDefault(fromCurrency, 1.0));
        double convertedAmount = toCurrency.equals("RON") ? amountInRon : (amountInRon * rates.getOrDefault(toCurrency, 1.0));

        model.addAttribute("convertedAmount", convertedAmount);
        model.addAttribute("fromCurrency", fromCurrency);
        model.addAttribute("toCurrency", toCurrency);
        model.addAttribute("amount", amount);
        model.addAttribute("rates", rates);

        return "exchangeResult";
    }
}


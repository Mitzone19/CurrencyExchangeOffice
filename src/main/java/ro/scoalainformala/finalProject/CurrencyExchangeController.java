package ro.scoalainformala.finalProject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class CurrencyExchangeController {

    @Autowired
    private CurrencyRateService currencyRateService;

    @GetMapping("/")
    public String showExchangeForm(Model model) {
        model.addAttribute("rates", currencyRateService.getCurrencyRates());
        return "exchangeForm";
    }
}


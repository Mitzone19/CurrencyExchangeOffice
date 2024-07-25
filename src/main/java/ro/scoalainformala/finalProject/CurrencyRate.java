package ro.scoalainformala.finalProject;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CurrencyRate {

    @Id
    private String currency;
    private Double rate;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}


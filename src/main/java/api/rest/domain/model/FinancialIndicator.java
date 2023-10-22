package api.rest.domain.model;

import jakarta.persistence.Entity;
import org.springframework.stereotype.Component;

@Component
public class FinancialIndicator {
    // https://www.dadosdemercado.com.br/indices/cdi
    private final Double cdi = 12.65d;
    //https://www.dadosdemercado.com.br/indices/ipca-15
    private final Double ipca15 = 3.74d;

    public Double getCdi() {
        return cdi;
    }

    public Double getIpca15() {
        return ipca15;
    }
}

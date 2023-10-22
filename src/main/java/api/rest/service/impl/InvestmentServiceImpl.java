package api.rest.service.impl;

import api.rest.domain.model.Investment;
import api.rest.domain.model.User;
import api.rest.domain.repository.InvestmentRepository;
import api.rest.domain.model.FinancialIndicator;
import api.rest.domain.repository.UserRepository;
import api.rest.service.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class InvestmentServiceImpl implements InvestmentService {
    @Autowired
    FinancialIndicator financialIndicator;
    @Autowired
    InvestmentRepository investmentRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public Investment create(Investment investmentRequest) {
        if (investmentRequest.getDescription() == null ||
                investmentRequest.getType() == null ||
                investmentRequest.getIndexer() == null ||
                investmentRequest.getTerm() == null ||
                investmentRequest.getAmountInvested() == null ||
                investmentRequest.getInterestRate() == null ) {
            throw new IllegalArgumentException("Todo os campos devem ser preenchidos");
        }
        String[] investmentTypes = {"lci", "lca", "cri", "cra", "poupança", "cdb", "tesouro"};
        Set<String> investmentTypesSet = new HashSet<>(Arrays.asList(investmentTypes));
        if (!investmentTypesSet.contains(investmentRequest.getType().toLowerCase())) {
            throw new IllegalArgumentException("Tipo de investimento inválido");
        }
        Investment investmentResult = calculateInvestment(investmentRequest);
        investmentResult.setCreatorId(userRepository.findById(investmentRequest.getCreatorId().getId()).get());
        return investmentRepository.save(investmentResult);
    }

    @Override
    public Iterable<Investment> findAll() {
        return investmentRepository.findAll();
    }

    @Override
    public Investment findById(Long id) {
        return investmentRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        investmentRepository.deleteById(id);
    }

    private Investment calculateInvestment(Investment investment) {
        //String[] noIncomeTax = {"lci", "lca", "cri", "cra", "poupança"};
        String[] payIncomeTax = {"cdb", "tesouro"};
        //Set<String> noIncomeTaxSet = new HashSet<>(Arrays.asList(noIncomeTax));
        Set<String> payIncomeTaxSet = new HashSet<>(Arrays.asList(payIncomeTax));

        Double profit = calculateProfit(investment);
        investment.setGrossTotalValue(new BigDecimal(profit + investment.getAmountInvested()).setScale(2, RoundingMode.HALF_DOWN));

        boolean isItTaxed = payIncomeTaxSet.contains(investment.getType().toLowerCase());
        if (isItTaxed) {
            // calcular o imposto de renda e então retornar: investimento bruto - imposto de renda
            Double incomeTaxValue = calculateIncomeTaxValue(investment, profit);
            investment.setIncomeTaxValue(new BigDecimal(incomeTaxValue).setScale(2, RoundingMode.HALF_DOWN));
            investment.setDueIncomeTax(true);
            investment.setNetTotalValue(new BigDecimal(profit + investment.getAmountInvested() - incomeTaxValue).setScale(2, RoundingMode.HALF_DOWN));
        } else {
            // retornar o investimento sem IR
            investment.setNetTotalValue(new BigDecimal(profit + investment.getAmountInvested()).setScale(2, RoundingMode.HALF_DOWN));
            investment.setIncomeTaxValue(new BigDecimal(0));
            investment.setDueIncomeTax(false);
        }
        return investment;
    }

    private Double calculateProfit(Investment investment) {
        Double profit = 0d;
        Double termToDays = (investment.getTerm() * 30d);

        switch (investment.getIndexer().toLowerCase()) {
            case "pre":
                profit = (investment.getAmountInvested() * Math.pow(1 +
                        calculateDailyInterestRate(investment.getInterestRate()), termToDays))
                        - investment.getAmountInvested();
                break;
            case "pos":
                Double interestCdi = (investment.getInterestRate() * financialIndicator.getCdi()) / 100;
                profit = (investment.getAmountInvested() * Math.pow(1 +
                        calculateDailyInterestRate(interestCdi), termToDays))
                        - investment.getAmountInvested();;
                break;
            case "misto":
                Double interestIpca = investment.getInterestRate() + financialIndicator.getIpca15();
                profit = (investment.getAmountInvested() * Math.pow(1 +
                        calculateDailyInterestRate(interestIpca), termToDays))
                        - investment.getAmountInvested();;
                break;
            default:
                throw new IllegalArgumentException("Indexador inválido");
        }
        return profit;
    }

    private Double calculateIncomeTaxValue(Investment investment, Double profit) {
        Integer term = investment.getTerm();
        Double incomeTaxRate = 0d;
        Double incomeTaxValue = 0d;

        if (term <= 180){
            incomeTaxRate = 0.225;
            incomeTaxValue = profit * incomeTaxRate;

        } else if (term > 180 && term <= 360){
            incomeTaxRate = 0.2;
            incomeTaxValue = profit * incomeTaxRate;

        } else if (term > 360 && term <= 720){
            incomeTaxRate = 0.175;
            incomeTaxValue = profit * incomeTaxRate;

        } else {
            incomeTaxRate = 0.15;
            incomeTaxValue = profit * incomeTaxRate;

        }
        return incomeTaxValue;
    }

    private Double calculateDailyInterestRate(Double interestRateAnual) {
        double interestRateDecimal = (interestRateAnual) / 100d;
        double anualDays = 360d;
        double dailyInterestRate = Math.pow(1d + interestRateDecimal, 1.0 / anualDays) - 1d;
        return dailyInterestRate;
    }
}

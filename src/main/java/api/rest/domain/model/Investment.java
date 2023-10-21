package api.rest.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity(name = "tb_investment")
public class Investment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String description;
    String type;
    String indexer;
    Integer term;
    @Column(name = "amount_invested", precision = 13, scale = 2)
    BigDecimal amountInvested;
    @Column(name = "interest_rate", precision = 3, scale = 4)
    Float interestRate;
    @Column(name = "gross_total_value", precision = 13, scale = 2)
    BigDecimal grossTotalValue;
    @Column(name = "net_total_value", precision = 13, scale = 2)
    BigDecimal netTotalValue;
    @Column(name = "income_tax_value", precision = 13, scale = 2)
    BigDecimal incomeTaxValue;
    @Column(name = "income_tax_due")
    Boolean isDueIncomeTax;

    public Investment() {

    }

    public Investment(Long id, String description, String type, String indexer, Integer term, BigDecimal amountInvested, Float interestRate, BigDecimal grossTotalValue, BigDecimal netTotalValue, BigDecimal incomeTaxValue, Boolean isDueIncomeTax) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.indexer = indexer;
        this.term = term;
        this.amountInvested = amountInvested;
        this.interestRate = interestRate;
        this.grossTotalValue = grossTotalValue;
        this.netTotalValue = netTotalValue;
        this.incomeTaxValue = incomeTaxValue;
        this.isDueIncomeTax = isDueIncomeTax;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIndexer() {
        return indexer;
    }

    public void setIndexer(String indexer) {
        this.indexer = indexer;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public BigDecimal getAmountInvested() {
        return amountInvested;
    }

    public void setAmountInvested(BigDecimal amountInvested) {
        this.amountInvested = amountInvested;
    }

    public Float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Float interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getGrossTotalValue() {
        return grossTotalValue;
    }

    public void setGrossTotalValue(BigDecimal grossTotalValue) {
        this.grossTotalValue = grossTotalValue;
    }

    public BigDecimal getNetTotalValue() {
        return netTotalValue;
    }

    public void setNetTotalValue(BigDecimal netTotalValue) {
        this.netTotalValue = netTotalValue;
    }

    public BigDecimal getIncomeTaxValue() {
        return incomeTaxValue;
    }

    public void setIncomeTaxValue(BigDecimal incomeTaxValue) {
        this.incomeTaxValue = incomeTaxValue;
    }

    public Boolean getDueIncomeTax() {
        return isDueIncomeTax;
    }

    public void setDueIncomeTax(Boolean dueIncomeTax) {
        isDueIncomeTax = dueIncomeTax;
    }

    @Override
    public String toString() {
        return "Investment{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", indexer='" + indexer + '\'' +
                ", term=" + term +
                ", amountInvested=" + amountInvested +
                ", interestRate=" + interestRate +
                ", grossTotalValue=" + grossTotalValue +
                ", netTotalValue=" + netTotalValue +
                ", incomeTaxValue=" + incomeTaxValue +
                ", isDueIncomeTax=" + isDueIncomeTax +
                '}';
    }
}

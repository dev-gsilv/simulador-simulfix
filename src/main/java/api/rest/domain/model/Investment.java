package api.rest.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity(name = "tb_investment")
public class Investment {
    // Atributos fornnecidos pelo usuário
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String type; // tipo de investimento: "lci", "lca", "cri", "cra", "poupança", "cdb", "tesouro"
    private String indexer; // tipo de juros da simulação: pre-fixado, pos-fixado ou misto.
    private Integer term; // prazo do investimento em meses
    @Column(name = "interest_rate")
    private Double interestRate; // taxa de juros anual
    @Column(name = "amount_invested")//, precision = 13, scale = 2)
    private Double amountInvested; // valor investido em reais

    // Atributos calculados
    @Column(name = "gross_total_value", precision = 13, scale = 2)
    private BigDecimal grossTotalValue; // valor total do retorno
    @Column(name = "net_total_value", precision = 13, scale = 2)
    private BigDecimal netTotalValue; // valor líquido do retorno
    @Column(name = "income_tax_value", precision = 13, scale = 2)
    private BigDecimal incomeTaxValue; // valor do imposto de renda
    @Column(name = "income_tax_due")
    private Boolean isDueIncomeTax; // boleano para investimento com imposto de renda obrigatorio

    // Creator relation
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    private User creatorId;

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

    public Double getAmountInvested() {
        return amountInvested;
    }

    public void setAmountInvested(Double amountInvested) {
        this.amountInvested = amountInvested;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
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

    public User getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(User creatorId) {
        this.creatorId = creatorId;
    }
}

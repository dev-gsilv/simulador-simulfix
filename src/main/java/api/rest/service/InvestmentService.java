package api.rest.service;

import api.rest.domain.model.Investment;

public interface InvestmentService {
    Iterable<Investment> findAll();

    Investment findById(Long id);

    Investment create(Investment investment);

    void delete(Long id);

    // TODO Implementar métodos find e delete por type, indexer e term
}

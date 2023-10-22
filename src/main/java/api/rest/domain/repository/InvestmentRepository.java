package api.rest.domain.repository;

import api.rest.domain.model.Investment;
import org.springframework.data.repository.CrudRepository;

public interface InvestmentRepository extends CrudRepository<Investment, Long> {
}

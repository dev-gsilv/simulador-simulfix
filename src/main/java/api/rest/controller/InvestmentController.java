package api.rest.controller;

import api.rest.service.InvestmentService;
import api.rest.domain.model.Investment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/investment")
public class InvestmentController {
    @Autowired
    private InvestmentService investmentService;

    @GetMapping
    public Iterable<Investment> findAll() {
        return investmentService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Investment> findById(@PathVariable Long id) {
        return ResponseEntity.ok(investmentService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Investment> create(@RequestBody Investment investment) {
        investmentService.create(investment);
        return ResponseEntity.ok(investment);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        investmentService.delete(id);
        return ResponseEntity.ok().build();
    }
}

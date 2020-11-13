package transaction.analyzer.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import transaction.analyzer.models.Transaction;
import transaction.analyzer.service.TransactionService;
import transaction.analyzer.utils.EndPointsUtils;

import java.util.List;
import java.util.Optional;

import static transaction.analyzer.utils.EndPointsUtils.*;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService service;

    @GetMapping("/all")
    public List<Transaction> getAll(){
        return service.findAll();
    }

    @GetMapping("/average/{department}")
    public String getAverageForDepartment(@PathVariable String department){
        double averageByDepartment = service.getAverageAmountByDepartment(department);
        return String.format(AVERAGE_INFO,department,averageByDepartment);
    }

    @GetMapping("/max/{department}")
    public String getMaxForDepartment(@PathVariable String department){
        Optional<Transaction> maxAmountByDepartment = service.getMaxAmountByDepartment(department);
        return maxAmountByDepartment.isPresent()? maxAmountByDepartment.get().toString() : NOT_FIND;
    }

    @GetMapping("/min/{department}")
    public String getMinForDepartment(@PathVariable String department){
        Optional<Transaction> minAmountByDepartment = service.getMinAmountByDepartment(department);
        return minAmountByDepartment.isPresent()? minAmountByDepartment.get().toString() : NOT_FIND;
    }
}


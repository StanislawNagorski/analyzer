package transaction.analyzer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import transaction.analyzer.models.Transaction;
import transaction.analyzer.repositories.TransactionRepository;
import transaction.analyzer.utils.EndPointsUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static transaction.analyzer.utils.EndPointsUtils.DEPARTMENT_ALL;
import static transaction.analyzer.utils.EndPointsUtils.convertToDouble;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository repository;

    public Optional<Transaction> findById(Long id) {
        return repository.findById(id);
    }

    public List<Transaction> findAll() {
        return repository.findAll();
    }

    public Transaction save(Transaction transaction) {
        return repository.save(transaction);
    }

    public Iterable<Transaction> save(List<Transaction> transactions) {
        return repository.saveAll(transactions);
    }

    public double getAverageAmountByDepartment(String department) {
        return getTransactions(department).stream()
                .mapToDouble(value -> Double.parseDouble(value.getMoney().substring(1)))
                .average().getAsDouble();
    }

    public Optional<Transaction> getMaxAmountByDepartment(String department) {
        return getTransactions(department).stream()
                .max((o1, o2) -> (int) (convertToDouble(o1) - convertToDouble(o2)));
    }

    public Optional<Transaction> getMinAmountByDepartment(String department) {
        return getTransactions(department).stream()
                .min((o1, o2) -> (int) (convertToDouble(o1) - convertToDouble(o2)));
    }

    public double getMedianAmountByDepartment(String department) {
        List<Double> doubles = getTransactions(department).stream()
                .map(EndPointsUtils::convertToDouble)
                .collect(Collectors.toList());
        int size = doubles.size();
        return size % 2 != 0 ? doubles.get(size / 2) : doubles.get(size / 2 - 1);

    }

    private List<Transaction> getTransactions(String department) {
        List<Transaction> allByDepartment;
        if (department.equals(DEPARTMENT_ALL)) {
            allByDepartment = repository.findAll();
        } else {
            allByDepartment = repository.findAllByDepartment(department);
        }
        return allByDepartment;
    }

}

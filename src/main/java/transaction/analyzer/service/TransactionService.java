package transaction.analyzer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import transaction.analyzer.models.Transaction;
import transaction.analyzer.repositories.TransactionRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository repository;

    public Optional<Transaction> findById(Long id){
        return repository.findById(id);
    }

    public List<Transaction> findAll(){
        return repository.findAll();
    }

    public Transaction save(Transaction transaction){
        return repository.save(transaction);
    }

    public Iterable<Transaction> save(List<Transaction> transactions){
        return repository.saveAll(transactions);
    }

}

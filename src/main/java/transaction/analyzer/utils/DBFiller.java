package transaction.analyzer.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import transaction.analyzer.models.Transaction;
import transaction.analyzer.repositories.TransactionRepository;
import transaction.analyzer.service.TransactionService;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
@RequiredArgsConstructor
@Log4j2
public class DBFiller implements CommandLineRunner {
    private final TransactionService service;

    @Override
    public void run(String... args) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Transaction>> typeReference = new TypeReference<>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/transactions.json");
        try {
            List<Transaction> transactions = mapper.readValue(inputStream,typeReference);
            service.save(transactions);
            log.info("Data base is now filled");
        } catch (IOException e){
            log.error("Unable to save: " + e.getMessage());
        }
    }
}

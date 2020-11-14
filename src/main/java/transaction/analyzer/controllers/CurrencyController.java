package transaction.analyzer.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import transaction.analyzer.models.Currency;
import transaction.analyzer.models.Rates;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {
    public static final String URL = "/currencies.json";
    public static final String URL_1 = "http://api.nbp.pl/api/exchangerates/tables/A/?format=json";

    @GetMapping
    public List<Rates> callForCurrency() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Currency>> typeReference = new TypeReference<>() {};
        URLConnection connection = new URL(URL_1).openConnection();
        InputStream response = connection.getInputStream();
        List<Currency> currency = mapper.readValue(response, typeReference);
        return currency.get(0).getRates();

    }

}

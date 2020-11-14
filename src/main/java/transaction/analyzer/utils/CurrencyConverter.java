package transaction.analyzer.utils;

import transaction.analyzer.controllers.CurrencyController;
import transaction.analyzer.models.Rates;
import transaction.analyzer.models.Transaction;

import java.io.IOException;
import java.util.List;

public class CurrencyConverter {

    private static final CurrencyController currency = new CurrencyController();
    private static final List<Rates> rates = getCurrentRates();

    public static double convertToPLN(Transaction transaction){
        String moneyString = transaction.getMoney();
        double money = convertToDouble(moneyString);
        String transactionCurrency = transaction.getCurrency();

        for (Rates currentRate : rates) {
            if (currentRate.getCode().equals(transactionCurrency)){
                return money * currentRate.getMid();
            }
        }
        return money;
    }

    private static double convertToDouble(String money){
        return Double.parseDouble(money.substring(1));
    }

    private static List<Rates> getCurrentRates(){
        List<Rates> rates = null;
        try {
            rates = currency.callForCurrency();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rates;
    }

    
}

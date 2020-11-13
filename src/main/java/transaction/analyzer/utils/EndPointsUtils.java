package transaction.analyzer.utils;

import transaction.analyzer.models.Transaction;

public class EndPointsUtils {

    public static final String AVERAGE_INFO = "Average for department: %s is %.2f";
    public static final String NOT_FIND ="Could not find any transaction";

    public static double convertToDouble(Transaction transaction){
        return Double.parseDouble(transaction.getMoney().substring(1));
    }
}

package transaction.analyzer.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Rates {
    private String currency;
    private String code;
    private double mid;
}

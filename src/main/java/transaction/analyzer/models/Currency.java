package transaction.analyzer.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class Currency {
    private String table;
    private String no;
    private String effectiveDate;
    private List<Rates> rates;

}

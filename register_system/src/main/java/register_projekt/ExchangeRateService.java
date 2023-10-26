package register_projekt;

import java.math.BigDecimal;
import java.util.Currency;

public interface ExchangeRateService {
    BigDecimal getExchangeRate(Currency fromCurrency, Currency toCurrency);
}

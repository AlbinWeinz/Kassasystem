package register_projekt;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PaymentFailedExceptionTest {

    @Test
    public void testConstructorWithoutMessage() {
        PaymentFailedException exception = new PaymentFailedException();
        assertNull(exception.getMessage());
    }
    @Test
    public void testConstructorWithMessage() {
        String errorMessage = "Payment failed due to insufficient funds";
        PaymentFailedException exception = new PaymentFailedException(errorMessage);
        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    public void testExceptionMessageWhenThrown() {
        String errorMessage = "Payment failed due to a technical error";
        try {
            throw new PaymentFailedException(errorMessage);
        } catch (PaymentFailedException exception) {
            assertEquals(errorMessage, exception.getMessage());
        }
    }
}

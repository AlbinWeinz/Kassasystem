package register_projekt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckOutTest {
    @Mock
    private CheckOut.Payment mockPayment;

    @InjectMocks
    private CheckOut checkOut;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPaymentSuccessful() throws PaymentFailedException {
        double amount = 50.0;
        Mockito.when(mockPayment.chargeCard(amount)).thenReturn(true);

        boolean paymentSuccess = checkOut.processPayment(amount);
        assertTrue(paymentSuccess);

    }

    @Test
    public void testPaymentFailed() {
        double amount = 25.0;
        Mockito.when(mockPayment.chargeCard(amount)).thenReturn(false);

        assertThrows(PaymentFailedException.class, () -> checkOut.processPayment(amount));
    }

    @Test
    public void testChargeCard() {
        double amount = 100.0;
        assertThrows(PaymentFailedException.class, () -> checkOut.processPayment(amount));
    }

    @Test
    public void testChargeCardWithInsufficientFunds()  {
        CheckOut.Payment payment = amount -> false;
        CheckOut checkOut = new CheckOut(payment);
        double amount = 1000.0;
        assertThrows(PaymentFailedException.class, () -> checkOut.processPayment(amount));
    }
}
package register_projekt;

public class CheckOut {
    private final Payment payment;
    public CheckOut(Payment payment){
        this.payment=payment;
    }
    public boolean processPayment(double amount) throws PaymentFailedException {
            boolean paymentSuccess = payment.chargeCard(amount);
            if (!paymentSuccess) {
                throw new PaymentFailedException("Payment Failed");
            }
            return true;
        }
    public interface Payment{
        boolean chargeCard(double amount);
    }


}

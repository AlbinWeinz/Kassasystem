package register_projekt;

public class CheckOut {
    private Payment payment;
    public CheckOut(Payment payment){
        this.payment=payment;
    }
    public boolean processPayment(double amount) throws PaymentFailedException {
            boolean paymentSucces = payment.chargeCard(amount);
            if (!paymentSucces) {
                throw new PaymentFailedException("Payment Failed");
            }
            return true;
        }
    public interface Payment{
        boolean chargeCard(double amount);
    }


}

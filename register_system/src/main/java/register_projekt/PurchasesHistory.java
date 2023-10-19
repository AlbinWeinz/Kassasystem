package register_projekt;

import java.util.ArrayList;
import java.util.List;

public class PurchasesHistory extends Customers {

    private List<Customers> customerDataList = new ArrayList<>();

    public PurchasesHistory(String personalNumber, int amountSpent, int yearsAsMember) {
        this.personalNumber=personalNumber;
    }

    public PurchasesHistory() {

    }

    public void addCustomerData(String personalNumber, int amountSpent, int yearsAsMember) {
       if(super.personalNumber.equals(personalNumber)) {
           PurchasesHistory purchasesHistory=new PurchasesHistory(personalNumber,amountSpent,yearsAsMember);
           customerDataList.add(purchasesHistory);
       } //lägg till error medelande

    }

    public void updatePurchasesHistory(int newYearsAsMember, int newAmountSpent, int newAmountOfPurchases) {
        super.amountOfPurchases = super.amountOfPurchases + newAmountOfPurchases;
        super.amountSpent= super.amountSpent + newAmountSpent;
        super.yearsAsMember= super.yearsAsMember + newYearsAsMember;

        //super.points=calculatePoints(newAmountOfPurchases, newAmountSpent, newYearsAsMember); uppdaterar poängen tror jag
    }

    public double averageAmountPerCustomer (String personalNumber){
        for (Customers customer : customerDataList) {
            if (customer.getPersonalNumber().equalsIgnoreCase(personalNumber)) {
                int amountSpent = customer.amountSpent;
                int yearsAsMember = customer.yearsAsMember;
                if (yearsAsMember > 0) {
                    return (double) amountSpent / yearsAsMember;
                }
            }
        }
        return 0.0;
    }

    @Override
    public String toString(){
        return super.toString()+
                "\nHur mycket kunden spenderat: " + amountSpent +
                "\nHur många köp kunden har gjort: " + amountOfPurchases +
                "\nAntalet köp per år: " + yearsAsMember +
                "\nMedelvärdet av hur mycket kunden spenderat: " + averageAmountPerCustomer (personalNumber);
    }
}

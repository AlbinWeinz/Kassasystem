package register_projekt;

import java.util.ArrayList;
import java.util.List;

public class PurchasesHistory extends Customer {

    private List<Customer> customerDataList = new ArrayList<>();

    public PurchasesHistory(String personalNumber, int amountSpent, int yearsAsMember, int amountOfPurchases) {
        super();
        this.personalNumber=personalNumber;
    }

    public PurchasesHistory() {
    }


    public PurchasesHistory(String personalNumber, int amountSpent, int yearsAsMember) {

    }

    public void addCustomerData(String personalNumber, int amountSpent, int yearsAsMember) {
       if(super.personalNumber.equals(personalNumber)) {
           PurchasesHistory purchasesHistory=new PurchasesHistory(personalNumber,amountSpent,yearsAsMember);
           customerDataList.add(purchasesHistory);
       }

    }

    public Customer updatePurchasesHistory(int newYearsAsMember, int newAmountSpent, int newAmountOfPurchases) {
        super.amountOfPurchases += newAmountOfPurchases;
        super.amountSpent += newAmountSpent;
        super.yearsAsMember += newYearsAsMember;

        return this;

        //super.points=calculatePoints(newAmountOfPurchases, newAmountSpent, newYearsAsMember); uppdaterar poängen tror jag
    }

    public double averageAmountPerCustomer (String personalNumber){
        for (Customer customer : customerDataList) {
            if (customer.getPersonalNumber().equals(personalNumber)) {
                int amountSpent = customer.amountSpent;
                int yearsAsMember = customer.yearsAsMember;
                if (yearsAsMember > 0) {
                    return (double) amountSpent / yearsAsMember;
                }
            }
        }
        return 0.0;
    }

    public double averageAmountPerPurchase(String personalNumber){
        for (Customer customer : customerDataList) {
            if (customer.getPersonalNumber().equals(personalNumber)) {
                int amountSpent = customer.amountSpent;
                int amountOfPurchases = customer.amountOfPurchases;
                if (yearsAsMember > 0) {
                    return (double) amountSpent /amountOfPurchases;
                }
            }
        }
        return 0.0;
    }

    public void removeCustomerData(String personalNumber){
       for(Customer customer:customerDataList)
        if(super.personalNumber.equals(personalNumber)){
            customerDataList.remove(customer);
            break;
        }
    }

    @Override
    public String toString(){
        return super.toString()+
                "\nHur mycket kunden spenderat: " + amountSpent +
                "\nHur många köp kunden har gjort: " + amountOfPurchases +
                "\nAntalet köp per år: " + yearsAsMember +
                "\nMedelvärdet av hur mycket kunden spenderat per år: " + averageAmountPerCustomer (personalNumber)+
                "\nMedelvärdet av hur mycket kunden spenderat per köp: "+averageAmountPerPurchase(personalNumber);
    }
}

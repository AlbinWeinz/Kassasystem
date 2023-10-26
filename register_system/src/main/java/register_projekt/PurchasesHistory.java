package register_projekt;

import java.util.ArrayList;
import java.util.List;

public class PurchasesHistory extends Customer {

    protected static List<PurchasesHistory> purchaseList = new ArrayList<>();

    public PurchasesHistory(String personalNumber, int amountSpent, int yearsAsMember, int amountOfPurchases) {
       super(personalNumber,amountSpent,yearsAsMember,amountOfPurchases);
    }

    public PurchasesHistory(){
        super("", 1,1,1);
    }

    public PurchasesHistory(String personalNumber){
        super(personalNumber,0,0,0);

    }

    public void updatePurchasesHistory(String personalNumber,int newYearsAsMember, int newAmountSpent, int newAmountOfPurchases) {
        if(super.personalNumber.equals(personalNumber)) {
            super.amountOfPurchases += newAmountOfPurchases;
            super.amountSpent += newAmountSpent;
            super.yearsAsMember += newYearsAsMember;
        }
    }

    public double averageAmountPerPurchase(String personalNumber){
            if (this.personalNumber.equals(personalNumber)) {
                if (yearsAsMember > 0) {
                    return (double) amountSpent /amountOfPurchases;
                }
            }
        return 0.0;
    }

    @Override ///fixa!
    public String toString(){
        return "\nPersonnummer: " + personalNumber +
                "\nHur mycket kunden spenderat: " + amountSpent +
                "\nHur många köp kunden har gjort: " + amountOfPurchases +
                "\nAntalet köp per år: " + yearsAsMember +
                "\nMedelvärdet av hur mycket kunden spenderat per köp: "+averageAmountPerPurchase(personalNumber);
    }
}

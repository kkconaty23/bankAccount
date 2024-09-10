Kevin Conaty 

Lab 1 

 

public class App { 

    

    public static void main(String[] args) throws Exception { 

         

        //debit kevinsCard = new debit(); 

        //debit justinsCard = new debit(50); 

 

    //     kevinsCard.setBalance(400); 

    //     kevinsCard.deposit(50); 

 

    //    kevinsCard.withdraw(5); 

 

    //     System.out.println(kevinsCard.getBalance()); 

 

    // credit newCard = new credit(2000); 

 

    // newCard.withdraw(500); 

    // newCard.withdraw(500); 

 

    // newCard.deposit(1); 

    // System.out.println(newCard.toString()); 

 

    loanAccount carLoan = new loanAccount(20000); 

    carLoan.deposit(200); 

    carLoan.deposit(500); 

 

    System.out.println(carLoan.toString()); 

    carLoan.getPayments(); 

 

    } 

} 

 

public abstract class bankAccount { 

 

    private double balance; 

    int accountNumber; 

 

    public bankAccount(){ 

        this.balance = 0; 

    } 

 

    public bankAccount(double _balance){ 

        this.balance = _balance; 

    } 

 

    public bankAccount(bankAccount act){ 

        this.balance = act.balance; 

    } 

 

    public double getBalance(){ 

        return this.balance; 

    } 

 

    public void setBalance(double adjustBal){ 

        this.balance = adjustBal; 

    } 

     

 

    public abstract boolean deposit(double _ammount); 

    public abstract boolean withdraw(double _ammount); 

 

 

} 

 

public class debit extends bankAccount { 

    debit(){ 

        super(); 

    } 

    debit(double promo){ 

        setBalance(promo); 

    } 

 

 

    @Override 

    public boolean deposit(double _ammount){ 

        if(_ammount <= 0){                                              //checks if ammount going in is > $0 

            System.out.println("Incorrect must deposit more that $0"); 

            return false; 

        } 

        setBalance(getBalance() + _ammount);                            //if > $0 sets the new balance to old balance + ammount added 

        return true; 

    } 

 

    @Override 

    public boolean withdraw(double _ammount){ 

        if(_ammount > getBalance()){                                    // check if account will be overdrawn 

            System.out.println("you do not have that much money"); 

            return false; 

        } 

        if (_ammount <= 0){                                             //prevents negative account withdrawl 

            System.out.println("Enter ammount greater than 0 bozo"); 

            return false; 

        } 

        double wBalance = getBalance() - _ammount;                      // deducts withdraw ammount from account balance 

        setBalance(wBalance); 

        return true; 

    } 

 

    @Override 

    public String toString(){                                           //use toString method to print current balance 

        String debitGreeting = "Your balance is " + getBalance(); 

        return debitGreeting; 

    } 

 

 

} 

public class credit extends bankAccount{ 

    public int creditLimit; 

     

    credit(){ 

        super(); 

    } 

 

    credit(int limit){                                              //args constructor to set a credit limit 

        this.creditLimit = limit; 

 

    } 

 

 

    @Override                                                       //override deposit to "subtract" (add debt) from balance 

    public boolean deposit(double _ammount) {                       // makes sure the ammount being used is not greater than card limit 

    if (_ammount > creditLimit){ 

        System.out.println("Over the limit!"); 

        return false; 

    } 

 

        double wBalance = getBalance() - _ammount; 

        setBalance(wBalance); 

        return true; 

        

    } 

 

         

     

 

    @Override                                                       //override withdraw to "add" (remove debt) from credit balance 

    public boolean withdraw(double _ammount) { 

        if(_ammount <= 0){ 

            System.out.println("invalid"); 

            return false; 

        } 

        setBalance(getBalance() + _ammount); 

        return true; 

    } 

 

    @Override 

    public String toString(){                                       //override toString method to show credit limit and balance 

       String creditGreeting = "Current card limit is: " + creditLimit + "\n" + "Current balance is: " + getBalance(); 

       return creditGreeting; 

    } 

    } 

 

 

 

 

 

 

public class loanAccount extends bankAccount { 

 

    private double principle; 

    double principleTotalMade =0; 

    double interestPayments =0; 

    private double interest = .05; 

    double loan = principle * interest; 

     

 

     

    loanAccount(double principle){ 

        this.principle = principle; 

        this.loan = principle * interest; 

         

 

    } 

    @Override 

    public boolean deposit(double _ammount){  //make payemnt only on the principle 

        if (_ammount <= 0 ){ 

            return false; 

        } 

        else if (_ammount > principle){ 

            return false;                              //paying more than is due 

        } 

        else{            

            this.principle -= _ammount; 

             

            principleTotalMade += _ammount; 

            interestPayments += _ammount * interest; 

            loan = this.principle + (this.principle * interest); 

            updateLoanAndInterest();                //calls method to adjust new balance 

             

             

            return true; 

             

        } 

    } 

 

    private void updateLoanAndInterest() {       // Update the total loan amount and interest based on the remaining principle 

         loan = this.principle * interest; 

        interestPayments = principleTotalMade * interest;  

    } 

 

    public boolean getPayments(){ 

         

        System.out.println("You have paid $" + principleTotalMade + " towards the principle."); 

        System.out.println("You have paid $" + interestPayments + " in interest."); 

        System.out.println("You have $" + (principle - principleTotalMade) + " left on the principle."); 

        return true; 

    } 

 

                                                     

    @Override 

    public boolean withdraw(double _ammount) { 

         

        return false; 

    } 

 

 

    @Override 

    public String toString(){ 

        String balanceCheck = "Hello you currently owe " + principle; 

        return balanceCheck; 

    } 

 

     

 

} 

 

 
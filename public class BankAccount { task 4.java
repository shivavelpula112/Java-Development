public class BankAccount {
    private double balance = 0; // Declare the balance variable

    public String deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return "Deposit successful! New balance: " + balance;
        }
        return "Invalid deposit amount!";
    }

    public String withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return "Withdrawal successful! New balance: " + balance;
        } else if (amount > balance) {
            return "Insufficient balance!";
        }
        return "Invalid withdrawal amount!";
    }

    public String checkBalance() {
        return "Current balance: " + balance; // Fixed syntax error
    }
}

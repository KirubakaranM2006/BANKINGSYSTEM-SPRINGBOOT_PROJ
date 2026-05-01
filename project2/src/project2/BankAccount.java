package project2;

public class BankAccount {

    private double balance;

    // Constructor
    public BankAccount(double balance) {
        this.balance = balance;
    }

    // Deposit
    public void deposit(double amount) {
        balance += amount;
    }

    // Withdraw
    public void withdraw(double amount) {
        if (amount > balance) {
            throw new RuntimeException("Insufficient balance");
        }
        balance -= amount;
    }

    // Getter
    public double getBalance() {
        return balance;
    }
}
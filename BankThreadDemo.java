import java.util.Random;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public synchronized void deposit(double amount) {
        balance += amount;
        System.out.println(Thread.currentThread().getName() + " deposited $" + amount + ". New balance: $" + balance);
    }

    public synchronized void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " withdrew $" + amount + ". New balance: $" + balance);
        } else {
            System.out.println(Thread.currentThread().getName() + " insufficient funds for withdrawal.");
        }
    }

    public double getBalance() {
        return balance;
    }
}

class Customer implements Runnable {
    private static final int NUM_TRANSACTIONS = 5;
    private BankAccount account;

    public Customer(BankAccount account) {
        this.account = account;
    }

    @Override
    public void run() {
        Random random = new Random();

        for (int i = 0; i < NUM_TRANSACTIONS; i++) {
            double amount = random.nextDouble() * 100; // Random transaction amount

            if (random.nextBoolean()) {
                account.deposit(amount);
            } else {
                account.withdraw(amount);
            }

            try {
                Thread.sleep(500); // Simulate some processing time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class BankThreadDemo {
    public static void main(String[] args) {
        BankAccount sharedAccount = new BankAccount(1000);

        Thread customer1 = new Thread(new Customer(sharedAccount), "Customer 1");
        Thread customer2 = new Thread(new Customer(sharedAccount), "Customer 2");

        customer1.start();
        customer2.start();
    }
}

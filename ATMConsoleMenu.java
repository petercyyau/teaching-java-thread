import java.util.Scanner;

public class ATMConsoleMenu {

    private static final int WELCOME_SCREEN_TIMEOUT = 5000; // 5 seconds in milliseconds
    private static volatile boolean userInputReceived = false;

    public static void main(String[] args) {
        System.out.println("Welcome to the ATM!");

        Thread inputThread = new Thread(() -> getUserInput());
        inputThread.start();

        // Display the main menu
        displayMenu();

        // Wait for the input thread to finish or timeout
        try {
            inputThread.join(WELCOME_SCREEN_TIMEOUT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // If the user didn't input anything, return to the welcome screen
        if (!userInputReceived) {
            System.out.println("\nReturning to the Welcome Screen...");
            // You can add additional logic or actions here
        }
    }

    private static void displayMenu() {
        System.out.println("\nATM Menu:");
        System.out.println("1. Check Balance");
        System.out.println("2. Withdraw Money");
        System.out.println("3. Deposit Money");
        System.out.println("4. Exit");
    }

    private static void getUserInput() {
        Scanner scanner = new Scanner(System.in);
        userInputReceived = false;

        System.out.print("\nEnter your choice: ");
        String userInput = scanner.nextLine();

        // Set the flag to indicate that user input has been received
        userInputReceived = true;

        // Process user input (you can add more logic here)
        switch (userInput) {
            case "1":
                System.out.println("Checking Balance...");
                break;
            case "2":
                System.out.println("Withdrawing Money...");
                break;
            case "3":
                System.out.println("Depositing Money...");
                break;
            case "4":
                System.out.println("Exiting ATM. Goodbye!");
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}

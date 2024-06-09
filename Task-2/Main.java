import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();

        while (true) {
            System.out.println("BankY - Choose an option:");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Funds");
            System.out.println("3. Withdraw Funds");
            System.out.println("4. Transfer Funds");
            System.out.println("5. Display Account");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Account Number: ");
                    String accountNumber = scanner.nextLine();
                    System.out.print("Enter Account Holder Name: ");
                    String accountHolderName = scanner.nextLine();
                    System.out.print("Enter Initial Balance: ");
                    double initialBalance = scanner.nextDouble();
                    bank.addAccount(new BankAccount(accountNumber, accountHolderName, initialBalance));
                    break;

                case 2:
                    System.out.print("Enter Account Number: ");
                    accountNumber = scanner.nextLine();
                    System.out.print("Enter Deposit Amount: ");
                    double depositAmount = scanner.nextDouble();
                    BankAccount depositAccount = bank.getAccount(accountNumber);
                    if (depositAccount != null) {
                        depositAccount.deposit(depositAmount);
                        bank.saveData();
                        System.out.println("Deposit successful. New Balance: " + depositAccount.getBalance());
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter Account Number: ");
                    accountNumber = scanner.nextLine();
                    System.out.print("Enter Withdrawal Amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    BankAccount withdrawAccount = bank.getAccount(accountNumber);
                    if (withdrawAccount != null) {
                        if (withdrawAccount.withdraw(withdrawAmount)) {
                            bank.saveData();
                            System.out.println("Withdrawal successful. New Balance: " + withdrawAccount.getBalance());
                        } else {
                            System.out.println("Insufficient funds.");
                        }
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter From Account Number: ");
                    String fromAccountNumber = scanner.nextLine();
                    System.out.print("Enter To Account Number: ");
                    String toAccountNumber = scanner.nextLine();
                    System.out.print("Enter Transfer Amount: ");
                    double transferAmount = scanner.nextDouble();
                    BankAccount fromAccount = bank.getAccount(fromAccountNumber);
                    BankAccount toAccount = bank.getAccount(toAccountNumber);
                    if (fromAccount != null && toAccount != null) {
                        if (fromAccount.transfer(toAccount, transferAmount)) {
                            bank.saveData();
                            System.out.println("Transfer successful.");
                        } else {
                            System.out.println("Insufficient funds.");
                        }
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 5:
                    System.out.print("Enter Account Number: ");
                    accountNumber = scanner.nextLine();
                    BankAccount account = bank.getAccount(accountNumber);
                    if (account != null) {
                        System.out.println(account);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 6:
                    System.out.println("Exiting BankY. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;

            }
            System.out.println();
            System.out.println();
        }
    }
}

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Bank {
    private static final String FILE_NAME = "bank_data.dat";
    private Map<String, BankAccount> accounts;

    @SuppressWarnings("unchecked")
    public Bank() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            accounts = (HashMap<String, BankAccount>) ois.readObject();
        } catch (FileNotFoundException e) {
            accounts = new HashMap<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addAccount(BankAccount account) {
        accounts.put(account.getAccountNumber(), account);
        saveData();
    }

    public BankAccount getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(accounts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

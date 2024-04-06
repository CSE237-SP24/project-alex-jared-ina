package bankapp;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String username;
    private String password; 
    private Map<String, BankAccount> accounts; 

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.accounts = new HashMap<>();
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    public void addAccount(BankAccount account) {
        accounts.put(account.getAccountName(), account);
    }

    public BankAccount getAccount(String accountName) {
        return accounts.get(accountName);
    }

    public String getUsername() {
        return username;
    }

    public Map<String, BankAccount> getAccounts() {
        return accounts;
    }
}

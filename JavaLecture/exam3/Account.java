package JavaLecture.exam3;

import JavaLecture.exam3.Exceptions.NotAllowTransferException;
import JavaLecture.exam3.Exceptions.NotAllowWithdrawException;
import JavaLecture.exam3.Exceptions.NotEnoughException;
import java.io.IOException;

public abstract class Account implements Comparable<Account> {

    private int accountNumber;
    private String accountName;
    private String ownerName;
    protected long balance;

    public Account(int accountNumber, String accountName, String ownerName, long balance) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    // 입금
    public abstract void deposit() throws IOException;

    public abstract void deposit(long amount);

    public abstract void withdraw() throws NotAllowWithdrawException;

    public abstract void withdraw(long amount) throws NotEnoughException;

    public abstract void transfer() throws NotAllowTransferException;

    public abstract String toString();

    public long getBalance() {
        return balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int compareTo(Account other) {
        return this.accountNumber - other.accountNumber; // 오름차순 정렬
    }

}

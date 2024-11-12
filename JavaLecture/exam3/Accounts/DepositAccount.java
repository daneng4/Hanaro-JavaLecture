package JavaLecture.exam3.Accounts;

import JavaLecture.exam3.Account;
import JavaLecture.exam3.Exceptions.NotAllowTransferException;
import JavaLecture.exam3.Exceptions.NotAllowWithdrawException;
import JavaLecture.exam3.Exceptions.NotEnoughException;

public class DepositAccount extends Account {

    private int accountNumber;
    private String accountName;
    private String owner;
    private long balance;

    public DepositAccount(int accountNumber, String accountName, String owner, long balance) {
        super(accountNumber, accountName, owner, balance);

    }

    @Override
    public void deposit() {

    }

    @Override
    public void deposit(long amount) {

    }

    @Override
    public void withdraw() throws NotAllowWithdrawException {
        throw new NotAllowWithdrawException("출금할 수 없는 통장입니다.");
    }

    @Override
    public void withdraw(long amount) throws NotEnoughException {

    }

    @Override
    public void transfer() throws NotAllowTransferException {
        throw new NotAllowTransferException("이체할 수 없는 통장입니다.");
    }

    public void expiration() {

    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public long getBalance() {
        return 0;
    }
}

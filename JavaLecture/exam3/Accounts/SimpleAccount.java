package JavaLecture.exam3.Accounts;

import JavaLecture.InputReader;
import JavaLecture.exam3.Account;
import JavaLecture.exam3.Exceptions.NotEnoughException;
import JavaLecture.exam3.Main;
import JavaLecture.exam3.Message;
import java.io.IOException;

public class SimpleAccount extends Account {

    private int accountNumber;
    private String accountName;
    private String ownerName;
    protected long balance;

    public SimpleAccount(int accountNumber, String accountName, String ownerName, long balance) {
        super(accountNumber, accountName, ownerName, balance);
    }

    @Override
    public void deposit() throws IOException {
        while (true) {
            try {
                System.out.print(Message.DEPOSIT_MSG);
                String input = InputReader.readInput();
                if (InputReader.isEscape(input)) {
                    return;
                }
                if (!input.chars().allMatch(Character::isDigit)) {
                    throw new IllegalArgumentException();
                }
                long amount = Long.parseLong(input);
                if (amount < 0) {
                    throw new IllegalArgumentException();
                } else {
                    deposit(amount);
                    System.out.printf("%s 통장에 %,d원이 입금되었습니다!\n", this.getAccountName(), amount);
                    return;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("잘못된 입금 금액입니다!");
            }
        }
    }

    @Override
    public void deposit(long amount) {
        this.balance += amount;
    }

    @Override
    public void withdraw() {
        while (true) {
            try {
                System.out.println(Message.WITHDRAW_MSG);
                String input = InputReader.readInput();
                if (InputReader.isEscape(input)) {
                    break;
                }
                long amount = Long.parseLong(input);
                withdraw(amount);
            } catch (NotEnoughException | IOException e) {
                System.out.println(e.getMessage());
            }

        }

    }

    @Override
    public void withdraw(long amount) throws NotEnoughException {
        if (amount > this.getBalance()) {
            throw new NotEnoughException(
                String.format("잔액이 부족합니다! (잔액 : %,d원)", this.getBalance()));
        } else {
            this.balance -= amount;
            System.out.printf("%s 통장의 잔액은 %,d원 입니다.\n", this.getAccountName(),
                this.getBalance());
        }
    }


    @Override
    public void transfer() {
        while (true) {
            try {
                System.out.printf("%s (%s) ", Message.TRANSFER_MSG,
                    Message.excludeCurrentAccount(accountNumber));

                String input = InputReader.readInput();
                if (InputReader.isEscape(input)) {
                    break;
                }
                Account targetAccount = Main.accounts.get(Integer.parseInt(input) - 1);
                while (true) {
                    try {
                        System.out.printf("%s 통장에 보낼 금액은? ", targetAccount.getAccountName());
                        input = InputReader.readInput();
                        if (InputReader.isEscape(input)) {
                            break;
                        }
                        long amount = Long.parseLong(input);
                        if (amount > this.getBalance()) {
                            throw new NotEnoughException(
                                String.format("잔액이 부족합니다! (잔액 : %,d원)", this.getBalance()));
                        } else {
                            withdraw(amount);
                            targetAccount.deposit(amount);
                            System.out.printf("%s 통장에 %,d원이 입금되었습니다.\n",
                                targetAccount.getAccountName(), amount);
                            return;
                        }
                    } catch (NotEnoughException e) {
                        System.out.println(e.getMessage());
                    }

                }

            } catch (IOException e) {
                System.out.println("잘못된 입력입니다.");
            }

        }

    }

    @Override
    public long getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return String.format("자유입출금 통장 (계좌번호: %s, 잔액: %,d원, 예금주: %s)", this.getAccountNumber(),
            this.getBalance(), this.getOwnerName());
    }
}

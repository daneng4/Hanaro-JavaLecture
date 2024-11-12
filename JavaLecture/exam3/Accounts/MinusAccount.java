package JavaLecture.exam3.Accounts;

import JavaLecture.InputReader;
import JavaLecture.exam3.Account;
import JavaLecture.exam3.Main;
import JavaLecture.exam3.Message;
import java.io.IOException;

public class MinusAccount extends SimpleAccount {

    public MinusAccount(int accountNumber, String accountName, String owner, long balance) {
        super(accountNumber, accountName, owner, balance);
    }

    @Override
    public void withdraw(long amount) {
        this.balance -= amount; // 잔액이 음수가 될 수 있음
        System.out.printf("%s 통장의 잔액은 %,d원 입니다.\n", this.getAccountName(), this.getBalance());
    }

    @Override
    public void transfer() {
        while (true) {
            try {
                System.out.printf("%s (%s) ", Message.TRANSFER_MSG,
                    Message.excludeCurrentAccount(this.getAccountNumber()));

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

                        // MinusAccount는 잔액 확인 없이 이체 허용
                        withdraw(amount);
                        targetAccount.deposit(amount);
                        System.out.printf("%s 통장에 %,d원이 입금되었습니다.\n",
                            targetAccount.getAccountName(), amount);
                        return;
                    } catch (IOException e) {
                        System.out.println("잘못된 입력입니다.");
                    }
                }

            } catch (IOException e) {
                System.out.println("잘못된 입력입니다.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public String toString() {
        return String.format("%s통장 - 잔액: %,d", this.getAccountName(), this.getBalance());
    }
}

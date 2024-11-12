package JavaLecture.exam3;

import JavaLecture.InputReader;
import JavaLecture.exam3.Accounts.DepositAccount;
import JavaLecture.exam3.Accounts.MinusAccount;
import JavaLecture.exam3.Accounts.SimpleAccount;
import JavaLecture.exam3.Exceptions.NotAllowTransferException;
import JavaLecture.exam3.Exceptions.NotAllowWithdrawException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static ArrayList<Account> accounts;

    public static void main(String[] args) throws IOException {
        initAccount();
        process();
    }

    private static void initAccount() {
        String owner = "홍길동";
        SimpleAccount simpleAccount = new SimpleAccount(1, "자유입출금", owner, 0);
        DepositAccount depositAccount = new DepositAccount(2, "정기예금", owner, 50000000);
        MinusAccount minusAccount = new MinusAccount(3, "마이너스", owner, 0);
        accounts = new ArrayList<>();
        accounts.add(simpleAccount);
        accounts.add((depositAccount));
        accounts.add(minusAccount);
        Collections.sort(accounts);
    }

    private static void process() throws IOException {
        while (true) {
            System.out.print("통장을 선택하세요 (1: 자유입출금, 2: 정기예금, 3:마이너스) ");
            String selectedAccount = InputReader.readInput();
            int accountNumber = Integer.parseInt(selectedAccount);
            if (InputReader.isEscape(selectedAccount)) {
                break;
            }
            System.out.println(accounts.get(accountNumber - 1));
            doService(accountNumber);
        }
    }

    private static void doService(int accountNumber) throws IOException {
        switch (accountNumber) {
            case 2 -> {
                while (true) {
                    try {
                        System.out.print("정기 예금이 만기되었습니다. (+:만기처리, -:출금, T:이체, I:정보) ");
                        String command = InputReader.readInput();
                        if (InputReader.isEscape(command)) {
                            break;
                        }
                        switch (command) {
                            case "+" -> {
                                if (accounts.get(
                                    accountNumber - 1) instanceof DepositAccount depositAccount) {
                                    depositAccount.expiration();
                                }
                            }
                            case "-" -> accounts.get(accountNumber - 1).withdraw();
                            case "T" -> accounts.get(accountNumber - 1).transfer();
                            case "I" -> System.out.println(accounts.get(accountNumber - 1));
                            default -> throw new IllegalArgumentException("유효하지 않은 업무 명령입니다.");
                        }
                    } catch (IllegalArgumentException | NotAllowWithdrawException |
                             NotAllowTransferException e) {
                        System.out.println(e.getMessage());
                    }

                }
            }
            case 1, 3 -> {
                while (true) {
                    try {
                        System.out.println("원하시는 업무는? (+:입금, -:출금, T:이체, I:정보) ");
                        String command = InputReader.readInput();
                        if (InputReader.isEscape(command)) {
                            break;
                        }
                        switch (command) {
                            case "+" -> accounts.get(accountNumber - 1).deposit();
                            case "-" -> accounts.get(accountNumber - 1).withdraw();
                            case "T" -> accounts.get(accountNumber - 1).transfer();
                            case "I" -> System.out.println(accounts.get(accountNumber - 1));
                            default -> throw new IllegalArgumentException("유효하지 않은 업무 명령입니다.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    } catch (NotAllowWithdrawException | NotAllowTransferException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}

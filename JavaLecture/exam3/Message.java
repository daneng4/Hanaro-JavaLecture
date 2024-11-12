package JavaLecture.exam3;

public class Message {

    public static final String DEPOSIT_MSG = "입금 하실 금액은? ";
    public static final String WITHDRAW_MSG = "출금 하실 금액은? ";
    public static final String TRANSFER_MSG = "어디로 보낼까요? ";
    public static final String SEND_TO_MINUS = "마이너스 통장에 보낼 금액은? ";

    public static String excludeCurrentAccount(int accountNumber) {
        StringBuilder sb = new StringBuilder();
        for (Account account : Main.accounts) {
            if (account.getAccountNumber() != accountNumber + 1) {
                sb.append(
                    String.format("%s: %s ", account.getAccountNumber(),
                        account.getAccountName()));
            }

        }

        return sb.substring(0, sb.toString().length() - 1);
    }
}

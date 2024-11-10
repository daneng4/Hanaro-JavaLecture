package JavaLecture.exam1;

import JavaLecture.InputReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Main {

    private static final String EXIT_CODE = "7";
    private static final String USER_INFO_CODE = "1";
    private static final String CART_INFO_CODE = "2";
    private static final String ADD_PRODUCT_CODE = "3";
    private static final String DELETE_ONE_PRODUCT_CODE = "4";
    private static final String EMPTY_CART_CODE = "5";
    private static final String SHOW_RECEIPT_CODE = "6";
    private static final String REQUIRE_MENU_NUMBER_TEXT = "메뉴 번호를 선택해주세요 ";
    private static final String DEVIDELINE = "--------------------------------------------";
    private static ArrayList<Book> bookData;

    private static User user;
    private static Cart cart;

    public static void main(String[] args) throws IOException {

        saveBookData();
        saveUserData();
        cart = new Cart();
        while (true) {
            showGreetMessage();
            System.out.print(REQUIRE_MENU_NUMBER_TEXT);
            String selectedMenu = InputReader.readInput();
            if (selectedMenu.equals(EXIT_CODE)) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }
            switch (selectedMenu) {
                case USER_INFO_CODE -> {
                    System.out.println("현재 고객 정보: ");
                    System.out.println(user);
                }
                case CART_INFO_CODE -> showCartProcess();
                case ADD_PRODUCT_CODE -> addCartProcess();
                case DELETE_ONE_PRODUCT_CODE -> deleteCartProcess();
                case EMPTY_CART_CODE -> makeEmptyProcess();
                case SHOW_RECEIPT_CODE -> showReceiptProcess();
                default -> System.out.println("유효하지 않은 명령입니다.");
            }
        }

    }

    private static void showGreetMessage() {
        final String GREETMSG = "오늘의 선택, 코난 문고\n영원한 스테디셀러, 명탐정 코난시리즈를 만나보세요~";
        final String MENU = "1. 고객 정보 확인하기 \t 2. 장바구니 상품 목록 보기\n3. 바구니에 항목 추가하기 \t 4. 장바구니의 항목 삭제하기\n5. 장바구니 비우기 \t 6. 영수증 표시하기 \t 7. 종료";

        System.out.println(DEVIDELINE);
        System.out.println(GREETMSG);
        System.out.println(DEVIDELINE);
        System.out.println(MENU);
        System.out.println(DEVIDELINE);
    }

    private static void saveBookData() {
        Book book1 = new Book("ISBN1", "셜록홈즈", "20000원", "코난 도일", "그 누구도 뛰어넘지 못한 추리소설", "추리소설",
            "2018/10/28");
        Book book2 = new Book("ISBN2", "도리안 그레이의 초상", "16000원", "오스카 와일드", "예술을 위한 예술!",
            "고전소설", "2022/01/22");
        Book book3 = new Book("ISBN3", "쥐덫", "27000원", "애거사 크리스티", "폭설 속에 같힌 몽스웰 여관", "추리소설",
            "2019/06/10");

        bookData = new ArrayList<>();
        bookData.add(book1);
        bookData.add(book2);
        bookData.add(book3);
    }

    private static void saveUserData() throws IOException {
        final String getNameText = "당신의 이름을 입력하세요 : ";
        final String getPhoneText = "연락처를 입력하세요 : ";

        System.out.print(getNameText);
        String name = InputReader.readInput();
        System.out.print(getPhoneText);
        String phone = InputReader.readInput();
        user = new User(name, phone);
    }

    private static void showCartProcess() {
        System.out.println(cart);
    }

    private static void addCartProcess() throws IOException {
        for (Book book : bookData) {
            System.out.printf("%s|%s|%s|%s|%s|%s|%s\n", book.getIsbn(), book.getTitle(),
                book.getPrice(), book.getWriter(), book.getBookIntro(), book.getBookType(),
                book.getDate());
        }
        System.out.println(DEVIDELINE);
        System.out.print("장바구니에 추가할 도서의 ID를 입력하세요 : ");
        String inputIsbn = InputReader.readInput();
        boolean isExistIsbn = false;
        for (Book book : bookData) {
            if (book.getIsbn().equals(inputIsbn)) {
                isExistIsbn = true;
                break;
            }
        }
        if (isExistIsbn) {
            System.out.println("장바구니에 추가하겠습니까? Y|N");
        } else {
            System.out.println("해당 ISBN이 존재하지 않습니다.");
            return;
        }
        String yesOrNoInput = InputReader.readInput().toLowerCase();
        if (yesOrNoInput.equals("y")) {
            cart.addByIsbn(inputIsbn, bookData);
        }
    }

    private static void deleteCartProcess() throws IOException {
        System.out.println(cart);
        System.out.println("장바구니에서 삭제할 도서의 ID를 입력하세요 : ");
        String deletedIsbn = InputReader.readInput();
        if (cart.deleteByIsbn(deletedIsbn)) {
            System.out.printf("장바구니에서 %s가 삭제되었습니다\n", deletedIsbn);
        } else {
            System.out.println("해당 ISBN이 존재하지 않습니다.");
        }

    }

    private static void makeEmptyProcess() {
        cart.makeEmptyCart();
    }

    private static void showReceiptProcess() throws IOException {
        System.out.println("배송받을 분은 고객정보와 같습니까?");
        String deliverInfo = InputReader.readInput().toLowerCase();
        if (deliverInfo.equals("y")) {
            System.out.println("배송지를 입력해주세요 ");
        }
        String deliveredLocation = InputReader.readInput();
        System.out.println("--------------------- 배송 받을 고객 정보 ---------------------\n");
        System.out.println(user);
        System.out.printf("배송지 : %s 발송일 : %s\n", deliveredLocation, LocalDate.now());
        System.out.println(cart);
        System.out.printf("전체 총액 : %,d원\n", cart.getTotalPrice());
    }
}

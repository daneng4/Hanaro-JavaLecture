package JavaLecture.exam1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Cart {

    private Map<Book, Integer> myCart;


    public Cart() {
        myCart = new HashMap<>();
    }

    public void addByIsbn(String isbn, ArrayList<Book> bookData) {
        for (Book book : bookData) {
            if (book.getIsbn().equals(isbn)) {
                myCart.put(book, myCart.getOrDefault(book, 0) + 1);
            }
        }
    }

    public boolean deleteByIsbn(String isbn) {
        for (Book book : myCart.keySet()) {
            if (book.getIsbn().equals(isbn)) {
                myCart.remove(book);
                return true;
            }
        }
        return false;
    }

    public void makeEmptyCart() {
        myCart.clear();
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for (Entry<Book, Integer> cart : myCart.entrySet()) {
            totalPrice += Integer.parseInt(cart.getKey().getPrice()) * cart.getValue();
        }
        return totalPrice;
    }

    @Override
    public String toString() {
        String DEVIDELINE = "--------------------------------------------";

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("장바구니 상품 목록 :\n%s\n", DEVIDELINE));
        sb.append("도서ID\t\t\t|\t\t\t수량\t\t\t|\t\t\t합계\n");
        for (Entry<Book, Integer> cart : myCart.entrySet()) {
            sb.append(String.format("%s\t\t\t|\t\t\t%s\t\t\t|\t\t\t%,d원\n", cart.getKey().getIsbn(),
                cart.getValue(),
                Integer.parseInt(cart.getKey().getPrice()) * cart.getValue()));
        }
        return sb.toString();
    }
}

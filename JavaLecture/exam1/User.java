package JavaLecture.exam1;

public class User {

    private String name;
    private String phone;

    public User(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return this.name;
    }

    public String getPhone() {
        return this.phone;
    }

    @Override
    public String toString() {
        return String.format("고객명: %s 연락처: %s", getName(), getPhone());
    }
}

package OOD.DesignPattern.Builder;

public class Test {
    public static void main(String[] args) {
        User user = new User.UserBuilder("hhh", "123")
                .address("123456486")
                .age(12)
                .phone("123456")
                .build();

        User user1 = new User.UserBuilder("hhjjjj", "123")
                .age(12)
                .build();

        System.out.println(user.toString());
        System.out.println(user1.toString());
    }
}

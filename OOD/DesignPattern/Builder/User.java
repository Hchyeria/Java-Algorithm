package OOD.DesignPattern.Builder;

public class User {
    private final String firstName;
    private final String lastName;
    private String address;
    private int age;
    private String phone;

    private User(UserBuilder ub) {
        this.firstName = ub.firstName;
        this.lastName = ub.lastName;
        this.address = ub.address;
        this.age = ub.age;
        this.phone = ub.phone;
    }

    public String toString() {
        return "firstName: " + firstName + "\n" +
                "lastName: " + lastName + "\n" +
                "address: " + address + "\n" +
                "age: " + age + "\n" +
                "phone: " + phone;
    }

    public static class UserBuilder {
        private final String firstName;
        private final String lastName;
        private String address;
        private int age;
        private String phone;

        public UserBuilder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public UserBuilder address(String address) {
            this.address = address;
            return this;
        }

        public UserBuilder age(int age) {
            this.age = age;
            return this;
        }

        public UserBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}

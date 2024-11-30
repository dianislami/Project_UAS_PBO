public class Customer extends Akun{
    public Customer(String id, String username, String password) {
        super(id, username, password);
    }

    @Override
    public void displayRole() {
        System.out.println("Role: Customer");
    }
}

public class Admin extends Akun {
    public Admin(String id, String username, String password) {
        super(id, username, password);
    }

    @Override
    public void displayRole() {
        System.out.println("Role: Admin");
    }
}

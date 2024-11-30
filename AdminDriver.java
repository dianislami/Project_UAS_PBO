public class AdminDriver extends Driver {
    public AdminDriver(String id) {
        super(id);
    }

    @Override
    public void login() {
        System.out.println("Admin dengan ID " + getId() + " berhasil login.");
    }

    @Override
    public void logout() {
        System.out.println("Admin dengan ID " + getId() + " berhasil logout.");
    }
}

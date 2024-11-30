public class CustomerDriver extends Driver {
    public CustomerDriver(String id) {
        super(id);
    }

    @Override
    public void login() {
        System.out.println("Customer dengan ID " + getId() + " berhasil login.");
    }

    @Override
    public void logout() {
        System.out.println("Customer dengan ID " + getId() + " berhasil logout.");
    }
}

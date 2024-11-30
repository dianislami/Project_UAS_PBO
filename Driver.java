import java.util.ArrayList;

public abstract class Driver {
    private String id; // ID unik untuk Driver

    // Constructor
    public Driver(String id) {
        this.id = id;
    }

    // Getter dan Setter untuk ID
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Method abstrak yang harus diimplementasikan oleh subkelas
    public abstract void login();

    public abstract void logout();
}

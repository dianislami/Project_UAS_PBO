public abstract class Akun {
    protected String id;
    protected String username;
    protected String password;

    public Akun(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    // Getter methods
    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public abstract boolean login(String username, String password);
}
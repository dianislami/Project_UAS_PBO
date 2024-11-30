public abstract class Akun {
    private String id;         
    private String username;   
    private String password;   

    public Akun(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    // Getter dan Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Metode untuk validasi login
    public boolean login(String un, String pw) {
        return this.username.equals(un) && this.password.equals(pw);
    }

    // Method untuk logout
    public void logout() {
        System.out.println("Akun dengan ID " + id + " berhasil logout.");
    }

    // Metode abstrak (harus diimplementasikan oleh subclass)
    public abstract void displayRole();
}

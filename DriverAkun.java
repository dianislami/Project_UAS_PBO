import java.util.List;

public class DriverAkun extends Driver {

    // Implementasi login yang memverifikasi username dan password
    @Override
    public Akun login(String username, String password, List<Akun> accounts) {
        // Iterasi melalui daftar akun untuk mencocokkan username dan password
        for (Akun akun : accounts) {
            if (akun.getUsername().equals(username) && akun.getPassword().equals(password)) {
                return akun; // Mengembalikan akun yang cocok
            }
        }
        return null; // Jika akun tidak ditemukan
    }

    @Override
    public void start() {
        // Implementasi metode start jika diperlukan
        System.out.print("");
    }
}

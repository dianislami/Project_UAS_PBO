import java.util.List;

// Metode abstrak untuk memulai proses yang spesifik di subclass
public abstract class Driver {
    public abstract void start(); 
    public abstract Akun login(String username, String password, List<Akun> accounts);
}

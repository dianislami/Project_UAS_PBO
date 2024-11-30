import java.util.List;

public abstract class Driver {
    public abstract void start(); // Metode abstrak yang akan diimplementasikan oleh subclass
    public abstract Akun login(String username, String password, List<Akun> accounts);
}

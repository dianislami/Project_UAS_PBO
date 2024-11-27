public abstract class Driver {
    // Menentukan akun yang digunakan (Customer atau Admin)
    protected Akun akun;

    // Konstruktor untuk menginisialisasi akun
    public Driver(Akun akun) {
        this.akun = akun;
    }

    // Metode abstract untuk memulai proses
    public abstract void start();
}

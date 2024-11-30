public class Barang {
    private String nama;
    private double harga;

    // Constructor
    public Barang(String nama, double harga) {
        this.nama = nama;
        this.harga = harga;
    }

    // Getter untuk nama
    public String getNama() {
        return nama;
    }

    // Setter untuk nama
    public void setNama(String nama) {
        this.nama = nama;
    }

    // Getter untuk harga
    public double getHarga() {
        return harga;
    }

    // Setter untuk harga
    public void setHarga(double harga) {
        this.harga = harga;
    }
}

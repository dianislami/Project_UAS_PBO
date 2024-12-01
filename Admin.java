import java.util.ArrayList;
import java.util.List;

public class Admin extends Akun {
    private List<Barang> products; // Menggunakan Barang sebagai tipe data untuk produk

    public Admin(String id, String username, String password) {
        super(id, username, password);
        this.products = new ArrayList<>();// Inisialisasi daftar produk kosong
    }

    @Override
    public boolean login(String username, String password) {
        // Mengecek apakah username dan password sesuai dengan yang dimiliki admin
        return this.username.equals(username) && this.password.equals(password);
    }

    // Menambahkan barang baru ke dalam daftar produk
    public void addBarang(Barang barang) {
        products.add(barang);
    }

    // Mencari barang berdasarkan ID dan menggantinya dengan barang yang baru
    public void editBarang(String productId, Barang updatedBarang) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getIdBarang().equals(productId)) {
                // Jika ID cocok, barang diubah
                products.set(i, updatedBarang);
                System.out.println("Barang berhasil diubah.");
                return;
            }
        }
        // Jika barang dengan ID tersebut tidak ditemukan
        System.out.println("Barang dengan ID tersebut tidak ditemukan.");
    }

    // Menghapus barang dari daftar berdasarkan ID
    public void removeBarang(String productId) {
        products.removeIf(product -> product.getIdBarang().equals(productId));
        System.out.println("Barang berhasil dihapus.");
    }

    // Menampilkan daftar semua barang yang tersedia
    public void viewBarang() { // Jika tidak ada barang dalam daftar
        if (products.isEmpty()) {
            System.out.println("Tidak ada barang tersedia.");
        } else { // Menampilkan tabel berisi informasi barang
            System.out.println("+----+---------------+---------+-------+");
            System.out.println("| ID | Nama Barang   | Harga   | Stok  |");
            System.out.println("+----+---------------+---------+-------+");
            for (Barang product : products) {
                System.out.printf("| %-2s | %-13s | %-7.0f | %-5d |\n",
                        product.getIdBarang(), product.getNamaBarang(), product.getHargaBarang(), product.getStokBarang());
            }
            System.out.println("+----+---------------+---------+-------+");
        }
    }

    // Mengembalikan daftar barang yang dimiliki admin
    public List<Barang> getProducts() {
        return products;
    }
}

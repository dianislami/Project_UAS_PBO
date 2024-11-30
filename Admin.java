import java.util.ArrayList;
import java.util.List;

public class Admin extends Akun {
    private List<Barang> products; // Menggunakan Barang sebagai tipe data untuk produk

    public Admin(String id, String username, String password) {
        super(id, username, password);
        this.products = new ArrayList<>();
    }

    @Override
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public void addBarang(Barang barang) {
        products.add(barang);
    }

    public void editBarang(String productId, Barang updatedBarang) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getIdBarang().equals(productId)) {
                products.set(i, updatedBarang);
                System.out.println("Barang berhasil diubah.");
                return;
            }
        }
        System.out.println("Barang dengan ID tersebut tidak ditemukan.");
    }

    public void removeBarang(String productId) {
        products.removeIf(product -> product.getIdBarang().equals(productId));
        System.out.println("Barang berhasil dihapus.");
    }

    public void viewBarang() {
        if (products.isEmpty()) {
            System.out.println("Tidak ada barang tersedia.");
        } else {
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

    public List<Barang> getProducts() {
        return products;
    }
}

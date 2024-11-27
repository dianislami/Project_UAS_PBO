import java.util.ArrayList;
import java.util.List;

public class Admin extends Akun {
    private List<Product> products;

    public Admin(String id, String username, String password) {
        super(id, username, password);
        this.products = new ArrayList<>();
    }

    @Override
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public void addProduct(Product product) {
        products.add(product);
        System.out.println("Barang berhasil ditambahkan: " + product);
    }

    public void editProduct(String productId, Product updatedProduct) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(productId)) {
                products.set(i, updatedProduct);
                System.out.println("Barang berhasil diubah.");
                return;
            }
        }
        System.out.println("Barang dengan ID tersebut tidak ditemukan.");
    }

    public void removeProduct(String productId) {
        products.removeIf(product -> product.getId().equals(productId));
        System.out.println("Barang berhasil dihapus.");
    }

    public void viewProducts() {
        if (products.isEmpty()) {
            System.out.println("Tidak ada barang tersedia.");
        } else {
            System.out.println("+----+---------------+---------+-------+");
            System.out.println("| ID | Nama Barang   | Harga   | Stok  |");
            System.out.println("+----+---------------+---------+-------+");
            for (Product product : products) {
                System.out.printf("| %-2s | %-13s | %-7.0f | %-5d |\n",
                        product.getId(), product.getName(), product.getPrice(), product.getStock());
            }
            System.out.println("+----+---------------+---------+-------+");
        }
    }

    public List<Product> getProducts() {
        return products;
    }
}

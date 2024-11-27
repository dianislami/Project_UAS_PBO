import java.util.ArrayList;
import java.util.List;

public class Customer extends Akun {
    private List<CartItem> cart;

    public Customer(String id, String username, String password) {
        super(id, username, password);
        this.cart = new ArrayList<>();
    }

    @Override
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public void viewProducts(List<Product> products) {
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

    public void addToCart(Product product, int quantity) {
        if (product.getStock() >= quantity) {
            product.reduceStock(quantity); // Kurangi stok barang
            cart.add(new CartItem(product, quantity));
            System.out.println(quantity + " unit " + product.getName() + " berhasil ditambahkan ke keranjang.");
        } else {
            System.out.println("Stok tidak mencukupi untuk jumlah yang diminta.");
        }
    }

    public void removeFromCart(String productId) {
        boolean removed = false;
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getProduct().getId().equals(productId)) {
                cart.get(i).getProduct().increaseStock(cart.get(i).getQuantity()); // Kembalikan stok barang
                cart.remove(i); // Hapus barang dari keranjang
                removed = true;
                break;
            }
        }
        if (removed) {
            System.out.println("Barang dengan ID " + productId + " berhasil dihapus dari keranjang.");
        } else {
            System.out.println("Barang dengan ID " + productId + " tidak ditemukan di keranjang.");
        }
    }

    public void checkout() {
        double total = 0;
        for (CartItem item : cart) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }
        System.out.println("Checkout berhasil. Total pembayaran: Rp " + total);
        cart.clear(); // Kosongkan keranjang
    }

    public void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("Keranjang kosong.");
        } else {
            System.out.println("\nIsi Keranjang:");
            for (CartItem item : cart) {
                System.out.println(item);
            }
        }
    }
}

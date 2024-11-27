import java.util.List;
import java.util.Scanner;

public class CustomerDrive {
    private Customer customer;
    private List<Product> allProducts;

    // Constructor to initialize Customer object and all products list
    public CustomerDrive(Customer customer, List<Product> allProducts) {
        this.customer = customer; // Ensure customer is passed correctly
        this.allProducts = allProducts; // List of all products
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nMenu Customer:");
            System.out.println("1. Lihat Barang");
            System.out.println("2. Tambah ke Keranjang");
            System.out.println("3. Lihat Keranjang");
            System.out.println("4. Checkout");
            System.out.println("5. Logout");
            System.out.print("Pilih: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Daftar Barang:");
                    customer.viewProducts(allProducts);  // Pass all products list
                    break;
                case 2:
                    System.out.print("Masukkan ID Barang: ");
                    String addId = scanner.nextLine();
                    Product product = findProductById(addId);
                    if (product != null) {
                        System.out.print("Masukkan jumlah barang: ");
                        int quantity = scanner.nextInt();
                        scanner.nextLine();
                        if (quantity > 0 && quantity <= product.getStock()) {
                            customer.addToCart(product, quantity);
                        } else {
                            System.out.println("Jumlah barang tidak valid atau stok tidak mencukupi.");
                        }
                    } else {
                        System.out.println("Barang tidak ditemukan.");
                    }
                    break;
                case 3:
                    customer.viewCart();
                    break;
                case 4:
                    customer.checkout();
                    break;
                case 5:
                    System.out.println("Logout berhasil.");
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    // Method to find product by ID
    private Product findProductById(String id) {
        for (Product product : allProducts) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }
}

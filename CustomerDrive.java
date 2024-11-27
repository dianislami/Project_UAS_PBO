import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class CustomerDrive {
    private Customer customer;

    // Daftar produk, bisa diambil dari sumber lain seperti Admin atau Database
    private List<Product> allProducts;

    public CustomerDrive(Customer customer) {
        this.customer = customer;
        this.allProducts = generateDummyProducts();  // Memanggil metode untuk mendapatkan produk
    }

    // Contoh metode untuk mendapatkan daftar produk, bisa diganti sesuai kebutuhan
    private List<Product> getAllProducts() {
        return allProducts;
    }

    // Metode untuk membuat produk dummy, atau bisa diambil dari Admin atau database
    private List<Product> generateDummyProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("P001", "Laptop", 10000000, 5));
        products.add(new Product("P002", "Smartphone", 5000000, 10));
        products.add(new Product("P003", "Headphone", 2000000, 15));
        return products;
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
                    customer.viewProducts(getAllProducts());  // Menampilkan daftar produk
                    break;
                case 2:
                    // kode untuk menambah ke keranjang
                    break;
                case 3:
                    customer.viewCart();
                    break;
                case 4:
                    customer.checkout();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }
}

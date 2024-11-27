import java.util.Scanner;

public class AdminDrive {
    private Admin admin;

    // Constructor to initialize Admin object
    public AdminDrive(Admin admin) {
        this.admin = admin; // Ensure admin is passed correctly
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nMenu Admin:");
            System.out.println("1. Tambah Produk");
            System.out.println("2. Hapus Produk");
            System.out.println("3. Edit Produk");
            System.out.println("4. Lihat Semua Produk");
            System.out.println("5. Keluar");
            System.out.print("Pilih: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: // Tambah Produk
                    System.out.print("Masukkan ID Produk: ");
                    String id = scanner.nextLine();
                    System.out.print("Masukkan Nama Produk: ");
                    String name = scanner.nextLine();
                    System.out.print("Masukkan Harga Produk: ");
                    double price = scanner.nextDouble();
                    System.out.print("Masukkan Stok Produk: ");
                    int stock = scanner.nextInt();
                    scanner.nextLine();
                    admin.addProduct(new Product(id, name, price, stock));
                    break;
                case 2: // Hapus Produk
                    System.out.print("Masukkan ID Produk yang ingin dihapus: ");
                    String productId = scanner.nextLine();
                    admin.removeProduct(productId);
                    break;
                case 3: // Edit Produk
                    System.out.print("Masukkan ID Produk yang ingin diubah: ");
                    String editId = scanner.nextLine();
                    System.out.print("Masukkan Nama Baru: ");
                    String newName = scanner.nextLine();
                    System.out.print("Masukkan Harga Baru: ");
                    double newPrice = scanner.nextDouble();
                    System.out.print("Masukkan Stok Baru: ");
                    int newStock = scanner.nextInt();
                    scanner.nextLine();
                    admin.editProduct(editId, new Product(editId, newName, newPrice, newStock));
                    break;
                case 4: // Lihat Semua Produk
                    System.out.println("\nDaftar Produk:");
                    if (admin.getProducts().isEmpty()) {
                        System.out.println("Tidak ada produk tersedia.");
                    } else {
                        System.out.println("+----+---------------+---------+-------+");
                        System.out.println("| ID | Nama Barang   | Harga   | Stok  |");
                        System.out.println("+----+---------------+---------+-------+");
                        for (Product product : admin.getProducts()) {
                            System.out.printf("| %-2s | %-13s | %-7.0f | %-5d |\n",
                                    product.getId(), product.getName(), product.getPrice(), product.getStock());
                        }
                        System.out.println("+----+---------------+---------+-------+");
                    }
                    break;
                case 5: // Keluar
                    System.out.println("Keluar dari menu Admin.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (choice != 5);

        scanner.close();
    }
}

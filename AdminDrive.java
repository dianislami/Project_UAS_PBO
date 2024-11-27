import java.util.Scanner;

public class AdminDrive {
    private Admin admin;

    public AdminDrive(Admin admin) {
        this.admin = admin;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nMenu Admin:");
            System.out.println("1. Tambah Barang");
            System.out.println("2. Edit Barang");
            System.out.println("3. Hapus Barang");
            System.out.println("4. Lihat Barang");
            System.out.println("5. Logout");
            System.out.print("Pilih: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Nama Barang: ");
                    String name = scanner.nextLine();
                    System.out.print("Harga Barang: ");
                    double price = scanner.nextDouble();
                    System.out.print("Stok Barang: ");
                    int stock = scanner.nextInt();
                    admin.addProduct(new Product(name, price, stock));
                }
                case 2 -> {
                    System.out.print("ID Barang: ");
                    String id = scanner.nextLine();
                    System.out.print("Nama Baru: ");
                    String name = scanner.nextLine();
                    System.out.print("Harga Baru: ");
                    double price = scanner.nextDouble();
                    System.out.print("Stok Baru: ");
                    int stock = scanner.nextInt();
                    admin.editProduct(id, new Product(name, price, stock));
                }
                case 3 -> {
                    System.out.print("ID Barang: ");
                    String id = scanner.nextLine();
                    admin.removeProduct(id);
                }
                case 4 -> admin.getProducts().forEach(System.out::println);
                case 5 -> {
                    return;
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }
}

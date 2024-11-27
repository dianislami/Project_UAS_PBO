import java.util.Scanner;

public class CustomerDrive {
    private Customer customer;

    public CustomerDrive(Customer customer) {
        this.customer = customer;
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
                case 1 -> {
                    System.out.println("Daftar Barang:");
                    customer.viewProducts();
                }
                case 2 -> {
                    System.out.print("Nama Barang: ");
                    String name = scanner.nextLine();
                    System.out.print("Jumlah: ");
                    int qty = scanner.nextInt();
                    customer.addToCart(new Product(name, 0, qty), qty);
                }
                case 3 -> customer.viewCart();
                case 4 -> customer.checkout();
                case 5 -> {
                    return;
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }
}

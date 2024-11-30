import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // Membuat akun Customer dan Admin
        Customer customer = new Customer("C001", "customer123", "password123");
        Admin admin = new Admin("A001", "admin123", "adminpassword");

        // Membuat daftar barang
        ListBarang listBarang = new ListBarang();
        listBarang.tambahBarang(new Barang("Laptop", 15000000));
        listBarang.tambahBarang(new Barang("Mouse", 250000));
        listBarang.tambahBarang(new Barang("Keyboard", 500000));

        while (running) {
            System.out.println("\n================================");
            System.out.println("       Sistem Online Shopping   ");
            System.out.println("================================");
            System.out.println("1. Login sebagai Admin");
            System.out.println("2. Login sebagai Customer");
            System.out.println("0. Keluar");
            System.out.print("Pilih opsi: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Membersihkan buffer

            switch (pilihan) {
                case 1:
                    // Login sebagai Admin
                    System.out.print("\n--- Login Admin ---\nUsername: ");
                    String adminUsername = scanner.nextLine();
                    System.out.print("Password: ");
                    String adminPassword = scanner.nextLine();

                    if (admin.login(adminUsername, adminPassword)) {
                        System.out.println("Login berhasil!");
                        adminMode(scanner, listBarang, admin);
                    } else {
                        System.out.println("Login gagal. Username atau password salah.");
                    }
                    break;

                case 2:
                    // Login sebagai Customer
                    System.out.print("\n--- Login Customer ---\nUsername: ");
                    String customerUsername = scanner.nextLine();
                    System.out.print("Password: ");
                    String customerPassword = scanner.nextLine();

                    if (customer.login(customerUsername, customerPassword)) {
                        System.out.println("Login berhasil!");
                        customerMode(scanner, listBarang, customer);
                    } else {
                        System.out.println("Login gagal. Username atau password salah.");
                    }
                    break;

                case 0:
                    // Keluar dari sistem
                    System.out.println("Terima kasih telah menggunakan sistem.");
                    running = false;
                    break;

                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
        scanner.close();
    }

    private static void adminMode(Scanner scanner, ListBarang listBarang, Admin admin) {
        boolean running = true;

        while (running) {
            System.out.println("\n--- Mode Admin ---");
            System.out.println("1. Tambah Barang");
            System.out.println("2. Hapus Barang");
            System.out.println("3. Lihat Daftar Barang");
            System.out.println("0. Logout");
            System.out.print("Pilih opsi: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Membersihkan buffer

            switch (pilihan) {
                case 1:
                    System.out.print("Nama Barang: ");
                    String namaBarang = scanner.nextLine();
                    System.out.print("Harga Barang: ");
                    double hargaBarang = scanner.nextDouble();

                    listBarang.tambahBarang(new Barang(namaBarang, hargaBarang));
                    System.out.println("Barang berhasil ditambahkan.");
                    break;

                case 2:
                    System.out.println("\n--- Daftar Barang ---");
                    listBarang.tampilkanBarang();
                    System.out.print("Masukkan nomor barang yang akan dihapus: ");
                    int index = scanner.nextInt();

                    listBarang.hapusBarang(index - 1);
                    System.out.println("Barang berhasil dihapus.");
                    break;

                case 3:
                    System.out.println("\n--- Daftar Barang ---");
                    listBarang.tampilkanBarang();
                    break;

                case 0:
                    System.out.println("Logout dari mode Admin.");
                    running = false;
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private static void customerMode(Scanner scanner, ListBarang listBarang, Customer customer) {
        Keranjang keranjang = new Keranjang();
        boolean running = true;

        while (running) {
            System.out.println("\n--- Mode Customer ---");
            System.out.println("1. Lihat Daftar Barang");
            System.out.println("2. Tambah Barang ke Keranjang");
            System.out.println("3. Lihat Keranjang");
            System.out.println("4. Checkout dan Bayar");
            System.out.println("0. Logout");
            System.out.print("Pilih opsi: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Membersihkan buffer

            switch (pilihan) {
                case 1:
                    System.out.println("\n--- Daftar Barang ---");
                    listBarang.tampilkanBarang();
                    break;

                case 2:
                    System.out.println("\n--- Daftar Barang ---");
                    listBarang.tampilkanBarang();
                    System.out.print("Masukkan nomor barang untuk ditambahkan ke keranjang: ");
                    int index = scanner.nextInt();

                    Barang barang = listBarang.getBarang().get(index - 1);
                    keranjang.tambahBarang(barang);
                    System.out.println("Barang berhasil ditambahkan ke keranjang.");
                    break;

                case 3:
                    System.out.println("\n--- Isi Keranjang ---");
                    keranjang.tampilkanKeranjang();
                    break;

                case 4:
                    if (keranjang.getBarang().isEmpty()) {
                        System.out.println("Keranjang Anda kosong. Tambahkan barang terlebih dahulu.");
                    } else {
                        Transaksi transaksi = new Transaksi(customer);
                        for (Barang barangKeranjang : keranjang.getBarang()) {
                            transaksi.tambahBarang(barangKeranjang);
                        }
                        Pembayaran pembayaran = new QRIS();
                        Invoice invoice = new Invoice(transaksi, pembayaran);

                        System.out.println("\n--- Detail Invoice ---");
                        invoice.cetakInvoice();

                        System.out.println("\n--- Proses Pembayaran ---");
                        pembayaran.prosesPembayaran(invoice.getTotalBayar());
                        keranjang.getBarang().clear(); // Mengosongkan keranjang setelah pembayaran
                    }
                    break;

                case 0:
                    System.out.println("Logout dari mode Customer.");
                    running = false;
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }
}

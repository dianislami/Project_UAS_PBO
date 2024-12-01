import java.util.List;
import java.util.Scanner;

public class CustomerDrive extends Driver {
    private Customer customer;
    private Transaksi transaksi; // Add transaksi instance
    private ListBarang listBarang;
    private Invoice invoice;

    public CustomerDrive(Customer customer, ListBarang listBarang) {
        this.customer = customer;
        this.listBarang = listBarang;
        this.transaksi = new Transaksi(customer);
        listBarang.muatDataDariFile();
    }

    @Override
    public Customer login(String username, String password, List<Akun> accounts) {
        // Proses login untuk customer
        for (Akun akun : accounts) {
            if (akun instanceof Customer && akun.getUsername().equals(username) && akun.getPassword().equals(password)) {
                return (Customer) akun;
            }
        }
        return null; // Jika login gagal
    }

    @Override
    public void start() {
        // Menu utama untuk customer
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nMenu Customer:");
            System.out.println("1. Lihat Barang");
            System.out.println("2. Tambah ke Keranjang");
            System.out.println("3. Hapus Barang dari Keranjang");
            System.out.println("4. Lihat Keranjang");
            System.out.println("5. Checkout");
            System.out.println("6. Lihat Riwayat Transaksi");
            System.out.println("7. Logout");
            System.out.print("Pilih: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> { // Menampilkan daftar barang yang tersedia
                    System.out.println("Daftar Barang:");
                    listBarang.muatDataDariFile();
                    customer.viewBarang(listBarang.getBarang());
                }
                case 2 -> { // Menambahkan barang ke keranjang
                    System.out.print("Masukkan ID Barang: ");
                    String idBarang = scanner.nextLine();
                    Barang barang = listBarang.cariBarang(idBarang); // Cari barang di ListBarang
                    if (barang != null) {
                        System.out.print("Masukkan jumlah: ");
                        int jumlah = scanner.nextInt();
                        scanner.nextLine();
                        if (jumlah > 0 && jumlah <= barang.getStokBarang()) {
                            customer.addToCart(barang, jumlah); // Tambahkan barang ke keranjang
                            transaksi.tambahBarang(barang, jumlah); // Tambahkan ke transaksi
                            listBarang.simpanDataKeFile(); // Kurangi stok di ListBarang
                        } else {
                            System.out.println("Jumlah barang tidak valid atau stok tidak mencukupi.");
                        }
                    } else {
                        System.out.println("Barang tidak ditemukan.");
                    }
                }
                case 3 -> { // Menghapus barang dari keranjang
                    System.out.print("Masukkan ID Barang yang akan dihapus: ");
                    String idBarang = scanner.nextLine();
                    customer.removeFromCart(idBarang); // Hapus barang dari keranjang
                }
                case 4 -> { // Menampilkan isi keranjang
                    System.out.println("Isi Keranjang:");
                    customer.viewCart(); // Tampilkan isi keranjang
                }
                case 5 -> { // Melakukan proses checkout
                    System.out.println("Melakukan Checkout...");
                    customer.checkout(); // Lakukan proses checkout
                    listBarang.simpanDataKeFile(); // Print transaction details
                    // Here you can add the payment option to complete the transaction
                }
                case 6 -> { // Menampilkan riwayat transaksi
                    System.out.println("Riwayat Transaksi:");
                    invoice.bacaDariFile("C:\\Users\\asusa\\OneDrive\\Documents\\java\\uas\\customer\\" + transaksi.getAkun().getUsername() + "\\transaksi.txt");
                }
                case 7 -> { // Keluar dari menu customer
                    System.out.println("Logout berhasil.");
                    return;
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }
}

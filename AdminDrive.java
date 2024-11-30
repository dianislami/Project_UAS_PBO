import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminDrive extends Driver {
    private Admin admin;
    private ListBarang listBarang;
    private List<Transaksi> listTransaksi;
    private Invoice invoice;

    public AdminDrive(Admin admin) {
        this.admin = admin;
        this.listBarang = new ListBarang(); // Inisialisasi ListBarang
        this.listTransaksi = new ArrayList<>();
    }

    @Override
    public Admin login(String username, String password, List<Akun> accounts) {
        for (Akun akun : accounts) {
            if (akun instanceof Admin && akun.getUsername().equals(username) && akun.getPassword().equals(password)) {
                return (Admin) akun;
            }
        }
        return null; // Jika login gagal
    }

    @Override
    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nMenu Admin:");
            System.out.println("1. Tambah Barang");
            System.out.println("2. Edit Barang");
            System.out.println("3. Hapus Barang");
            System.out.println("4. Lihat Barang");
            System.out.println("5. Lihat Transaksi");
            System.out.println("6. Logout");
            System.out.print("Pilih: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Bersihkan buffer

            switch (choice) {
                case 1:
                    System.out.print("ID Barang: ");
                    String id = scanner.nextLine();
                    System.out.print("Nama Barang: ");
                    String name = scanner.nextLine();
                    System.out.print("Harga Barang: ");
                    double price = scanner.nextDouble();
                    System.out.print("Stok Barang: ");
                    int stock = scanner.nextInt();
                    listBarang.tambahBarang(new Barang(id, name, price, stock));
                    Barang.simpanDataBarang(listBarang.barang);
                    break;
                case 2:
                    System.out.print("ID Barang yang ingin diedit: ");
                    String idBarang = scanner.nextLine();
                    Barang barang = listBarang.cariBarang(idBarang);
                    if (barang != null) {
                        System.out.print("Nama Baru: ");
                        String namaBaru = scanner.nextLine();
                        System.out.print("Harga Baru: ");
                        double hargaBaru = scanner.nextDouble();
                        System.out.print("Stok Baru: ");
                        int stokBaru = scanner.nextInt();
                        scanner.nextLine(); // Clear buffer
                        barang.setNamaBarang(namaBaru);
                        barang.setHargaBarang(hargaBaru);
                        barang.setStokBarang(stokBaru);
                        System.out.println("Barang berhasil diedit.");
                        Barang.simpanDataBarang(listBarang.barang);
                    }
                    break;
                case 3:
                    System.out.print("ID Barang yang ingin dihapus: ");
                    String idBarangToDelete = scanner.nextLine();
                    listBarang.hapusBarang(idBarangToDelete);
                    Barang.simpanDataBarang(listBarang.barang);
                    break;
                case 4:
                    System.out.println("Daftar Barang:");
                    listBarang.tampilkanSemuaBarang();
                    break;
                case 5:
                    System.out.println("Riwayat Transaksi:");
                    invoice.bacaDariFile("invoices.txt");
                    break;
                case 6:
                    // Logout
                    System.out.println("Logout berhasil.");
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }
        }
    }
}

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Customer extends Akun {
    private Keranjang keranjang;
    private ArrayList<Invoice> invoiceSelesai;
    private static final String FILE_NAME = "invoices.txt";

    public Customer(String id, String username, String password) {
        super(id, username, password);
        this.keranjang = new Keranjang();
        this.invoiceSelesai = new ArrayList<>();
    }

    @Override
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public void viewBarang(List<Barang> barangList) {
        if (barangList.isEmpty()) {
            System.out.println("Tidak ada barang tersedia.");
        } else {
            System.out.println("+----+---------------+---------+-------+");
            System.out.println("| ID | Nama Barang   | Harga   | Stok  |");
            System.out.println("+----+---------------+---------+-------+");
            for (Barang barang : barangList) {
                System.out.printf("| %-2s | %-13s | %-7.0f | %-5d |\n",
                        barang.getIdBarang(), barang.getNamaBarang(), barang.getHargaBarang(), barang.getStokBarang());
            }
            System.out.println("+----+---------------+---------+-------+");
        }
    }

    public void addToCart(Barang barang, int quantity) {
        keranjang.tambahBarang(barang, quantity);
    }

    public void removeFromCart(String id) {
        keranjang.hapusBarang(id);
    }

    public void viewCart() {
        keranjang.tampilkanBarang();
    }

    public void checkout() {
        if (keranjang.getBarang().isEmpty()) {
            System.out.println("Keranjang kosong. Tidak dapat melakukan checkout.");
            return;
        }

        keranjang.tampilkanBarang();

        // Minta pengguna memilih barang yang ingin di-checkout
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPilih barang yang ingin di-checkout (pisahkan dengan koma, contoh: 1,3):");
        System.out.print("Pilihan Anda: ");
        String pilihanInput = scanner.nextLine();
        String[] pilihanArray = pilihanInput.split(",");

        // Validasi input pengguna
        List<Barang> barangUntukCheckout = new ArrayList<>();
        for (String id : pilihanArray) {
            Barang barangDipilih = null;
            for (Barang item : keranjang.getBarang()) {
                if (item.getIdBarang().equalsIgnoreCase(id.trim())) {
                    barangDipilih = item;
                    break;
                }
            }
            if (barangDipilih != null) {
                barangUntukCheckout.add(barangDipilih);
            } else {
                System.out.println("Barang dengan ID " + id.trim() + " tidak ditemukan di keranjang. Dilewati.");
            }
        }

        // Cek apakah ada barang yang dipilih
        if (barangUntukCheckout.isEmpty()) {
            System.out.println("Tidak ada barang yang dipilih untuk checkout. Proses dibatalkan.");
            return;
        }

        // Hitung total pembayaran
        double total = 0;
        for (Barang item : barangUntukCheckout) {
            total += item.getHargaBarang() * item.getStokBarang();
        }

        // Meminta pengguna memilih metode pembayaran
        System.out.println("\nPilih metode pembayaran:");
        System.out.println("1. QRIS");
        System.out.println("2. COD");
        System.out.println("3. Bank");
        System.out.print("Pilihan Anda: ");
        int pilihan = scanner.nextInt();
        scanner.nextLine(); // Konsumsi newline

        Pembayaran pembayaran = null;
        switch (pilihan) {
            case 1:
                pembayaran = new QRIS("QRIS-" + System.currentTimeMillis()); // Generate ID unik
                break;
            case 2:
                pembayaran = new COD("COD-" + System.currentTimeMillis());
                break;
            case 3:
                pembayaran = new Bank("BANK-" + System.currentTimeMillis());
                break;
            default:
                System.out.println("Metode pembayaran tidak valid. Checkout dibatalkan.");
                return;
        }

        // Buat invoice
        Transaksi transaksi = new Transaksi(this);
        for (Barang item : barangUntukCheckout) {
            transaksi.tambahBarang(item, item.getStokBarang());
        }

        Invoice invoice = new Invoice(transaksi, pembayaran);

        // Cetak invoice dan proses pembayaran
        invoice.cetakInvoice();
        pembayaran.prosesPembayaran();

        // Tambahkan ke daftar transaksi selesai
        invoiceSelesai.add(invoice);

        // Kosongkan keranjang
        keranjang.getBarang().removeAll(barangUntukCheckout);
        keranjang.simpanKeFile();
        System.out.println("\nCheckout berhasil. Keranjang telah dikosongkan.");
    }
}

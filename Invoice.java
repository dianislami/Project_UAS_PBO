import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Invoice {
    private Transaksi transaksi;
    private Pembayaran pembayaran; // Metode pembayaran yang digunakan
    private double totalBayar; // Total yang harus dibayar
    private static int nomorTransaksiCounter = 1; // Counter untuk nomor transaksi unik
    private int nomorTransaksi;
    private String tanggalWaktu;

    // Constructor
    public Invoice(Transaksi transaksi, Pembayaran pembayaran, double totalBayar) {
        if (transaksi == null || pembayaran == null) {
            throw new IllegalArgumentException("Transaksi dan Pembayaran tidak boleh null.");
        }
        this.transaksi = transaksi;
        this.pembayaran = pembayaran;
        this.totalBayar = totalBayar; // Menggunakan total yang diberikan
        this.nomorTransaksi = nomorTransaksiCounter++; // Nomor transaksi unik
        this.tanggalWaktu = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    // Getter untuk metode pembayaran
    public Pembayaran getPembayaran() {
        return pembayaran;
    }

    // Getter untuk total bayar
    public double getTotalBayar() {
        return totalBayar;
    }

    // Method untuk mencetak detail invoice
    public void cetakInvoice() {
        System.out.println("======== INVOICE ========");

        // Validasi jika transaksi tidak memiliki barang
        if (transaksi.getBarang().isEmpty()) {
            System.out.println("Transaksi tidak memiliki barang.");
            return;
        }

        // Tampilkan informasi di konsol
        System.out.println("Nomor Transaksi: T-" + nomorTransaksi);
        System.out.println("Tanggal dan Waktu: " + tanggalWaktu);
        System.out.println("Customer: " + transaksi.getAkun().getId());
        System.out.println("Daftar Barang:");
        for (Barang barang : transaksi.getBarang()) {
            System.out.println("- " + barang.getNamaBarang() + " | Harga: Rp" + barang.getHargaBarang());
        }

        System.out.println("Total Bayar: Rp" + totalBayar);
        System.out.println("Metode Pembayaran: " + pembayaran.getJenisPembayaran());
        System.out.println("=========================");

        simpanKeFile();
    }

    // Method untuk menyimpan invoice ke file txt dengan timestamp
    private void simpanKeFile() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String fileName = "invoices_" + timestamp + ".txt";  // Nama file dengan timestamp
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("======== INVOICE ========"); 
            writer.newLine();
            writer.write("Nomor Transaksi: T-" + nomorTransaksi);
            writer.newLine();
            writer.write("Tanggal dan Waktu: " + tanggalWaktu);
            writer.newLine();
            writer.write("Customer: " + transaksi.getAkun().getId());
            writer.newLine();
            writer.write("Daftar Barang:");
            writer.newLine();
            for (Barang barang : transaksi.getBarang()) {
                writer.write("- " + barang.getNamaBarang() + " | Harga: Rp" + barang.getHargaBarang());
                writer.newLine();
            }
            writer.write("Total Bayar: Rp" + totalBayar);
            writer.newLine();
            writer.write("Metode Pembayaran: " + pembayaran.getJenisPembayaran());
            writer.newLine();
            writer.write("=========================");
            writer.newLine();

            System.out.println("Invoice berhasil disimpan ke " + fileName);
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menyimpan invoice ke file: " + e.getMessage());
        }
    }

    // Method untuk membaca invoice dari file
    public static void bacaDariFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);  // Menampilkan baris-baris file
            }
        } catch (IOException e) {
            System.out.println("Belum ada transaksi yang tersimpan");
        }
    }

    // Apply discount method (Optional feature)
    public void applyDiscount(double discountPercentage) {
        if (discountPercentage < 0 || discountPercentage > 100) {
            System.out.println("Diskon harus antara 0% hingga 100%.");
            return;
        }
        double discountAmount = (totalBayar * discountPercentage) / 100;
        totalBayar -= discountAmount;
        System.out.println("Diskon diterapkan: Rp" + discountAmount);
        System.out.println("Total bayar setelah diskon: Rp" + totalBayar);
    }
}

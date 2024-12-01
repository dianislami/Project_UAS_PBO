import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Invoice {
    private static final String fileNo = "no_transaksi.txt";
    private Transaksi transaksi;
    private Pembayaran pembayaran; // Metode pembayaran yang digunakan
    private int nomorTransaksi = 101; // Nomor transaksi unik
    private String tanggalWaktu; //Menampilkan tanggal dan waktu teransaksi dibuat

    // Constructor
    public Invoice(Transaksi transaksi, Pembayaran pembayaran) {
        if (transaksi == null || pembayaran == null) {
            throw new IllegalArgumentException("Transaksi dan Pembayaran tidak boleh null.");
        }
        this.transaksi = transaksi;
        this.pembayaran = pembayaran;
        this.nomorTransaksi = getNextTransactionNumber(); // Nomor transaksi unik
        this.tanggalWaktu = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    // Getter untuk metode pembayaran
    public Pembayaran getPembayaran() {
        return pembayaran;
    }

    // Method untuk mendapatkan nomor transaksi berikutnya
    private static int getNextTransactionNumber() {
        int currentNumber = 100; // Default awal jika file tidak ada

        File file = new File(fileNo);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line = reader.readLine();
                if (line != null) {
                    currentNumber = Integer.parseInt(line.trim());
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Gagal membaca nomor transaksi. Menggunakan nomor default.");
            }
        }

        // Increment nomor transaksi
        int nextNumber = currentNumber + 1;

        // Simpan nomor transaksi yang diperbarui ke file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(String.valueOf(nextNumber));
        } catch (IOException e) {
            System.out.println("Gagal menyimpan nomor transaksi ke file.");
        }

        return currentNumber;
    }
    
    // Method untuk mencetak detail invoice
    public void cetakInvoice() {
        // Validasi jika transaksi tidak memiliki barang
        if (transaksi.getBarang().isEmpty()) {
            System.out.println("Transaksi tidak memiliki barang.");
            return;
        }

        // Header Invoice
        String header = "============INVOICE TRANSAKSI============";
        int width = 40;
        System.out.println(String.format("%" + ((width + header.length()) / 2) + "s", header));
        System.out.println();

        // Informasi utama transaksi
        System.out.printf("%-20s: %-20s\n", "Nomor Transaksi", "T01-" + nomorTransaksi);
        System.out.printf("%-20s: %-20s\n", "Tanggal dan Waktu", tanggalWaktu);
        System.out.printf("%-20s: %-20s\n", "Customer", transaksi.getAkun().getId());
        System.out.println("--------------------+--------------------");
        
        // Daftar Barang
        System.out.printf("%-20s| %-10s\n", "Nama Barang", "Harga");
        System.out.println("--------------------+--------------------");
        for (Barang barang : transaksi.getBarang()) {
            System.out.printf("%-20s| Rp%-10.0f\n", barang.getNamaBarang(), barang.getHargaBarang());
        }

        // Total bayar dan metode pembayaran
        System.out.println("--------------------+--------------------");
        System.out.printf("%-20s: Rp%-10.0f\n", "Total Bayar", transaksi.hitungTotal());
        System.out.printf("%-20s: %-10s\n", "Metode Pembayaran", pembayaran.getJenisPembayaran());
        System.out.println("=========================================");

        simpanInvoice();
    }

    private void simpanInvoice() {
        // Daftar lokasi file tujuan
        String[] fileNames = {
            "invoices.txt", // File global untuk admin
            "C:\\Users\\asusa\\OneDrive\\Documents\\java\\uas\\customer\\" + transaksi.getAkun().getUsername() + "\\transaksi.txt" // File spesifik untuk customer
        };
    
        try {
            for (String fileName : fileNames) {
                simpanKeFile(fileName); // true = append mode
            }
            System.out.println("Invoice berhasil disimpan ke kedua file.");
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menyimpan invoice ke file: " + e.getMessage());
        }
    }

    // Method untuk menyimpan invoice ke file txt
    private void simpanKeFile(String fileName) throws IOException {
        File file = new File(fileName);
 
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {

            // Header Invoice
            String header = "============INVOICE TRANSAKSI============";
            int width = 40;
            writer.write(String.format("%" + ((width + header.length()) / 2) + "s", header));
            writer.newLine();

            // Informasi utama transaksi
            writer.write(String.format("%-20s: %-20s", "Nomor Transaksi", "T-" + nomorTransaksi));
            writer.newLine();
            writer.write(String.format("%-20s: %-20s", "Tanggal dan Waktu", tanggalWaktu));
            writer.newLine();
            writer.write(String.format("%-20s: %-20s", "Customer", transaksi.getAkun().getId()));
            writer.newLine();
            writer.write("--------------------+--------------------\n");
            
            // Daftar Barang
            writer.write(String.format("%-20s| %-10s", "Nama Barang", "Harga"));
            writer.newLine();
            writer.write("--------------------+--------------------");
            writer.newLine();
            for (Barang barang : transaksi.getBarang()) {
                writer.write(String.format("%-20s| Rp%-10.0f", barang.getNamaBarang(), barang.getHargaBarang()));
                writer.newLine();
            }

            // Total bayar dan metode pembayaran
            writer.write("--------------------+--------------------");
            writer.newLine();
            writer.write(String.format("%-20s: Rp%-10.0f", "Total Bayar", transaksi.hitungTotal()));
            writer.newLine();
            writer.write(String.format("%-20s: %-10s", "Metode Pembayaran", pembayaran.getJenisPembayaran()));
            writer.newLine();

            // Footer Invoice
            writer.write("=========================================\n");
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menyimpan invoice ke file: " + e.getMessage());
        }
    }

    // Method untuk membaca invoice dari file
    public static void bacaDariFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists() || file.length() == 0) { // Cek jika file tidak ada atau kosong
            System.out.println("Belum ada transaksi yang tersimpan.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);  // Menampilkan baris-baris file
            }
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat membaca file");
        }
    }
}

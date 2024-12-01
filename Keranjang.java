import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Keranjang {
    private ArrayList<Barang> barang;
    private String customerUsername;
    private double diskon;

    // Constructor untuk membuat keranjang berdasarkan username customer
    public Keranjang(String customerUsername) {
        this.customerUsername = customerUsername;
        this.barang = new ArrayList<>();
        this.diskon = 0;
        muatDariFile(); // Muat data keranjang dari file saat objek dibuat
    }

    // Method untuk menetapkan diskon
    public void setDiskon(double diskon) {
        if (diskon >= 0 && diskon <= 100) {
            this.diskon = diskon;
            System.out.println("Diskon sebesar " + diskon + "% berhasil diterapkan.");
        } else {
            System.out.println("Diskon harus antara 0% dan 100%.");
        }
    }

    // Method untuk menambahkan barang ke keranjang
    public void tambahBarang(Barang item, int jumlah) {
        // Cek apakah barang sudah ada di keranjang
        for (Barang b : barang) {
            if (b.getIdBarang().equals(item.getIdBarang())) {
                // Jika ada, tambahkan jumlah barang yang ada
                item.kurangiStok(jumlah); // Kurangi stok barang di inventori
                b.tambahStok(jumlah); // Tambahkan stok barang di keranjang
                System.out.println(jumlah + " unit " + item.getNamaBarang() + " ditambahkan ke keranjang. Total jumlah: " + b.getStokBarang());
                simpanKeFile(); // Simpan data keranjang ke file
                return;
            }
        }
        if (item.kurangiStok(jumlah)) { // Kurangi stok jika cukup
            barang.add(new Barang(item.getIdBarang(), item.getNamaBarang(), item.getHargaBarang(), jumlah));
            System.out.println(jumlah + " unit " + item.getNamaBarang() + " ditambahkan ke keranjang.");
            simpanKeFile(); // Simpan data keranjang ke file
        } else {
            System.out.println("Gagal menambahkan barang ke keranjang. Stok tidak mencukupi.");
        }

    }

    // Method untuk menghapus barang dari keranjang
    public void hapusBarang(String id) {
        for (Barang b : barang) {
            if (b.getIdBarang().equals(id)) {
                // Cari barang yang cocok di daftar barang utama untuk menambah stok kembali
                List<Barang> daftarBarangUtama = Barang.bacaDataBarang(); // Baca data dari barang.txt
                for (Barang barangUtama : daftarBarangUtama) {
                    if (barangUtama.getIdBarang().equals(id)) {
                        barangUtama.tambahStok(b.getStokBarang()); // Tambahkan stok barang yang dihapus
                        Barang.simpanDataBarang(daftarBarangUtama); // Simpan kembali daftar barang ke file
                        break;
                    }
                }
                barang.remove(b); // Hapus barang dari keranjang
                System.out.println(b.getNamaBarang() + " dihapus dari keranjang.");
                simpanKeFile(); // Simpan perubahan ke file
                return;
            }
        }
        System.out.println("Barang dengan ID " + id + " tidak ditemukan di keranjang.");
    }

    // Method untuk menghitung total harga barang dalam keranjang (termasuk diskon)
    public double hitungTotal() {
        double total = 0;
        for (Barang item : barang) {
            total += item.getHargaBarang() * item.getStokBarang(); // Hitung total harga berdasarkan jumlah barang
        }
        return total - (total * diskon / 100); // Kurangi dengan diskon
    }

    // Method untuk menampilkan total harga setelah diskon
    public void tampilkanTotal() {
        System.out.println("Total harga setelah diskon: Rp" + hitungTotal());
    }

    // Method untuk menampilkan barang dalam keranjang
    public void tampilkanBarang() {
        if (barang.isEmpty()) {
            System.out.println("Keranjang kosong.");
        } else {
            System.out.println("Daftar barang dalam keranjang:");
            for (Barang item : barang) {
                System.out.println(item.getIdBarang() + "| " + item.getNamaBarang() + " | Harga: Rp" + item.getHargaBarang() +
                                   " | Jumlah: " + item.getStokBarang());
            }
        }
    }

    // Getter untuk mendapatkan daftar barang dalam keranjang
    public ArrayList<Barang> getBarang() {
        return barang;
    }

    // Getter untuk mendapatkan diskon yang diterapkan
    public double getDiskon() {
        return this.diskon;
    }

    // Method untuk menyimpan keranjang ke file
    public void simpanKeFile() {
        File fileKeranjang = new File("C:\\Users\\asusa\\OneDrive\\Documents\\java\\uas\\customer\\" + customerUsername + "\\keranjang.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileKeranjang))) {
            for (Barang item : barang) {
                writer.write(item.getIdBarang() + "," +
                             item.getNamaBarang() + "," +
                             item.getHargaBarang() + "," +
                             item.getStokBarang());
                writer.newLine();
            }
            System.out.println("Data keranjang berhasil disimpan ke keranjang.txt");
        } catch (IOException e) {
            System.out.println("Gagal menyimpan data keranjang: " + e.getMessage());
        }
    }

    // Method untuk memuat data keranjang dari file
    public void muatDariFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\asusa\\OneDrive\\Documents\\java\\uas\\customer\\" + customerUsername + "\\keranjang.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    String id = data[0];
                    String nama = data[1];
                    double harga = Double.parseDouble(data[2]);
                    int stok = Integer.parseInt(data[3]);
                    barang.add(new Barang(id, nama, harga, stok));
                }
            }
            System.out.println("Data keranjang berhasil dimuat dari file.");
        } catch (FileNotFoundException e) {
            System.out.println("File keranjang.txt tidak ditemukan. Memulai dengan keranjang kosong.");
        } catch (IOException e) {
            System.out.println("Gagal membaca data keranjang: " + e.getMessage());
        }
    }
}

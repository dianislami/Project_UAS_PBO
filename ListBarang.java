import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ListBarang {
    public ArrayList<Barang> barang; // Daftar barang
    private static final String FILE_NAME = "barang.txt";

    // Constructor
    public ListBarang() {
        this.barang = new ArrayList<>();
        muatDataDariFile();
    }

    public ArrayList<Barang> getBarang() {
        return barang;
    }

    // Metode untuk menambahkan barang ke daftar
    public void tambahBarang(Barang barangBaru) {
        barang.add(barangBaru);
        simpanDataKeFile();
        System.out.println("Barang berhasil ditambahkan");
    }

    // Metode untuk menghapus barang berdasarkan ID
    public void hapusBarang(String idBarang) {
        boolean ditemukan = false;
        for (int i = 0; i < barang.size(); i++) {
            if (barang.get(i).getIdBarang().equals(idBarang)) {
                System.out.println("Barang berhasil dihapus: " + barang.get(i).getNamaBarang());
                barang.remove(i);
                simpanDataKeFile();
                ditemukan = true;
                break;
            }
        }
        if (!ditemukan) {
            System.out.println("Barang dengan ID " + idBarang + " tidak ditemukan.");
        }
    }

    // Metode untuk mencari barang berdasarkan ID
    public Barang cariBarang(String idBarang) {
        for (Barang item : barang) {
            if (item.getIdBarang().equals(idBarang)) {
                return item;
            }
        }
        System.out.println("Barang dengan ID " + idBarang + " tidak ditemukan.");
        return null;
    }

    // Metode untuk menampilkan seluruh daftar barang
    public void tampilkanSemuaBarang() {
        muatDataDariFile();
        if (barang.isEmpty()) {
            System.out.println("Tidak ada barang dalam daftar.");
        } else {
            System.out.println("+------+-------------------------+--------------+----------+");
            System.out.println("|  ID  |       Nama Barang       |     Harga    |   Stok   |");
            System.out.println("+------+-------------------------+--------------+----------+");
            for (Barang item : barang) {
                System.out.printf("| %-4s | %-23s | %-12.0f | %-8d |\n",
                        item.getIdBarang(), item.getNamaBarang(), item.getHargaBarang(), item.getStokBarang());
            }
            System.out.println("+------+-------------------------+--------------+----------+");
        }
    }

    // Metode untuk memuat data dari file ke daftar barang
    public void muatDataDariFile() {
        barang.clear(); // Bersihkan daftar barang sebelum memuat ulang
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    String idBarang = data[0];
                    String namaBarang = data[1];
                    double hargaBarang = Double.parseDouble(data[2]);
                    int stokBarang = Integer.parseInt(data[3]);
                    barang.add(new Barang(idBarang, namaBarang, hargaBarang, stokBarang));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan. Tidak ada data yang dimuat.");
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat membaca file: " + e.getMessage());
        }
    }

    // Metode untuk menyimpan data barang ke file
    public void simpanDataKeFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Barang item : barang) {
                writer.write(item.getIdBarang() + "," +
                             item.getNamaBarang() + "," +
                             item.getHargaBarang() + "," +
                             item.getStokBarang());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menyimpan file: " + e.getMessage());
        }
    }
}


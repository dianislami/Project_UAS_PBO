import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Barang {
    private String idBarang;   
    private String namaBarang;  
    private double hargaBarang;
    private int stokBarang;  

    // Constructor
    public Barang(String idBarang, String namaBarang, double hargaBarang, int stokBarang) {
        this.idBarang = idBarang;
        this.namaBarang = namaBarang;
        this.hargaBarang = hargaBarang;
        this.stokBarang = stokBarang;
    }

    // Getters and Setters
    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public double getHargaBarang() {
        return hargaBarang;
    }

    public void setHargaBarang(double hargaBarang) {
        this.hargaBarang = hargaBarang;
    }

    public int getStokBarang() {
        return stokBarang;
    }

    public void setStokBarang(int stokBarang) {
        this.stokBarang = stokBarang;
    }

    public void tampilkanInfoBarang() {
        System.out.println("ID Barang     : " + idBarang);
        System.out.println("Nama Barang   : " + namaBarang);
        System.out.println("Harga Barang  : Rp" + hargaBarang);
        System.out.println("Stok Barang   : " + stokBarang);
    }

    // Metode untuk mengurangi stok barang
    public boolean kurangiStok(int jumlah) {
        if (stokBarang >= jumlah) {
            stokBarang -= jumlah;
            return true;
        } else {
            System.out.println("Stok tidak mencukupi untuk barang: " + namaBarang);
            return false;
        }
    }

    // Metode untuk menambah stok barang
    public void tambahStok(int jumlah) {
        if (jumlah > 0) {
            stokBarang += jumlah;
        } else {
            System.out.println("Jumlah stok yang ditambahkan harus lebih dari 0.");
        }
    }

    // Metode untuk menyimpan data barang ke file
    public static void simpanKeFile(List<Barang> daftarBarang, String namaFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(namaFile))) {
            for (Barang barang : daftarBarang) {
                writer.write(barang.idBarang + "," + barang.namaBarang + "," + barang.hargaBarang + "," + barang.stokBarang);
                writer.newLine();
            }
            System.out.println("Data berhasil disimpan ke file: " + namaFile);
        } catch (IOException e) {
            System.out.println("Gagal menyimpan data ke file: " + e.getMessage());
        }
    }

    // Metode untuk membaca data barang dari file
    public static List<Barang> bacaDariFile(String namaFile) {
        List<Barang> daftarBarang = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(namaFile))) {
            String baris;
            while ((baris = reader.readLine()) != null) {
                String[] data = baris.split(",");
                if (data.length == 4) {
                    String id = data[0];
                    String nama = data[1];
                    double harga = Double.parseDouble(data[2]);
                    int stok = Integer.parseInt(data[3]);
                    daftarBarang.add(new Barang(id, nama, harga, stok));
                }
            }
        } catch (IOException e) {
            System.out.println("Gagal membaca data dari file: " + e.getMessage());
        }
        return daftarBarang;
    }
}

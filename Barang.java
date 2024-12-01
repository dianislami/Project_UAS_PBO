import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Barang {
    private String idBarang;   
    private String namaBarang;  
    private double hargaBarang;
    private int stokBarang; 
    private static final String FILE_NAME = "barang.txt"; 

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

    // Menampilkan informasi detail barang
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

            // Simpan perubahan stok ke file
            List<Barang> daftarBarang = bacaDataBarang();
            for (Barang barang : daftarBarang) {
                if (barang.getIdBarang().equals(this.idBarang)) {
                    barang.setStokBarang(this.stokBarang); // Perbarui stok di daftar
                    break;
                }
            }
            simpanDataBarang(daftarBarang);
            return true;
        } else {
            System.out.println("Stok tidak mencukupi untuk barang: " + namaBarang);
            return false;
        }
    }

    // Metode untuk menambah stok barang
    public void tambahStok(int jumlah) {
        // Menambahkan stok barang jika jumlah yang diberikan valid
        if (jumlah > 0) {
            stokBarang += jumlah;

            // Simpan perubahan stok ke file
            List<Barang> daftarBarang = bacaDataBarang();
            for (Barang barang : daftarBarang) {
                if (barang.getIdBarang().equals(this.idBarang)) {
                    barang.setStokBarang(this.stokBarang); // Perbarui stok di daftar
                    break;
                }
            }
            simpanDataBarang(daftarBarang);
        } else {
            System.out.println("Jumlah stok yang ditambahkan harus lebih dari 0.");
        }
    }

    // Menyimpan daftar barang ke file
    public static void simpanDataBarang(List<Barang> daftarBarang) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Barang barang : daftarBarang) {
                writer.write(barang.getIdBarang() + "," +
                             barang.getNamaBarang() + "," +
                             barang.getHargaBarang() + "," +
                             barang.getStokBarang());
                writer.newLine();
            }
            System.out.println("Data barang berhasil disimpan ke file.");
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menyimpan data barang: " + e.getMessage());
        }
    }

    // Membaca daftar barang dari file
    public static List<Barang> bacaDataBarang() {
        List<Barang> daftarBarang = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    String idBarang = data[0];
                    String namaBarang = data[1];
                    double hargaBarang = Double.parseDouble(data[2]);
                    int stokBarang = Integer.parseInt(data[3]);
                    daftarBarang.add(new Barang(idBarang, namaBarang, hargaBarang, stokBarang));
                }
            }
            System.out.println("Data barang berhasil dibaca dari file.");
        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan. Tidak ada data yang dibaca.");
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat membaca data barang: " + e.getMessage());
        }
        return daftarBarang;
    }

}

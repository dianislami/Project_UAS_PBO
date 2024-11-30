import java.util.ArrayList;

public class ListBarang {
    private ArrayList<Barang> barang; // Daftar barang yang tersedia

    // Constructor
    public ListBarang() {
        this.barang = new ArrayList<>();
    }

    // Method untuk menambahkan barang ke daftar
    public void tambahBarang(Barang item) {
        this.barang.add(item);
        System.out.println("Barang " + item.getNama() + " berhasil ditambahkan ke daftar.");
    }

    // Method untuk menghapus barang dari daftar
    public void hapusBarang(Barang item) {
        if (this.barang.contains(item)) {
            this.barang.remove(item);
            System.out.println("Barang " + item.getNama() + " berhasil dihapus dari daftar.");
        } else {
            System.out.println("Barang tidak ditemukan di daftar.");
        }
    }

    // Method untuk mendapatkan daftar barang
    public ArrayList<Barang> getBarang() {
        return barang;
    }

    // Method untuk mencari barang berdasarkan nama
    public Barang cariBarang(String nama) {
        for (Barang item : barang) {
            if (item.getNama().equalsIgnoreCase(nama)) {
                return item;
            }
        }
        System.out.println("Barang dengan nama " + nama + " tidak ditemukan.");
        return null;
    }

    // Method untuk menampilkan semua barang di daftar
    public void tampilkanBarang() {
        if (barang.isEmpty()) {
            System.out.println("Daftar barang kosong.");
        } else {
            System.out.println("Daftar Barang yang Tersedia:");
            for (Barang item : barang) {
                System.out.println("- " + item.getNama() + " | Harga: " + item.getHarga());
            }
        }
    }
}

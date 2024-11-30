import java.util.ArrayList;

public class Keranjang {
    private ArrayList<Barang> barang; // Daftar barang dalam keranjang

    // Constructor
    public Keranjang() {
        this.barang = new ArrayList<>();
    }

    // Method untuk menambahkan barang ke keranjang
    public void tambahBarang(Barang item) {
        this.barang.add(item);
        System.out.println("Barang " + item.getNama() + " berhasil ditambahkan ke keranjang.");
    }

    // Method untuk menghapus barang dari keranjang
    public void hapusBarang(Barang item) {
        if (this.barang.contains(item)) {
            this.barang.remove(item);
            System.out.println("Barang " + item.getNama() + " berhasil dihapus dari keranjang.");
        } else {
            System.out.println("Barang tidak ditemukan di keranjang.");
        }
    }

    // Method untuk mendapatkan daftar barang dalam keranjang
    public ArrayList<Barang> getBarang() {
        return barang;
    }

    // Method untuk menghitung total harga barang dalam keranjang
    public double hitungTotal() {
        double total = 0;
        for (Barang item : barang) {
            total += item.getHarga();
        }
        return total;
    }

    // Method untuk menampilkan isi keranjang
    public void tampilkanKeranjang() {
        if (barang.isEmpty()) {
            System.out.println("Keranjang kosong.");
        } else {
            System.out.println("Isi Keranjang:");
            for (Barang item : barang) {
                System.out.println("- " + item.getNama() + " | Harga: " + item.getHarga());
            }
            System.out.println("Total Harga: " + hitungTotal());
        }
    }
}

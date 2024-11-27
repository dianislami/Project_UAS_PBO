import java.util.ArrayList;

public class Keranjang {
    private ArrayList<Barang> barang;

    public Keranjang() {
        this.barang = new ArrayList<>();
    }

    // Method untuk menambahkan barang ke keranjang
    public void tambahBarang(Barang item) {
        barang.add(item);
        System.out.println(item.getNama() + " ditambahkan ke keranjang.");
    }

    // Method untuk menghapus barang dari keranjang
    public void hapusBarang(Barang item) {
        if (barang.remove(item)) {
            System.out.println(item.getNama() + " dihapus dari keranjang.");
        } else {
            System.out.println(item.getNama() + " tidak ditemukan di keranjang.");
        }
    }

    // Method untuk menampilkan barang dalam keranjang
    public void tampilkanBarang() {
        if (barang.isEmpty()) {
            System.out.println("Keranjang kosong.");
        } else {
            System.out.println("Daftar barang dalam keranjang:");
            for (Barang item : barang) {
                System.out.println("- " + item.getNama() + " | Harga: " + item.getHarga());
            }
        }
    }

    public ArrayList<Barang> getBarang() {
        return barang;
    }
}

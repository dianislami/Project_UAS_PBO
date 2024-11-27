import java.util.ArrayList;

public class Transaksi {
    private Customer akun; // Customer yang melakukan transaksi
    private ArrayList<Barang> barang; // Daftar barang dalam transaksi

    // Constructor
    public Transaksi(Customer akun) {
        this.akun = akun;
        this.barang = new ArrayList<>();
    }

    // Method untuk menambahkan barang ke transaksi
    public void tambahBarang(Barang item) {
        this.barang.add(item);
        System.out.println("Barang " + item.getNama() + " berhasil ditambahkan ke transaksi.");
    }

    // Method untuk menghapus barang dari transaksi
    public void hapusBarang(Barang item) {
        if (this.barang.contains(item)) {
            this.barang.remove(item);
            System.out.println("Barang " + item.getNama() + " berhasil dihapus dari transaksi.");
        } else {
            System.out.println("Barang tidak ditemukan dalam transaksi.");
        }
    }

    // Method untuk menghitung total harga transaksi
    public double hitungTotal() {
        double total = 0;
        for (Barang item : barang) {
            total += item.getHarga();
        }
        return total;
    }

    // Method untuk mendapatkan daftar barang
    public ArrayList<Barang> getBarang() {
        return barang;
    }

    // Method untuk mendapatkan akun customer
    public Customer getAkun() {
        return akun;
    }
    
    // Method untuk mencetak detail transaksi
    public void cetakDetail() {
        System.out.println("Detail Transaksi:");
        System.out.println("Customer: " + akun.getId());
        if (barang.isEmpty()) {
            System.out.println("Tidak ada barang dalam transaksi.");
        } else {
            for (Barang item : barang) {
                System.out.println("- " + item.getNama() + " | Harga: " + item.getHarga());
            }
            System.out.println("Total Transaksi: " + hitungTotal());
        }
    }
}

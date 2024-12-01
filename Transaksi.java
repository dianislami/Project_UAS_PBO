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
    public void tambahBarang(Barang item, int jumlah) {
        // Menambahkan barang ke dalam transaksi
        this.barang.add(new Barang(item.getIdBarang(), item.getNamaBarang(), item.getHargaBarang(), jumlah));
    }

    // Method untuk menghapus barang dari transaksi
    public void hapusBarang(Barang item) {
        if (this.barang.contains(item)) {
            this.barang.remove(item);
        } else {
            System.out.println("Barang tidak ditemukan dalam transaksi.");
        }
    }

    // Method untuk menghitung total harga transaksi
    public double hitungTotal() {
        double total = 0;
        for (Barang item : barang) {
            total += item.getHargaBarang() * item.getStokBarang();
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
}

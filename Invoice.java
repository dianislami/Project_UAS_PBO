public class Invoice {
    private Transaksi transaksi; // Transaksi terkait
    private Pembayaran pembayaran; // Metode pembayaran yang digunakan
    private double totalBayar; // Total yang harus dibayar

    // Constructor
    public Invoice(Transaksi transaksi, Pembayaran pembayaran) {
        this.transaksi = transaksi;
        this.pembayaran = pembayaran;
        this.totalBayar = transaksi.hitungTotal(); // Mengambil total dari transaksi
    }

    // Getter untuk transaksi
    public Transaksi getTransaksi() {
        return transaksi;
    }

    // Getter untuk metode pembayaran
    public Pembayaran getPembayaran() {
        return pembayaran;
    }

    // Getter untuk total bayar
    public double getTotalBayar() {
        return totalBayar;
    }

    // Method untuk mencetak detail invoice
    public void cetakInvoice() {
        System.out.println("======== INVOICE ========");
        System.out.println("Customer: " + transaksi.getAkun().getId());
        System.out.println("Daftar Barang:");
        for (Barang barang : transaksi.getBarang()) {
            System.out.println("- " + barang.getNama() + " | Harga: " + barang.getHarga());
        }
        System.out.println("Total Bayar: " + totalBayar);
        System.out.println("Metode Pembayaran: " + pembayaran.getJenisPembayaran());
        System.out.println("=========================");
    }
}

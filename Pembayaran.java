public abstract class Pembayaran {
    private String jenisPembayaran; // Jenis metode pembayaran

    // Constructor
    public Pembayaran(String jenisPembayaran) {
        this.jenisPembayaran = jenisPembayaran;
    }

    // Getter untuk jenis pembayaran
    public String getJenisPembayaran() {
        return jenisPembayaran;
    }

    // Method abstrak untuk memproses pembayaran
    public abstract void prosesPembayaran(double jumlah);
}

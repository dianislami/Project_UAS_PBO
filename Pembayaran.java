public abstract class Pembayaran {
    protected String id; // ID pembayaran

    // Constructor untuk membuat objek pembayaran dengan ID tertentu
    public Pembayaran(String id) {
        this.id = id;
    }

    // Method untuk mendapatkan ID pembayaran
    public String getId() {
        return id;
    }

    // Method abstrak untuk mendapatkan jenis pembayaran (contoh: Kartu Kredit, Transfer Bank)
    public abstract String getJenisPembayaran();

    // Method abstrak untuk memproses pembayaran sesuai jenis pembayaran
    public abstract void prosesPembayaran();
}
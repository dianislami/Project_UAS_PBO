public class COD extends Pembayaran {
    public COD(String id) {
        super(id); // Memanggil constructor dari kelas induk (Pembayaran) untuk mengatur ID pembayaran
    }

    @Override
    public String getJenisPembayaran() {
        return "COD";
    }

    @Override
    // Menampilkan informasi proses pembayaran menggunakan metode COD
    public void prosesPembayaran() {
        System.out.println("Pembayaran dilakukan melalui COD dengan ID: " + id);
        System.out.println("Silakan serahkan pembayaran ke kurir saat barang diterima.");
    }
}

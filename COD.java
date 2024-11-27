public class COD extends Pembayaran {
    public COD(String id) {
        super(id);
    }

    @Override
    public String getJenisPembayaran() {
        return "COD";
    }

    @Override
    public void prosesPembayaran() {
        System.out.println("Pembayaran dilakukan melalui COD dengan ID: " + id);
        System.out.println("Silakan serahkan pembayaran ke kurir saat barang diterima.");
    }
}

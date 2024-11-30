public class COD extends Pembayaran {
    public COD() {
        super("Cash on Delivery");
    }

    @Override
    public void prosesPembayaran(double jumlah) {
        System.out.println("Pembayaran sebesar " + jumlah + " akan dilakukan secara tunai saat barang diterima (COD).");
    }
}

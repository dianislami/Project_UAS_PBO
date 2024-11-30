public class QRIS extends Pembayaran {
    public QRIS() {
        super("QRIS");
    }

    @Override
    public void prosesPembayaran(double jumlah) {
        System.out.println("Pembayaran sebesar " + jumlah + " menggunakan QRIS berhasil diproses.");
    }
}

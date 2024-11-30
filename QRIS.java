public class QRIS extends Pembayaran {
    public QRIS(String id) {
        super(id);
    }

    @Override
    public String getJenisPembayaran() {
        return "QRIS";
    }

    @Override
    public void prosesPembayaran() {
        tampilkanQRCode(); // Tampilkan QR Code saat proses pembayaran dimulai
        System.out.println("Pembayaran berhasil melalui QRIS dengan ID: " + id);
    }

    private void tampilkanQRCode() {
        System.out.println("\n[QR Code]");
        System.out.println("|######################|");
        System.out.println("|      SCAN ME!       |");
        System.out.println("|######################|");
        System.out.println("Gunakan aplikasi pembayaran untuk memindai QR Code.\n");
    }
}

public class Bank extends Pembayaran {
    private String namaBank;

    public Bank(String namaBank) {
        super("Bank Transfer");
        this.namaBank = namaBank;
    }

    public String getNamaBank() {
        return namaBank;
    }

    @Override
    public void prosesPembayaran(double jumlah) {
        System.out.println("Pembayaran sebesar " + jumlah + " melalui bank " + namaBank + " berhasil diproses.");
    }
}

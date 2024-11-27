import java.util.HashMap;
import java.util.Map;

public abstract class Pembayaran {
    protected String id; // ID pembayaran

    public Pembayaran(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
    public abstract String getJenisPembayaran();
    public abstract void prosesPembayaran();
}

// QRIS
class QRIS extends Pembayaran {
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

// COD
class COD extends Pembayaran {
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

// Bank
class Bank extends Pembayaran {
    private static final Map<String, String[]> daftarBank = new HashMap<>();

    static {
        // Menambahkan daftar bank, nomor rekening, dan nama pemilik rekening
        daftarBank.put("Bank Aceh", new String[]{"1234567890", "Pinkypie"});
        daftarBank.put("BSI", new String[]{"2345678901", "Pinkypie"});
        daftarBank.put("BCA", new String[]{"3456789012", "Pinkypie"});
        daftarBank.put("Mandiri", new String[]{"4567890123", "Pinkypie"});
    }

    public Bank(String id) {
        super(id);
    }

    @Override
    public String getJenisPembayaran() {
        return "Bank";
    }

    @Override
    public void prosesPembayaran() {
        System.out.println("Pembayaran melalui transfer Bank dengan ID: " + id);
        tampilkanDaftarBank(); // Menampilkan daftar bank saat pembayaran dilakukan
    }

    // Method untuk menampilkan daftar bank
    private void tampilkanDaftarBank() {
        System.out.println("\nDaftar Bank yang Tersedia:");
        for (String bank : daftarBank.keySet()) {
            String[] info = daftarBank.get(bank);
            System.out.println("- " + bank + ":");
            System.out.println("  Nomor Rekening: " + info[0]);
            System.out.println("  Nama Pemilik  : " + info[1]);
        }
        System.out.println("Silakan transfer ke salah satu rekening di atas.\n");
    }
}

       

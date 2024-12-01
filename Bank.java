
import java.util.HashMap;
import java.util.Map;

public class Bank extends Pembayaran {
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
    public String getJenisPembayaran() { // Mengembalikan jenis pembayaran yaitu "Bank"
        return "Bank";
    }

    @Override
    public void prosesPembayaran() { // Menjalankan proses pembayaran melalui bank
        System.out.println("Pembayaran melalui transfer Bank dengan ID: " + id);
        tampilkanDaftarBank(); // Menampilkan daftar bank saat pembayaran dilakukan
    }

    private void tampilkanDaftarBank() {
        System.out.println("\nDaftar Bank yang Tersedia:");
        for (String bank : daftarBank.keySet()) {
            // Mengambil informasi bank, termasuk nomor rekening dan nama pemilik
            String[] info = daftarBank.get(bank);
            System.out.println("- " + bank + ":");
            System.out.println("  Nomor Rekening: " + info[0]);
            System.out.println("  Nama Pemilik  : " + info[1]);
        }
        // Memberikan instruksi untuk transfer ke salah satu rekening
        System.out.println("Silakan transfer ke salah satu rekening di atas.\n");
    }
}

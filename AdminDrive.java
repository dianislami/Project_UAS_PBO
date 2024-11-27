import java.util.Scanner;

public class AdminDrive {
    private Admin admin;

    public AdminDrive(Admin admin) {
        this.admin = admin;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nMenu Admin:");
            System.out.println("1. Tambah Barang");
            System.out.println("2. Edit Barang");
            System.out.println("3. Hapus Barang");
            System.out.println("4. Lihat Barang");
            System.out.println("5. Logout");
            System.out.print("Pilih: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // kode untuk menambah barang
                    break;
                case 2:
                    // kode untuk mengedit barang
                    break;
                case 3:
                    // kode untuk menghapus barang
                    break;
                case 4:
                    admin.viewProducts();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }
}

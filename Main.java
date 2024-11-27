import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Akun> accounts = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadData(); // Memuat data dari file teks saat program dimulai

        while (true) {
            System.out.println("\nSelamat datang di Sistem Belanja Online!");
            System.out.println("1. Login");
            System.out.println("2. Buat Akun");
            System.out.println("3. Keluar");
            System.out.print("Pilih: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Bersihkan buffer

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    createAccount();
                    break;
                case 3:
                    saveData(); // Menyimpan data sebelum keluar
                    System.out.println("Terima kasih telah menggunakan sistem ini.");
                    System.exit(0);
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private static void login() {
        System.out.print("\nMasukkan username: ");
        String username = scanner.nextLine();
        System.out.print("Masukkan password: ");
        String password = scanner.nextLine();

        for (Akun account : accounts) {
            if (account.login(username, password)) {
                if (account instanceof Admin) {
                    Admin admin = (Admin) account; // Casting eksplisit
                    System.out.println("Login berhasil! Mengalihkan ke menu Admin.");
                    adminMenu(admin);
                } else if (account instanceof Customer) {
                    Customer customer = (Customer) account; // Casting eksplisit
                    System.out.println("Login berhasil! Mengalihkan ke menu Customer.");
                    customerMenu(customer);
                }
                return;
            }
        }
        System.out.println("Login gagal! Periksa username dan password.");
    }

    private static void createAccount() {
        System.out.print("\nMasukkan username: ");
        String username = scanner.nextLine();
        System.out.print("Masukkan password: ");
        String password = scanner.nextLine();
        System.out.print("Pilih tipe akun (1. Admin, 2. Customer): ");
        int type = scanner.nextInt();
        scanner.nextLine(); // Bersihkan buffer
    
        Akun newAccount;
        if (type == 1) {
            newAccount = new Admin(String.valueOf(accounts.size() + 1), username, password);
        } else if (type == 2) {
            newAccount = new Customer(String.valueOf(accounts.size() + 1), username, password);
        } else {
            System.out.println("Pilihan tidak valid. Akun tidak dibuat.");
            return;
        }
        accounts.add(newAccount);
        saveData(); // Panggil saveData() setelah akun dibuat
        System.out.println("Akun berhasil dibuat! Silakan login.");
    }
    
    private static void adminMenu(Admin admin) {
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
                    System.out.print("Masukkan ID Barang: ");
                    String id = scanner.nextLine();
                    System.out.print("Masukkan Nama Barang: ");
                    String name = scanner.nextLine();
                    System.out.print("Masukkan Harga Barang: ");
                    double price = scanner.nextDouble();
                    System.out.print("Masukkan Stok Barang: ");
                    int stock = scanner.nextInt();
                    scanner.nextLine();
                    admin.addProduct(new Product(id, name, price, stock));
                    saveData(); // Simpan data setelah barang ditambahkan
                    break;
                case 2:
                    System.out.print("Masukkan ID Barang yang ingin diedit: ");
                    String editId = scanner.nextLine();
                    System.out.print("Masukkan Nama Baru: ");
                    String editName = scanner.nextLine();
                    System.out.print("Masukkan Harga Baru: ");
                    double editPrice = scanner.nextDouble();
                    System.out.print("Masukkan Stok Baru: ");
                    int editStock = scanner.nextInt();
                    scanner.nextLine();
                    admin.editProduct(editId, new Product(editId, editName, editPrice, editStock));
                    saveData(); // Simpan data setelah barang diedit
                    break;
                case 3:
                    System.out.print("Masukkan ID Barang yang ingin dihapus: ");
                    String deleteId = scanner.nextLine();
                    admin.removeProduct(deleteId);
                    saveData(); // Simpan data setelah barang dihapus
                    break;
                case 4:
                    admin.viewProducts();
                    break;
                case 5:
                    System.out.println("Logout berhasil.");
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private static void customerMenu(Customer customer) {
        while (true) {
            System.out.println("\nMenu Customer:");
            System.out.println("1. Lihat Barang");
            System.out.println("2. Tambah Barang ke Keranjang");
            System.out.println("3. Hapus Barang dari Keranjang");
            System.out.println("4. Checkout");
            System.out.println("5. Lihat Keranjang");
            System.out.println("6. Logout");
            System.out.print("Pilih: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    customer.viewProducts(getAllProducts());
                    break;
                case 2:
                    System.out.print("Masukkan ID Barang: ");
                    String addId = scanner.nextLine();
                    Product product = findProductById(addId);
                    if (product != null) {
                        System.out.print("Masukkan jumlah barang: ");
                        int quantity = scanner.nextInt();
                        scanner.nextLine();
                        if (quantity > 0 && quantity <= product.getStock()) {
                            customer.addToCart(product, quantity);
                            saveData(); // Simpan data setelah barang ditambahkan ke keranjang
                        } else {
                            System.out.println("Jumlah barang tidak valid atau stok tidak mencukupi.");
                        }
                    } else {
                        System.out.println("Barang tidak ditemukan.");
                    }
                    break;
                case 3:
                    System.out.print("Masukkan ID Barang yang ingin dihapus dari keranjang: ");
                    String removeId = scanner.nextLine();
                    customer.removeFromCart(removeId);
                    saveData(); // Simpan data setelah barang dihapus dari keranjang
                    break;
                case 4:
                    customer.checkout();
                    saveData(); // Simpan data setelah checkout
                    break;
                case 5:
                    customer.viewCart();
                    break;
                case 6:
                    System.out.println("Logout berhasil.");
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private static List<Product> getAllProducts() {
        List<Product> allProducts = new ArrayList<>();
        for (Akun account : accounts) {
            if (account instanceof Admin) {
                Admin admin = (Admin) account; // Casting eksplisit
                allProducts.addAll(admin.getProducts());
            }
        }
        return allProducts;
    }

    private static Product findProductById(String id) {
        for (Product product : getAllProducts()) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    // Menyimpan data akun dan produk ke file teks
    private static void saveData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data.txt"))) {
            if (accounts.isEmpty()) {
                System.out.println("Tidak ada data untuk disimpan.");
                return; // Tidak ada akun, langsung keluar dari metode
            }
    
            for (Akun account : accounts) {
                // Simpan akun (Admin atau Customer)
                writer.write(account.id + "," + account.username + "," + account.password + "\n");
    
                // Simpan produk jika akun adalah Admin
                if (account instanceof Admin) {
                    Admin admin = (Admin) account; // Casting eksplisit
                    if (admin.getProducts().isEmpty()) {
                        writer.write("# Produk kosong untuk Admin ini\n");
                    } else {
                        for (Product product : admin.getProducts()) {
                            writer.write(product.getId() + "," + product.getName() + "," +
                                    product.getPrice() + "," + product.getStock() + "\n");
                        }
                    }
                }
            }
            System.out.println("Data berhasil disimpan ke file data.txt.");
        } catch (IOException e) {
            System.out.println("Gagal menyimpan data: " + e.getMessage());
        }
    }
    
    // Membaca data akun dan produk dari file teks
    private static void loadData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("data.txt"))) {
            String line;
            Admin currentAdmin = null;
    
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) { // Akun
                    String id = data[0];
                    String username = data[1];
                    String password = data[2];
    
                    // Membuat akun Admin atau Customer
                    if (username.startsWith("admin")) {
                        currentAdmin = new Admin(id, username, password);
                        accounts.add(currentAdmin);
                    } else {
                        accounts.add(new Customer(id, username, password));
                    }
                } else if (data.length == 4 && currentAdmin != null) {
                    String productId = data[0];
                    String productName = data[1];
                    double productPrice = Double.parseDouble(data[2]);
                    int productStock = Integer.parseInt(data[3]);
                    currentAdmin.addProduct(new Product(productId, productName, productPrice, productStock));
                }
            }
        } catch (IOException e) {
            System.out.println("Gagal membaca data: " + e.getMessage());
        }
    }
}

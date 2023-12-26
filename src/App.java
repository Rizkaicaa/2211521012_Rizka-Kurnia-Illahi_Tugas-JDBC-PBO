import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.List;


public class App {

    private static Login login = new Login();
    private static boolean isLoggedIn = false;

    // Metode untuk mendapatkan tanggal dan waktu saat ini dengan format "EEE, dd/MM/yyyy"
    private static String getFormattedTanggal() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEEEEEE, dd/MM/yyyy");
        return dateFormat.format(date);
    }

    // Metode untuk mendapatkan waktu saat ini dengan format "hh:mm:ss z"
    private static String getFormattedWaktu() {
        Date date = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss z");
        return timeFormat.format(date);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Mengecek login sebelum memulai transaksi
        if (!doLogin(scanner)) {
            scanner.close();
            return;
        }

        boolean ulangTransaksi = true;
        while (ulangTransaksi) {
            // Input Data Transaksi
            System.out.println("\n------ Input Data Transaksi ------");
            try {
                System.out.print("No. Faktur\t: ");
                String noFaktur = scanner.nextLine();

                System.out.print("Nama Pelanggan\t: ");
                String namaPelanggan = scanner.nextLine();

                System.out.print("No. HP\t\t: ");
                String noHpPelanggan = scanner.nextLine();

                System.out.print("Alamat\t\t: ");
                String alamatPelanggan = scanner.nextLine();

                Faktur faktur = new Faktur(noFaktur, namaPelanggan);

                // Input Nama Kasir
                System.out.print("Nama Kasir\t: ");
                String namaKasir = scanner.nextLine();
                faktur.setNamaKasir(namaKasir);

                // Input Barang
                boolean tambahBarang = true;
                while (tambahBarang) {
                    System.out.print("Kode Barang\t: ");
                    String kodeBarang = scanner.nextLine();

                    System.out.print("Nama Barang\t: ");
                    String namaBarang = scanner.nextLine();

                    System.out.print("Harga Barang\t: ");
                    double hargaBarang = scanner.nextDouble();

                    System.out.print("Jumlah Barang\t: ");
                    int jumlahBarang = scanner.nextInt();
                    scanner.nextLine(); // Membersihkan buffer newline

                    Barang barang = new Barang(kodeBarang, namaBarang, hargaBarang, jumlahBarang);
                    HitungTotalBayar hitungTotalBayar = new HitungTotalBayar();

                    faktur.tambahBarang(barang);
                    faktur.setTotalBayar(faktur.getTotalBayar() + hitungTotalBayar.calculateTotal(barang));

                    // Apabila terdapat lebih dari satu barang
                    System.out.print("Tambah barang?(y/n): ");
                    String tambahLagi = scanner.nextLine().toLowerCase();
                    tambahBarang = tambahLagi.equals("y");
                }

                // Menampilkan faktur belanja
                System.out.println("\n========== R3 SUPERMARKET ==========");
                System.out.println("Tanggal \t: " + getFormattedTanggal());
                System.out.println("Waktu   \t: " + getFormattedWaktu());
                System.out.println("No. Faktur\t: " + noFaktur);
                System.out.println("====================================");
                System.out.println("         DATA PELANGGAN");
                System.out.println("------------------------------------");
                System.out.println("Nama Pelanggan \t: " + namaPelanggan.toUpperCase());
                System.out.println("No. HP         \t: " + noHpPelanggan); // Tambahkan informasi No. HP
                System.out.println("Alamat         \t: " + alamatPelanggan); // Tambahkan informasi Alamat
                System.out.println("++++++++++++++++++++++++++++++++++++");
                System.out.println("      DATA PEMBELIAN BARANG");
                System.out.println("------------------------------------");
                faktur.displayFaktur();
                // Tampilkan nama kasir
                System.out.println("++++++++++++++++++++++++++++++++++++");
                System.out.println("Kasir\t\t: " + namaKasir.toUpperCase());
                System.out.println("Terima kasih! Silahkan datang kembali.");
            } catch (Exception e) {
                System.out.println("Terjadi Kesalahan Pada Input: " + e.getMessage());
                System.out.println("\n");
            }

            // untuk lanjut ke transaksi pembelian berikutnya
            System.out.print("\nLakukan transaksi pembeli berikutnya? (y/n): ");
            String ulangiTransaksi = scanner.nextLine().toLowerCase();
            ulangTransaksi = ulangiTransaksi.equals("y");
        }
        scanner.close();
    }

    public static void doCRUDBarang(Scanner scanner) {
        boolean ulangCRUD = true;
        while (ulangCRUD) {
            System.out.println("\n------------ Menu (CRUD) ------------");
            System.out.println("1. Tambah Barang");
            System.out.println("2. Lihat Semua Barang");
            System.out.println("3. Update Barang");
            System.out.println("4. Hapus Barang");
            System.out.println("5. Transaksi Pelanggan");
            System.out.print("Pilih operasi (1/2/3/4/5): ");
    
            String crudChoice = scanner.nextLine();
    
            switch (crudChoice) {
                case "1":
                    // Tambah Barang
                    tambahBarang(scanner);
                    break;
                case "2":
                    // Lihat Semua Barang
                    lihatSemuaBarang();
                    break;
                case "3":
                    // Update Barang
                    updateBarang(scanner);
                    break;
                case "4":
                    // Hapus Barang
                    hapusBarang(scanner);
                    break;
                case "5":
                    // Kembali ke Menu Utama
                    ulangCRUD = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                    break;
            }
        }
    }
    
    private static void tambahBarang(Scanner scanner) {
        System.out.println("\n----------- Tambah Barang ------------");
    
        // Input data barang (kode, nama, harga, jumlah)
        System.out.print("Masukkan Kode Barang\t: ");
        String kode = scanner.nextLine();
    
        System.out.print("Masukkan Nama Barang\t: ");
        String nama = scanner.nextLine();
    
        System.out.print("Masukkan Harga Barang\t: ");
        double harga = scanner.nextDouble();
    
        System.out.print("Masukkan Jumlah Barang\t: ");
        int jumlah = scanner.nextInt();
        scanner.nextLine(); // Membersihkan buffer newline
    
        // Buat objek Barang dan panggil metode tambahBarang
        Barang barangBaru = new Barang(kode, nama, harga, jumlah);
        barangBaru.tambahBarang();
    
        System.out.println("Barang berhasil ditambahkan.");
    }
    
    private static void lihatSemuaBarang() {
        System.out.println("\n------------ Data Barang ------------");
    
        // Panggil metode bacaSemuaBarang dari Barang
        List<Barang> daftarBarang = Barang.lihatSemuaBarang();
    
        // Tampilkan informasi semua barang
        for (Barang barang : daftarBarang) {
            System.out.println("Kode: " + barang.getKodeBarang() + ", Nama: " + barang.getNamaBarang() +
                    ", Harga: " + barang.getHarga() + ", Jumlah: " + barang.getJumlah());
        }
    }
    
    public static void updateBarang(Scanner scanner) {
    System.out.println("\n------------ Update Barang ------------");

    // Input kode barang yang akan diupdate
    System.out.print("Masukkan Kode Barang yang akan diupdate\t: ");
    String kodeToUpdate = scanner.nextLine();

    // Mengecek apakah barang dengan kode yang dimasukkan pengguna ditemukan
    Barang barangToUpdate = Barang.getBarangByKode(kodeToUpdate);

    if (barangToUpdate != null) {
        // Input data baru
        System.out.print("Masukkan Nama Barang Baru\t: ");
        String namaBarangBaru = scanner.nextLine();

        System.out.print("Masukkan Harga Barang Baru\t: ");
        double hargaBarangBaru = scanner.nextDouble();

        System.out.print("Masukkan Jumlah Barang Baru: ");
        int jumlahBarangBaru = scanner.nextInt();
        scanner.nextLine(); // Membersihkan buffer newline

        // Update barang dan panggil metode updateBarang dari kelas Barang
        barangToUpdate.updateBarang(namaBarangBaru, hargaBarangBaru, jumlahBarangBaru);
        System.out.println("Barang berhasil diupdate.");
    } else {
        System.out.println("Barang dengan kode " + kodeToUpdate + " tidak ditemukan.");
    }
}

    
    private static void hapusBarang(Scanner scanner) {
        System.out.println("\n------------ Hapus Barang ------------");
    
        // Input kode barang yang akan dihapus
        System.out.print("Masukkan Kode Barang yang akan dihapus: ");
        String kodeToDelete = scanner.nextLine();
    
        // Hapus barang dan panggil metode hapusBarang dari kelas Barang
        Barang.hapusBarang(kodeToDelete);
    
        System.out.println("Barang berhasil dihapus.");
    }
    
    private static boolean doLogin(Scanner scanner) {
        System.out.println("\n------------- Login -------------");
        System.out.print("Username\t: ");
        String username = scanner.nextLine();
    
        System.out.print("Password\t: ");
        String password = scanner.nextLine();
    
        System.out.print("Captcha\t\t: " + login.getCurrentCaptcha() + "\nInput Captcha\t: ");
        String enteredCaptcha = scanner.nextLine();
    
        isLoggedIn = login.authenticate(username, password, enteredCaptcha);
        if (isLoggedIn) {
            System.out.println("Login successful!\n");
            doCRUDBarang(scanner);
        } else {
            System.out.println("Login failed. Username, password, or captcha incorrect.\n");
        }
    
        return isLoggedIn;
    }
    
    
    
}

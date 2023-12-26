import java.util.ArrayList;

public class Faktur {
    
    private ArrayList<Barang> daftarBarang = new ArrayList<>();
    private double totalBayar;

    public Faktur(String noFaktur, String namaPelanggan) throws MinimarketException {
        if (noFaktur == null || noFaktur.isEmpty() || namaPelanggan == null || namaPelanggan.isEmpty()) {
            throw new MinimarketException("Input tidak valid");
        }
    }

    // Metode untuk menambahkan barang ke faktur
    public void tambahBarang(Barang barang) {
        daftarBarang.add(barang);
    }

    // Metode untuk mengatur total bayar
    public void setTotalBayar(double totalBayar) {
        this.totalBayar = totalBayar;
    }

    // Metode untuk mendapatkan total bayar
    public double getTotalBayar() {
        return totalBayar;
    }
    
    // Metode untuk menampilkan faktur
    public void displayFaktur() {

        for (Barang barang : daftarBarang) {
            System.out.println("Kode Barang\t: "+ barang.getKodeBarang());  // Tambahkan informasi Kode Barang
            System.out.println("Nama Barang\t: " + barang.getNamaBarang().toLowerCase());
            System.out.println("Harga Barang\t: " + barang.getHarga());
            System.out.println("Jumlah Barang\t: " + barang.getJumlah());
            System.out.println("------------------------------------");
        }

        System.out.println("Total Bayar\t: " + totalBayar);
    }

    // Metode untuk mengatur nama kasir
    public void setNamaKasir(String namaKasir) {
    }
}

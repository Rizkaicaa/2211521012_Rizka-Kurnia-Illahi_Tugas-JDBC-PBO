import java.util.ArrayList;
import java.util.List;

public class Barang {
    private static List<Barang> daftarBarang = new ArrayList<>();

    private String kodeBarang;
    private String namaBarang;
    private double harga;
    private int jumlah;

    // Constructors
    public Barang(String kodeBarang, String namaBarang, double harga, int jumlah) {
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.harga = harga;
        this.jumlah = jumlah;
    }

    // Getter methods
    public String getNamaBarang() {
        return namaBarang;
    }

    public String getKodeBarang() {
        return kodeBarang;
    }

    public double getHarga() {
        return harga;
    }

    public int getJumlah() {
        return jumlah;
    }

    
// CRUD operations
public void tambahBarang() {
    daftarBarang.add(this);
}

public static void hapusBarang(String kodeBarang) {
    daftarBarang.removeIf(barang -> barang.getKodeBarang().equals(kodeBarang));
}


public static List<Barang> lihatSemuaBarang() {
    return new ArrayList<>(daftarBarang);
}

public static Barang getBarangByKode(String kodeBarang) {
    for (Barang barang : daftarBarang) {
        if (barang.getKodeBarang().equals(kodeBarang)) {
            return barang;
        }
    }
    return null;
}

public static void updateBarang(String kodeBarang, String namaBarang, double harga, int jumlah) {
    for (Barang barang : daftarBarang) {
        if (barang.getKodeBarang().equals(kodeBarang)) {
            barang.setNamaBarang(namaBarang);
            barang.setHarga(harga);
            barang.setJumlah(jumlah);
            System.out.println("Barang berhasil diupdate.");
            return;
        }
    }
    System.out.println("Barang dengan kode " + kodeBarang + " tidak ditemukan.");
}

    // Additional setter methods for update operation
    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public void updateBarang(String namaBarangBaru, double hargaBarangBaru, int jumlahBarangBaru) {
    }



}

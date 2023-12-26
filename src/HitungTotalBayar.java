public class HitungTotalBayar implements CalculateTotal {
    @Override
    public double calculateTotal(Barang barang) {
        return barang.getHarga() * barang.getJumlah();
    }
}

package tr.com.turksat.sekilapp.model;
import tr.com.turksat.sekilapp.annotation.TestableClass;
import tr.com.turksat.sekilapp.annotation.TestableMethod;


/**
 * Yuvarlak şekli sınıfı.
 * Yarıçap ve pi sayısı kullanılarak alan ve çevresi hesaplanır.
 */

@TestableClass
public class Yuvarlak extends Sekil {
    private final int yaricap;
    private final double piSayisi;
    // değişkenler sadece bir kere atanıp sonra değiştirilmediği için final yaptım.

    /**
     * Yuvarlak nesnesi oluşturur.
     *
     * @param yaricap Yuvarlağın yarıçapı
     * @param piSayisi Pi sayısı (genellikle 3.14 veya Math.PI gibi)
     */
    public Yuvarlak(int yaricap, double piSayisi) {
        this.yaricap = yaricap;
        this.piSayisi = piSayisi;
    }
    /**
     * Yuvarlağı konsola sembollerle çizer.
     * Basit mesafe kontrolüyle çevreye yakın noktalar sembol ile gösterilir.
     */
    @Override
    public void ciz() {
        int cap = yaricap * 2;


        for (int y = 0; y <= cap; y++) {
            for (int x = 0; x <= cap; x++) {
                int dist = (int) Math.sqrt(Math.pow(x - yaricap, 2) + Math.pow(y - yaricap, 2));
                if (dist >= yaricap - 1 && dist <= yaricap + 1) {
                    System.out.print(sembol);
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

    }
    /**
     * Yuvarlağın alanını hesaplar.
     * Formül: π * yaricap^2
     *
     * @return Alan değeri
     */
    @Override
    @TestableMethod

    public double alanHesapla() {

        return  piSayisi*yaricap *yaricap;
    }

    /**
     * Yuvarlağın çevresini hesaplar.
     * Formül: 2 * π * yaricap
     *
     * @return Çevre değeri
     */
    @Override
    @TestableMethod

    public double cevreHesapla() {

        return  2*piSayisi*yaricap;
    }
    /**
     * Yuvarlağın yarıçapı ve pi sayısını açıklayan String döner.
     *
     * @return Yuvarlak bilgisi
     */
    @Override
    public String toString() {
        return "Yuvarlak: yaricap: " + yaricap  + ", piSayisi: " + piSayisi;
    }
}




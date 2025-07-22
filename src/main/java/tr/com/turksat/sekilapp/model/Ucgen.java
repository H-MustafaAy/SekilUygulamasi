package tr.com.turksat.sekilapp.model;

<<<<<<< HEAD
import tr.com.turksat.sekilapp.annotation.TestableClass;
import tr.com.turksat.sekilapp.annotation.TestableMethod;

=======
>>>>>>> c0746bee659456860b0c0d1e61ceb9ef89fdd8a7
/**
 * Üçgen şekli sınıfı.
 * Verilen taban ve yükseklik değerlerine göre üçgeni temsil eder.
 */
<<<<<<< HEAD
@TestableClass
=======
>>>>>>> c0746bee659456860b0c0d1e61ceb9ef89fdd8a7
public class Ucgen extends Sekil {
    private final int yukseklik;
    private final int taban;

    /**
     * Üçgen nesnesi oluşturur.
     *
     * @param yukseklik Üçgenin yüksekliği
     * @param taban Üçgenin taban uzunluğu
     */
    public Ucgen(int yukseklik, int taban) {
        this.yukseklik = yukseklik;
        this.taban = taban;
    }

    /**
     * Üçgeni konsola sembollerle çizer.
     *
     * Yükseklik ve taban oranına göre her satırda artan sayıda sembol yazdırır.
     */
    @Override
    public void ciz(){

        for (double i = 0; i <= yukseklik; i++) {
            for (double j = 1; j <= (i*taban / yukseklik); j++) {
                System.out.print(sembol);
            }
            System.out.println();
        }


    }
    /**
     * Üçgenin alanını hesaplar.
     * Alan = (yukseklik * taban) / 2 formülü kullanılır.
     * @return Üçgenin alanı (double)
     */
    @Override
<<<<<<< HEAD
    @TestableMethod
=======
>>>>>>> c0746bee659456860b0c0d1e61ceb9ef89fdd8a7
    public double alanHesapla(){
        return (yukseklik * taban)/2;
    }

    /**
     * Üçgenin çevresini hesaplar.
     * double hipotenus = Math.sqrt(Math.pow(yukseklik,2)+ Math.pow(taban,2) ile bulunur.
     * cevre = yukseklik + taban + hipotenus ile hesaplanıyor.
     * @return cevre (double)
     */
    @Override
<<<<<<< HEAD
    @TestableMethod
=======
>>>>>>> c0746bee659456860b0c0d1e61ceb9ef89fdd8a7
    public double cevreHesapla(){
        double hipotenus = Math.sqrt(Math.pow(yukseklik, 2) + Math.pow(taban, 2));

        return yukseklik + taban + hipotenus ;
    }

        @Override
    public String toString() {
        return "Üçgen : "+ "yukseklik: " + yukseklik + ", taban: " + taban;
    }
}

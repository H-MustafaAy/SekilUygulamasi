package tr.com.turksat.sekilapp.model;
<<<<<<< HEAD

import tr.com.turksat.sekilapp.annotation.TestableClass;
import tr.com.turksat.sekilapp.annotation.TestableMethod;

=======
>>>>>>> c0746bee659456860b0c0d1e61ceb9ef89fdd8a7
/**
 * Dortgen sınıfı, Sekil sınıfından türetilmiş,
 * dikdörtgen ve kare gibi dört kenarlı şekiller için temel sınıftır.
 *
 * En ve boy uzunlukları protected olarak tanımlanmıştır,
 * böylece alt sınıflar tarafından erişilebilir.
 */
<<<<<<< HEAD
@TestableClass
=======
>>>>>>> c0746bee659456860b0c0d1e61ceb9ef89fdd8a7
public class Dortgen extends Sekil {
    protected int en;
    protected int boy;
    /**
     * Dortgen nesnesi oluşturur.
     *
     * @param en Dörtgenin eni (genişliği)
     * @param boy Dörtgenin boyu (yüksekliği)
     */
    public Dortgen(int en, int boy) {
        this.en = en;
        this.boy = boy;
    }
    /**
     * Dörtgenin alanını hesaplar.
     *
     * @return Alan değeri (en * boy)
     */
    @Override
<<<<<<< HEAD
    @TestableMethod
=======
>>>>>>> c0746bee659456860b0c0d1e61ceb9ef89fdd8a7
    public double alanHesapla() {
        return en * boy;
    }


    /**
     * Dörtgenin çevresini hesaplar.
     *
     * @return Çevre değeri (2 * (en + boy))
     */
    @Override
<<<<<<< HEAD
    @TestableMethod
=======
>>>>>>> c0746bee659456860b0c0d1e61ceb9ef89fdd8a7
    public double cevreHesapla() {
        return 2 * (en + boy);
    }

    /**
     * Konsola dörtgeni sembollerle çizdirir.
     * En ve boy kadar satır ve sütun olacak şekilde.
     */public void ciz(){
        for (double i= 0; i< boy; i++){
            for (double j= 0; j< en; j++){
                System.out.print(sembol + " ");

            }
            System.out.println();
        }
    }

    /**
     * Dörtgenin bilgilerini içeren açıklayıcı metin döner.
     *
     * @return Dörtgenin eni ve boyu ile ilgili String
     */
    @Override
    public String toString() {
        return "Dörtgen : "+ "en: " + en + ", boy: " + boy + "\n";
    }
}

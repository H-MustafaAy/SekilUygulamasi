package tr.com.turksat.sekilapp.model;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Kare sınıfı için alan ve çevre hesaplama metodlarının doğruluğunu test eden birim test sınıfı.
 */
public class KareTest {

    /**
     * Kare testleri için parametre sağlayan DataProvider metodu.
     
     *
     * @return test senaryoları dizisi
     */
    @DataProvider(name = "kareVerisi")
    public Object[][] kareVerisi() {
        return new Object[][] {
            {2, 4, 8},      // Alan: 2*2=4, Çevre: 4*2=8
            {5, 25, 20},    // Alan: 5*5=25, Çevre: 4*5=20
            {10, 100, 40}   // Alan: 10*10=100, Çevre: 4*10=40
        };
    }

    /**
     * Kare sınıfının alan ve çevre hesaplamalarının doğruluğunu test eder.
     * Her test için farklı kenar uzunlukları kullanılır.
     *
     * @param kenar Karenin kenar uzunluğu
     * @param beklenenAlan Beklenen alan sonucu
     * @param beklenenCevre Beklenen çevre sonucu
     */
    @Test(dataProvider = "kareVerisi")
    public void testKareAlanVeCevre(int kenar, int beklenenAlan, int beklenenCevre) {
        // Test edilecek Kare nesnesi oluşturuluyor
        Kare kare = new Kare(kenar);

        // Alan hesaplaması test ediliyor
        assertEquals(kare.alanHesapla(), beklenenAlan, "Alan hatalı!");

        // Çevre hesaplaması test ediliyor
        assertEquals(kare.cevreHesapla(), beklenenCevre, "Çevre hatalı!");
    }
}

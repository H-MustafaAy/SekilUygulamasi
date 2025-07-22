package tr.com.turksat.sekilapp.model;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
<<<<<<< HEAD

import tr.com.turksat.sekilapp.annotation.TestableClass;

=======
>>>>>>> c0746bee659456860b0c0d1e61ceb9ef89fdd8a7
import static org.testng.Assert.*;

/**
 * Dikdörtgen sınıfı için alan ve çevre hesaplama fonksiyonlarının test edildiği birim test sınıfı.
 */
<<<<<<< HEAD
@TestableClass
=======
>>>>>>> c0746bee659456860b0c0d1e61ceb9ef89fdd8a7
public class DikdortgenTest {

    /**
     * Test verilerini sağlayan DataProvider metodu.
     * Her satır: { en, boy, beklenenAlan, beklenenCevre }
     *
     * @return test için kullanılacak parametreler dizisi
     */
    @DataProvider(name = "dikdortgenVerisi")
    public Object[][] dikdortgenVerisi() {
        return new Object[][] {
            {2, 3, 6, 10},       // Alan: 2*3=6, Çevre: 2*(2+3)=10
            {5, 7, 35, 24},      // Alan: 5*7=35, Çevre: 2*(5+7)=24
            {10, 20, 200, 60}    // Alan: 10*20=200, Çevre: 2*(10+20)=60
        };
    }

    /**
     * Alan ve çevre hesaplamalarının doğru çalışıp çalışmadığını test eder.
     * TestNG'nin @Test ve @DataProvider özellikleri kullanılarak parametreli test yapılır.
     *
     * @param en dikdörtgenin kısa kenarı
     * @param boy dikdörtgenin uzun kenarı
     * @param beklenenAlan beklenen alan sonucu
     * @param beklenenCevre beklenen çevre sonucu
     */
    @Test(dataProvider = "dikdortgenVerisi")
    public void testDikdortgenAlanVeCevre(int en, int boy, int beklenenAlan, int beklenenCevre) {
        // Test edilecek Dikdortgen nesnesi oluşturuluyor
        Dikdortgen d = new Dikdortgen(en, boy);

        // Alan hesaplama sonucu beklenen değerle karşılaştırılıyor
        assertEquals(d.alanHesapla(), beklenenAlan, "Alan hatalı!");

        // Çevre hesaplama sonucu beklenen değerle karşılaştırılıyor
        assertEquals(d.cevreHesapla(), beklenenCevre, "Çevre hatalı!");
    }
}

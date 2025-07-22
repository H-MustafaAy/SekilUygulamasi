package tr.com.turksat.sekilapp.model;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Yuvarlak (daire) şekli için alan ve çevre hesaplama metodlarının doğruluğunu test eden birim test sınıfı.
 */
public class YuvarlakTest {

    /**
     * Yuvarlak testleri için test verilerini sağlayan DataProvider metodu.
     * Her satır: { yarıçap, pi değeri, beklenen alan, beklenen çevre }
     *
     * Not: Bazı satırlarda yaklaşık pi değeri (örneğin 3.14 veya 3.0) kullanılmakta.
     *
     * @return test senaryolarını içeren dizi
     */
    @DataProvider(name = "yuvarlakVerisi")
    public Object[][] yuvarlakVerisi() {
        return new Object[][] {
            {1, Math.PI, Math.PI, 2 * Math.PI},       // π * 1^2, 2π * 1
            {2, 3.14, 12.56, 12.56},                  // 3.14 * 2^2 = 12.56, 2*3.14*2 = 12.56
            {3, 3.0, 27.0, 18.0}                      // 3.0 * 3^2 = 27.0, 2*3.0*3 = 18.0
        };
    }

    /**
     * Yuvarlak sınıfının alan ve çevre hesaplama metodlarının, verilen yarıçap ve pi değeriyle
     * doğru sonuçlar ürettiğini test eder.
     *
     * @param yaricap Yarıçap değeri
     * @param pi Test sırasında kullanılacak pi değeri
     * @param beklenenAlan Beklenen alan sonucu
     * @param beklenenCevre Beklenen çevre sonucu
     */
    @Test(dataProvider = "yuvarlakVerisi")
    public void testYuvarlakAlanVeCevre(int yaricap, double pi, double beklenenAlan, double beklenenCevre) {
        // Yuvarlak nesnesi oluşturuluyor
        Yuvarlak yuvarlak = new Yuvarlak(yaricap, pi);

        // Alan hesaplaması kontrol ediliyor (0.01 toleransla)
        assertEquals(yuvarlak.alanHesapla(), beklenenAlan, 0.01, "Alan hatalı!");

        // Çevre hesaplaması kontrol ediliyor (0.01 toleransla)
        assertEquals(yuvarlak.cevreHesapla(), beklenenCevre, 0.01, "Çevre hatalı!");
    }
}

package tr.com.turksat.sekilapp.model;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class YildizTest {

    @DataProvider(name = "yildizVerisi")
    public Object[][] yildizVerisi() {
        return new Object[][] {
            {3, 0, 0},
            {5, 0, 0},
            {7, 0, 0}
        };
    }

    @Test(dataProvider = "yildizVerisi")
    public void testYildizAlanVeCevre(int boyut, int beklenenAlan, int beklenenCevre) {
        Yildiz y = new Yildiz(boyut);
        assertEquals(y.alanHesapla(), beklenenAlan, "Alan hatalı!");
        assertEquals(y.cevreHesapla(), beklenenCevre, "Çevre hatalı!");
    }
} 
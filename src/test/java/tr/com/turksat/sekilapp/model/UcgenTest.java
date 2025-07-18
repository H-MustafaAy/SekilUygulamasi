package tr.com.turksat.sekilapp.model;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class UcgenTest {

    @DataProvider(name = "ucgenVerisi")
    public Object[][] ucgenVerisi() {
        return new Object[][] {
            {3, 4, 6, 12}, // Alan: (3*4)/2=6, Çevre: 3+4+5=12 (dik üçgen)
            {5, 12, 30, 30}, // Alan: (5*12)/2=30, Çevre: 5+12+13=30
            {6, 8, 24, 24} // Alan: (6*8)/2=24, Çevre: 6+8+10=24
        };
    }

    @Test(dataProvider = "ucgenVerisi")
    public void testUcgenAlanVeCevre(int yukseklik, int taban, int beklenenAlan, int beklenenCevre) {
        Ucgen u = new Ucgen(yukseklik, taban);
        assertEquals(u.alanHesapla(), beklenenAlan, "Alan hatalı!");
        assertEquals(u.cevreHesapla(), beklenenCevre, "Çevre hatalı!");
    }
} 
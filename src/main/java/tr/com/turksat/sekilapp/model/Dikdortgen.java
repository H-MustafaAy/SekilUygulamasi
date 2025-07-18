package tr.com.turksat.sekilapp.model;
/**
 * Dikdörtgen sınıfı, Dortgen sınıfından türetilmiş,
 * farklı en ve boya sahip dörtgen şekildir.
 */
public class Dikdortgen extends Dortgen {
    /**
     * Dikdörtgen nesnesi oluşturur.
     *
     * @param en Dikdörtgenin eni
     * @param boy Dikdörtgenin boyu
     */
    public Dikdortgen(int en, int boy) {
        super(en, boy);
    }
    /**
     * Dikdörtgen bilgilerini içeren String döner.
     *
     * @return Dikdörtgenin eni ve boyu ile ilgili açıklayıcı metin
     */
    @Override
    public String toString() {
        return "Dikdörtgen: "+ "en: " +en + " boy: " +boy + "\n";
    }

}

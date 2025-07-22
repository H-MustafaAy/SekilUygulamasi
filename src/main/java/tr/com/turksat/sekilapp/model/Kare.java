package tr.com.turksat.sekilapp.model;

<<<<<<< HEAD
import tr.com.turksat.sekilapp.annotation.TestableClass;

@TestableClass
=======
>>>>>>> c0746bee659456860b0c0d1e61ceb9ef89fdd8a7
public class Kare extends Dortgen {

    /**
     * Kare nesnesi oluşturur. Kenar uzunluğunu parametre olarak alır.
     *
     * @param kenar Karenın bir kenarının uzunluğu
     */
    public Kare(int kenar) {
        super(kenar, kenar);
    }
    /**
     * Karenın bilgilerini içeren String döner.
     * @return Kare bilgisi (kenar uzunluğu)
     */
    @Override
    public String toString() {
        return "Kare : "+ "Kenar: " + en ;
    }
}

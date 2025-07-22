package tr.com.turksat.sekilapp.model;

import tr.com.turksat.sekilapp.annotation.TestableClass;

@TestableClass
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

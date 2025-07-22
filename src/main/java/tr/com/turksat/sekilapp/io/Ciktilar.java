package tr.com.turksat.sekilapp.io;

import tr.com.turksat.sekilapp.model.Sekil;


public class Ciktilar {
    /**
     * Konsola belirtilen mesajı yazdırır.
     *
     * @param mesaj Yazdırılacak metin
     */
    public void mesajYazdir(String mesaj) {
        System.out.println(mesaj);
    }

    /**
     * Parametre olarak verilen Sekil nesnesinin çizim işlemini gerçekleştirir.
     *
     * @param sekil Çizilmek istenen Sekil nesnesi
     */
    public void sekilCiz(Sekil sekil) {

        sekil.ciz();
    }


}

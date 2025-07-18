package tr.com.turksat.sekilapp.model;


import java.util.Set;

/**
 * Soyut temel sınıf. Tüm şekiller bu sınıftan türetilir.
 * Her şeklin bir sembolü vardır (varsayılan "*").
 * Ayrıca şeklin çizilmesi, alan ve çevresinin hesaplanması için soyut metotlar içerir.
 */
public abstract class Sekil {
    protected String sembol = "*";




    /**
     * Şeklin konsola çizimini yapan soyut metot.
     * Alt sınıflar bunu kendilerine göre implement eder.
     */
    public abstract void ciz();
    /**
     * Şeklin alanını hesaplayan soyut metot.
     *
     * @return Alan değeri
     */
    public abstract double alanHesapla();

    /**
     * Şeklin çevresini hesaplayan soyut metot.
     *
     * @return Çevre değeri
     */
    public abstract double cevreHesapla();


    /**
     * Şeklin türünü, alan ve çevre bilgilerini içeren açıklayıcı String döner.
     *
     * @return Şekil bilgisi String
     */
    public String toString() {

        return "Şekil: "+ getClass().getName() +  "  Alan: "+ alanHesapla() + "  Çevre: "+ cevreHesapla() ;

    }
}

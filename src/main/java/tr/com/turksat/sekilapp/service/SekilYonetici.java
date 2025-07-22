package tr.com.turksat.sekilapp.service;

import tr.com.turksat.sekilapp.model.Sekil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SekilYonetici {
    private final List<Sekil> sekiller = new ArrayList<>();
    private double toplamAlan = 0;
    private double toplamCevre = 0;

    private String sembol = "*";

    public static final Set<Character> GECERLI_SEMBOLLER = Set.of('*', '#', '$', '+', '-', '?', '&', 'x');

    /**
     * Verilen Sekil nesnesini listeye ekler ve toplam alan ile çevre değerlerini günceller.
     *
     * @param sekil Listeye eklenecek Sekil nesnesi
     */
    public void sekilEkle(Sekil sekil) {
        setSembol(sembol);  //Yeni eklenen şekle mevcut sembol atanıyor**
        sekiller.add(sekil);
        toplamAlan += sekil.alanHesapla();
        toplamCevre += sekil.cevreHesapla();
        System.out.println("-----------------------------------------");
    }

    /**
     * Listede bulunan tüm Sekil nesnelerini ekrana çizer ve her birinin alan ile çevresini yazdırır.
     */
    public void sekilleriCiz() {
        for (Sekil s : sekiller) {
            s.ciz();
            System.out.println("Alan: " + s.alanHesapla());
            System.out.println("Çevre: " + s.cevreHesapla());
        }
    }

    /**
     * Şu ana kadar eklenen tüm şekillerin toplam alan ve toplam çevre değerlerini ekrana yazdırır.
     */
    public void toplamBilgiGoster() {
        System.out.println("Toplam Alan: " + toplamAlan);
        System.out.println("Toplam Çevre: " + toplamCevre);
    }

    /**
     * Şekil listesini temizler ve toplam alan ile çevre değerlerini sıfırlar.
     * Ardından sıfırlanmış toplam alan ve çevre değerlerini ekrana yazdırır.
     */
    public void resetle() {
        sekiller.clear();
        toplamAlan = 0;
        toplamCevre = 0;
        System.out.println("Toplam Alan: " + toplamAlan);
        System.out.println("Toplam Çevre: " + toplamCevre);
    }

    /**
     * Şeklin çiziminde kullanılacak sembolü belirler.
     * Sembol tek karakter olmalı ve izin verilen semboller arasında olmalı.
     *
     * @param yeniSembol Yeni sembol karakter dizisi
     */
    public void setSembol(String yeniSembol) {
        if (yeniSembol == null || yeniSembol.length() != 1) {
            throw new IllegalArgumentException("Sembol tek karakter olmalı!");
        }

        // İzin verilen sembolleri regex ile belirleyelim:
        String sembolRegex = "^[*#$+\\-?&x]$";

        if (!yeniSembol.matches(sembolRegex)) {
            throw new IllegalArgumentException("Geçersiz sembol! İzin verilen semboller: * # $ + - ? & x");
        }

        this.sembol = yeniSembol;
    }



    /**
     * Yöneticiye eklenmiş olan Sekil nesnelerinin listesini döner.
     *
     * @return Sekil nesnelerinden oluşan liste
     */
    public List<Sekil> getSekiller() {
        return sekiller;
    }
}

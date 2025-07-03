package com.sekiluygulamasi;

import java.util.ArrayList;
import java.util.List;

public class SekilYonetici {
    private  final List<Sekil> sekiller = new ArrayList<>();
    private double toplamAlan = 0;
    private double toplamCevre = 0;

    public void sekilEkle(Sekil sekil) {

        sekiller.add(sekil);
        toplamAlan += sekil.alanHesapla();
        toplamCevre += sekil.cevreHesapla();
        System.out.println("-----------------------------------------");
    }

    public void sekilleriCiz() {
        for (Sekil s : sekiller) {
            s.ciz();
            System.out.println("Alan: " + s.alanHesapla());
          System.out.println("Çevre: " + s.cevreHesapla());
        }
    }

    public void toplamBilgiGoster() {
        System.out.println("Toplam Alan: " + toplamAlan);
        System.out.println("Toplam Çevre: " + toplamCevre);
    }

    public void resetle() {
        sekiller.clear();
        toplamAlan = 0;
        toplamCevre = 0;
        System.out.println("Toplam Alan: " + toplamAlan);
        System.out.println("Toplam Çevre: " + toplamCevre);
    }
    public void sembolDegistir(String yeniSembol) {
        for (Sekil s : sekiller) {
            s.setSembol(yeniSembol);
        }
    }

    public List<Sekil> getSekiller() {
        return sekiller;
    }
}

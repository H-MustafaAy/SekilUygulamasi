package com.sekiluygulamasi.model;

/**
 * Yıldız şekli sınıfı.
 * Boyut parametresi ile kare bir ızgara içinde yıldız şekli çizer.
 * Boyut tek sayı olmalıdır.
 */
public class Yildiz extends Sekil {
    private final int boyut;
    /**
     * Yıldız nesnesi oluşturur.
     *
     * @param boyut Yıldızın boyutu (tek sayı olmalı)
     */
    public Yildiz(int boyut) {
        this.boyut = boyut;
    }
    /**
     * Yıldız şeklini konsola çizer.
     * Boyut çift sayı ise uyarı mesajı verir.
     */
    @Override
    public void ciz() {
        if (boyut % 2 == 0) {
            System.out.println("Lütfen tek bir sayı girin.");

        } else {
            int orta = boyut / 2;
            for (int i = 0; i < boyut; i++) {
                for (int j = 0; j < boyut; j++) {
                    if (i == orta || j == orta || i == j || j == boyut - 1 - i) {
                        System.out.print(sembol);
                    } else {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
        }
    }

    /**
     * Yıldızın alanı hesaplanamaz, bu yüzden 0 döner.
     *
     * @return 0 (hesaplanamaz)
     */
    @Override
    public double alanHesapla() {
        System.out.println("Yıldızın alanını ve çevresini hesaplayamıyoruz.");
        return 0;
    }

    /**
     * Yıldızın çevresi hesaplanamaz, bu yüzden 0 döner.
     *
     * @return 0 (hesaplanamaz)
     */
    @Override
    public double cevreHesapla() {
        return 0;
    }

    /**
     * Yıldızın boyutunu açıklayan String döner.
     *
     * @return Yıldız bilgisi
     */
    @Override
    public String toString() {
        return "Yıldız: Boyut: " + boyut;
    }
}

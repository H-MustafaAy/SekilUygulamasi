package com.sekiluygulamasi;
public class Yildiz extends Sekil {
    private final int boyut;

    public Yildiz(int boyut) {
        this.boyut = boyut;
    }

    @Override
    public void ciz() {
        if (boyut % 2 == 0) {
            System.out.println("Lütfen tek bir sayı girin.");
            return;
        }

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

    @Override
    public double alanHesapla() {
        System.out.println("Yıldızın alanını ve çevresini hesaplayamıyoruz.");
        return 0;
    }

    @Override
    public double cevreHesapla() {
        return 0;
    }
    @Override
    public String toString() {
        return " Yıldız: Boyut: " + boyut;
    }
}












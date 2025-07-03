package com.sekiluygulamasi;
public class Yuvarlak extends Sekil {
    private final int yaricap;
    private final double piSayisi;
    // değişkenler sadece bir kere atanıp sonra değiştirilmediği için final yaptım.

    public Yuvarlak(int yaricap, double piSayisi) {
        this.yaricap = yaricap;
        this.piSayisi = piSayisi;
    }

    @Override
    public void ciz() {
        int cap = yaricap * 2;


        for (int y = 0; y <= cap; y++) {
            for (int x = 0; x <= cap; x++) {
                int dist = (int) Math.sqrt(Math.pow(x - yaricap, 2) + Math.pow(y - yaricap, 2));
                if (dist >= yaricap - 1 && dist <= yaricap + 1) {
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

        return  piSayisi*yaricap *yaricap;
    }

    @Override
    public double cevreHesapla() {

        return  2*piSayisi*yaricap;
    }
    @Override
    public String toString() {
        return "Yuvarlak: yaricap: " + yaricap  + ", piSayisi: " + piSayisi;
    }
}




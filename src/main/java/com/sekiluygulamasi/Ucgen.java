package com.sekiluygulamasi;
public class Ucgen extends Sekil{
    private final int yukseklik;
    private final int taban;

    public Ucgen(int yukseklik, int taban) {
        this.yukseklik = yukseklik;
        this.taban = taban;
    }

    @Override
    public void ciz(){

        for (int i = 0; i <= yukseklik; i++) {
            for (int j = 1; j <= (i*taban / yukseklik); j++) {
                System.out.print(sembol);
            }
            System.out.println();
        }


    }
    /**
     * Üçgenin alanını hesaplar.
     * Alan = (yukseklik * taban) / 2 formülü kullanılır.
     * @return Üçgenin alanı (double)
     */
    @Override
    public double alanHesapla(){
        return (yukseklik * taban)/2;
    }

    /**
     * Üçgenin çevresini hesaplar.
     * double hipotenus = Math.sqrt(Math.pow(yukseklik,2)+ Math.pow(taban,2) ile bulunur.
     * cevre = yukseklik + taban + hipotenus ile hesaplanıyor.
     * @return cevre (double)
     */
    @Override
    public double cevreHesapla(){
        double hipotenus = Math.sqrt(Math.pow(yukseklik, 2) + Math.pow(taban, 2));

        return yukseklik + taban + hipotenus ;
    }

        @Override
    public String toString() {
        return "Üçgen : "+ "yukseklik: " + yukseklik + ", taban: " + taban;
    }
}

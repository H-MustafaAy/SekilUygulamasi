package com.sekiluygulamasi;
public class Dortgen extends Sekil{
    protected int en;
    protected int boy;

    public Dortgen(int en, int boy) {
        this.en = en;
        this.boy = boy;

    }
    @Override
    public double alanHesapla() {
        return en * boy;
    }
    @Override
    public double cevreHesapla() {
        return 2 * (en + boy);
    }
    public void ciz(){
        for (int i= 0; i< boy; i++){
            for (int j= 0; j< en; j++){
                System.out.print(sembol + " ");

            }
            System.out.println();
        }
    }
    @Override
    public String toString() {
        return "Dörtgen : "+ "en: " + en + ", boy: " + boy + "\n";
    }
}

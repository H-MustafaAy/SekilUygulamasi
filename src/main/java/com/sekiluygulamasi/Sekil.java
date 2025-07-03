package com.sekiluygulamasi;
public abstract class Sekil {
    protected String sembol = "*";

    public void setSembol(String sembol) {
        this.sembol = sembol;
    }

    public abstract void ciz();

    public abstract double alanHesapla();

    public abstract double cevreHesapla();


    public String toString() {

        return "Şekil: "+ getClass().getName() +  "  Alan: "+ alanHesapla() + "  Çevre: "+ cevreHesapla() ;

    }
}

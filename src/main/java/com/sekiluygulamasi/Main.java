package com.sekiluygulamasi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        ConfigManager config = new ConfigManager("config.properties");
        String veriKaynagi = config.getVeriKaynagi();
        String dosyaAdi = config.getDosyaAdi();
        System.out.println("Config - veri_kaynagi: " + veriKaynagi);
        System.out.println("Config - dosya_adi: " + dosyaAdi);



        Girdiler girdiler = new Girdiler();
        Ciktilar ciktilar = new Ciktilar();
        SekilYonetici yonetici = new SekilYonetici();
        String sembol = "*";

        // Polimorfik Gson nesnesi oluşturuluyor
        RuntimeTypeAdapterFactory<Sekil> sekilAdapterFactory = RuntimeTypeAdapterFactory
                .of(Sekil.class, "type")
                .registerSubtype(Ucgen.class, "Ucgen")
                .registerSubtype(Yildiz.class, "Yildiz")
                .registerSubtype(Yuvarlak.class, "Yuvarlak")
                .registerSubtype(Dikdortgen.class, "Dikdortgen")
                .registerSubtype(Kare.class, "Kare");

        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(sekilAdapterFactory)
                .setPrettyPrinting()
                .create();

        ciktilar.mesajYazdir("Şekil Uygulamasına Hoşgeldiniz.");

        while (true) {

            ciktilar.mesajYazdir("Komutlar:");
            ciktilar.mesajYazdir("  test        - Uygulamanın çalışıp çalışmadığını test eder.");
            ciktilar.mesajYazdir("  şekilçiz    - Yeni bir şekil çizer (Üçgen, Yıldız, Yuvarlak, Dikdörtgen, Kare).");
            ciktilar.mesajYazdir("  setSembol   - Çizimde kullanılacak karakteri (sembolü) belirler.");
            ciktilar.mesajYazdir("  toplam      - Tüm şekillerin toplam alan ve çevresini gösterir.");
            ciktilar.mesajYazdir("  reset       - Alan ve çevre bilgilerini sıfırlar.");
            ciktilar.mesajYazdir("  yukle       - Daha önce kaydedilen şekilleri JSON dosyasından yükler.");
            ciktilar.mesajYazdir("  kaydet      - Mevcut şekilleri JSON formatında dosyaya kaydeder.");
            ciktilar.mesajYazdir("  temizle     - Kaydedilen dosyayı temizler (verileri siler).");
            ciktilar.mesajYazdir("  exit        - Programdan çıkış yapar.");

            ciktilar.mesajYazdir("İşlem seçiniz: ");
            String islem1 = girdiler.satirOku().trim().toLowerCase();

            if (islem1.equals("şekilçiz")) {
                ciktilar.mesajYazdir("Şekil seçiniz: (1) Üçgen, (2) Yıldız, (3) Yuvarlak, (4) Dikdörtgen, (5) Kare");
                String secim = girdiler.satirOku().trim();

                switch (secim) {
                    case "1": {
                        int yukseklik, taban;
                        while (true) {
                            try {
                                ciktilar.mesajYazdir("Yükseklik: ");
                                yukseklik = girdiler.sayiOku();
                                ciktilar.mesajYazdir("Taban: ");
                                taban = girdiler.sayiOku();

                                if (yukseklik <= 0 || taban <= 0) {
                                    ciktilar.mesajYazdir("Lütfen pozitif tam sayılar giriniz!");
                                    continue;
                                }
                                break;
                            } catch (NumberFormatException e) {
                                ciktilar.mesajYazdir("Lütfen geçerli bir tam sayı giriniz!");
                            }
                        }
                        Ucgen u = new Ucgen(yukseklik, taban);
                        u.setSembol(sembol);
                        ciktilar.sekilCiz(u);
                        ciktilar.mesajYazdir("Alan: " + u.alanHesapla());
                        ciktilar.mesajYazdir("Çevre: " + u.cevreHesapla());
                        yonetici.sekilEkle(u);
                        break;
                    }
                    case "2": {
                        boolean gecerli = false;
                        while (!gecerli) {
                            try {
                                ciktilar.mesajYazdir("Yıldız boyutu (tek sayı): ");
                                int boyut = girdiler.sayiOku();
                                if (boyut % 2 == 0) {
                                    ciktilar.mesajYazdir("Lütfen tek bir sayı girin.");
                                    continue;
                                }
                                Yildiz y = new Yildiz(boyut);
                                y.setSembol(sembol);

                                ciktilar.sekilCiz(y);
                                ciktilar.mesajYazdir("Alan: " + y.alanHesapla());
                                ciktilar.mesajYazdir("Çevre: " + y.cevreHesapla());
                                yonetici.sekilEkle(y);
                                gecerli = true;
                            } catch (NumberFormatException e) {
                                ciktilar.mesajYazdir("Lütfen integer bir sayı giriniz...");
                            }
                        }
                        break;
                    }
                    case "3": {
                        boolean gecerli = false;
                        while (!gecerli) {
                            try {
                                ciktilar.mesajYazdir("Yarıçap: ");
                                int yaricap = girdiler.sayiOku();
                                ciktilar.mesajYazdir("Pi sayısı: ");
                                double piSayisi = girdiler.doubleOku();

                                Yuvarlak yuvarlak = new Yuvarlak(yaricap, piSayisi);
                                yuvarlak.setSembol(sembol);
                                ciktilar.sekilCiz(yuvarlak);
                                ciktilar.mesajYazdir("Alan: " + yuvarlak.alanHesapla());
                                ciktilar.mesajYazdir("Çevre: " + yuvarlak.cevreHesapla());
                                yonetici.sekilEkle(yuvarlak);
                                gecerli = true;
                            } catch (NumberFormatException e) {
                                ciktilar.mesajYazdir("Lütfen geçerli sayı formatında veri giriniz!");
                            }
                        }
                        break;
                    }
                    case "4": {
                        boolean gecerli = false;
                        while (!gecerli) {
                            try {
                                ciktilar.mesajYazdir("En: ");
                                int en = girdiler.sayiOku();
                                ciktilar.mesajYazdir("Boy: ");
                                int boy = girdiler.sayiOku();

                                Dikdortgen d = new Dikdortgen(en, boy);
                                d.setSembol(sembol);
                                ciktilar.sekilCiz(d);
                                ciktilar.mesajYazdir("Alan: " + d.alanHesapla());
                                ciktilar.mesajYazdir("Çevre: " + d.cevreHesapla());
                                yonetici.sekilEkle(d);
                                gecerli = true;
                            } catch (NumberFormatException e) {
                                ciktilar.mesajYazdir("Lütfen geçerli bir tam sayı giriniz!");
                            }
                        }
                        break;
                    }
                    case "5": {
                        boolean gecerli = false;
                        while (!gecerli) {
                            try {
                                ciktilar.mesajYazdir("Kenar: ");
                                int kenar = girdiler.sayiOku();
                                Kare k = new Kare(kenar);
                                k.setSembol(sembol);
                                ciktilar.sekilCiz(k);
                                ciktilar.mesajYazdir("Alan: " + k.alanHesapla());
                                ciktilar.mesajYazdir("Çevre: " + k.cevreHesapla());
                                yonetici.sekilEkle(k);
                                gecerli = true;
                            } catch (NumberFormatException e) {
                                ciktilar.mesajYazdir("Lütfen geçerli bir tam sayı giriniz!");
                            }
                        }
                        break;
                    }
                    default:
                        ciktilar.mesajYazdir("Geçersiz seçim.");
                        break;
                }
            } else {
                switch (islem1) {
                    case "test":
                        ciktilar.mesajYazdir("Program çalışıyor.");
                        break;

                    case "toplam":
                        yonetici.toplamBilgiGoster();
                        break;

                    case "reset":
                        yonetici.resetle();
                        ciktilar.mesajYazdir("Alan ve çevre bilgileri sıfırlandı.");
                        break;

                    case "setsembol":
                        ciktilar.mesajYazdir("Yeni sembol: ");
                        sembol = girdiler.satirOku();
                        yonetici.sembolDegistir(sembol);
                        ciktilar.mesajYazdir("Sembol değiştirildi: " + sembol);
                        break;

                    case "yukle":
                        try {
                            List<Sekil> sekiller;
                            if (veriKaynagi.equalsIgnoreCase("json")) {
                                sekiller = DosyaIslemleri.sekilleriJsonYukle(dosyaAdi, gson);
                            } else if (veriKaynagi.equalsIgnoreCase("txt")) {
                                sekiller = DosyaIslemleri.sekilleriTxtYukle(dosyaAdi);
                            } else {
                                ciktilar.mesajYazdir("Bilinmeyen veri kaynağı: " + veriKaynagi);
                                break;
                            }
                            yonetici.getSekiller().clear();
                            for (Sekil s : sekiller) {
                                yonetici.sekilEkle(s);
                            }
                            ciktilar.mesajYazdir("Şekiller yüklendi ve çiziliyor...");
                            yonetici.sekilleriCiz();
                        } catch (IOException e) {
                            ciktilar.mesajYazdir("Dosya yükleme hatası: " + e.getMessage());
                        }
                        break;

                    case "kaydet":
                        try {
                            if (veriKaynagi.equalsIgnoreCase("json")) {
                                DosyaIslemleri.sekilleriJsonKaydet(dosyaAdi, yonetici.getSekiller(), gson);
                            } else if (veriKaynagi.equalsIgnoreCase("txt")) {
                                DosyaIslemleri.sekilleriTxtKaydet(dosyaAdi, yonetici.getSekiller());
                            } else {
                                ciktilar.mesajYazdir("Bilinmeyen veri kaynağı: " + veriKaynagi);
                                break;
                            }
                            ciktilar.mesajYazdir("Şekiller dosyaya kaydedildi.");
                        } catch (IOException e) {
                            ciktilar.mesajYazdir("Dosya kaydetme hatası: " + e.getMessage());
                        }
                        break;

                    case "temizle":
                        try {
                            DosyaIslemleri.dosyayiTemizle(dosyaAdi);
                            yonetici.resetle();
                            ciktilar.mesajYazdir("Tüm şekil verileri temizlendi.");
                        } catch (IOException e) {
                            ciktilar.mesajYazdir("Dosya temizlenirken bir hata oluştu: " + e.getMessage());
                        }
                        break;

                    case "exit":
                        ciktilar.mesajYazdir("Programdan çıkılıyor.");
                        System.exit(0);
                        break;

                    default:
                        ciktilar.mesajYazdir("Bilinmeyen işlem.");
                        break;
                }
            }
        }
    }
}

package tr.com.turksat.sekilapp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import tr.com.turksat.sekilapp.io.Ciktilar;
import tr.com.turksat.sekilapp.io.DosyaIslemleri;
import tr.com.turksat.sekilapp.io.Girdiler;
import tr.com.turksat.sekilapp.io.RuntimeTypeAdapterFactory;
//import com.sekilapp.model.*;
import tr.com.turksat.sekilapp.model.*;
import tr.com.turksat.sekilapp.service.SekilYonetici;
import tr.com.turksat.sekilapp.util.ConfigManager;

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
            ciktilar.mesajYazdir("  TEST için -> 1                 - Uygulamanın çalışıp çalışmadığını test eder.");
            ciktilar.mesajYazdir("  ŞEKİL ÇİZİMİ içim -> 2         - Yeni bir şekil çizer (Üçgen, Yıldız, Yuvarlak, Dikdörtgen, Kare).");
            ciktilar.mesajYazdir("  SEMBOL DEĞİŞTİRMEK için -> 3   - Çizimde kullanılacak karakteri (sembolü) belirler.");
            ciktilar.mesajYazdir("  TOPLAM içim -> 4               - Tüm şekillerin toplam alan ve çevresini gösterir.");
            ciktilar.mesajYazdir("  RESET İÇİM -> 5                - Alan ve çevre bilgilerini sıfırlar.");
            ciktilar.mesajYazdir("  YÜKLE içim -> 6                - Daha önce kaydedilen şekilleri JSON dosyasından yükler.");
            ciktilar.mesajYazdir("  KAYDET içim -> 7               - Mevcut şekilleri JSON formatında dosyaya kaydeder.");
            ciktilar.mesajYazdir("  TEMİZLE için -> 8              - Kaydedilen dosyayı temizler (verileri siler).");
            ciktilar.mesajYazdir("  EXIT için -> 9                 - Programdan çıkış yapar.");

            ciktilar.mesajYazdir("İşlem seçiniz: ");

            String islem1 = girdiler.satirOku().trim();

            // 1-9 arası seçim regex ile kontrol ediliyor
            if (!islem1.matches("^[1-9]$")) {
                ciktilar.mesajYazdir("Geçersiz seçim. Lütfen 1 ile 9 arasında bir sayı girin:");
                continue;
            }

            if (islem1.equals("2")) {
                ciktilar.mesajYazdir("Şekil seçiniz: (1) Üçgen, (2) Yıldız, (3) Yuvarlak, (4) Dikdörtgen, (5) Kare");

                while (true) {
                   String secim = girdiler.satirOku().trim();

                    // Regex: sadece 1, 2, 3, 4 veya 5 kabul edilir
                    secim = secim.trim(); // baştaki ve sondaki boşlukları temizle

                    if (!secim.matches("^[1-5]$")) {
                        ciktilar.mesajYazdir("Geçersiz seçim. Lütfen 1 ile 5 arasında bir sayı girin:");
                        continue;
                    }


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
                            yonetici.setSembol(sembol);
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
                                    yonetici.setSembol(sembol);

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
                                    yonetici.setSembol(sembol);
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
                                    yonetici.setSembol(sembol);
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
                                    yonetici.setSembol(sembol);
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
                            ciktilar.mesajYazdir(" Geçersiz seçim.");
                            return;
                    } break;
                }
            }
            else{
                    switch (islem1) {
                        case"1":

                            ciktilar.mesajYazdir("Testler başlatılıyor...");
                            // Tüm @TestableClass sınıflar ve uygun parametreler
                            Class<?>[] testClasses = new Class<?>[] {
                                tr.com.turksat.sekilapp.model.Kare.class,
                                tr.com.turksat.sekilapp.model.Dikdortgen.class,
                                tr.com.turksat.sekilapp.model.Dortgen.class,
                                tr.com.turksat.sekilapp.model.Ucgen.class,
                                tr.com.turksat.sekilapp.model.Yuvarlak.class
                            };
                            Object[][][] testParams = new Object[][][] {
                                { new Object[]{5}, new Object[]{10} }, // Kare(kenar)
                                { new Object[]{3, 4}, new Object[]{7, 2} }, // Dikdortgen(en, boy)
                                { new Object[]{6, 8}, new Object[]{2, 2} }, // Dortgen(en, boy)
                                { new Object[]{5, 12}, new Object[]{3, 4} }, // Ucgen(yükseklik, taban)
                                { new Object[]{7, 3.14}, new Object[]{5, 3.0} } // Yuvarlak(yarıçap, pi)
                            };
                            String testSonuclari = tr.com.turksat.sekilapp.util.TestRunner.runTests(testClasses, testParams);
                            ciktilar.mesajYazdir(testSonuclari);
                            ciktilar.mesajYazdir("Testler tamamlandı.");
                            ciktilar.mesajYazdir("Program çalışıyor.");

                            break;

                        case "4":
                            yonetici.toplamBilgiGoster();
                            break;

                        case "5":
                            yonetici.resetle();
                            ciktilar.mesajYazdir("Alan ve çevre bilgileri sıfırlandı.");
                            break;

                        case "3":
                            while (true) {
                                ciktilar.mesajYazdir("Yeni sembol (izin verilenler: " + SekilYonetici.GECERLI_SEMBOLLER + "): ");
                                String yeniSembol = girdiler.satirOku().trim();
                                try {
                                    yonetici.setSembol(yeniSembol);
                                    sembol = yeniSembol;
                                    ciktilar.mesajYazdir("Sembol değiştirildi: " + sembol);
                                    break;  // Başarılıysa döngüden çık
                                } catch (IllegalArgumentException e) {
                                    ciktilar.mesajYazdir("Hata: " + e.getMessage() + " Lütfen tekrar deneyin.");
                                    // Döngü devam edecek ve tekrar sembol istenecek
                                }
                            }
                            break;
                        case "6":
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

                        case "7":
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

                        case "8":
                            try {
                                DosyaIslemleri.dosyayiTemizle(dosyaAdi);
                                yonetici.resetle();
                                ciktilar.mesajYazdir("Tüm şekil verileri temizlendi.");
                            } catch (IOException e) {
                                ciktilar.mesajYazdir("Dosya temizlenirken bir hata oluştu: " + e.getMessage());
                            }
                            break;

                        case "9":
                            ciktilar.mesajYazdir("Programdan çıkılıyor.");
                            System.exit(0);
                            break;

                        default:
                            ciktilar.mesajYazdir("Bilinmeyen işlem.  Lütfen Geçerli Bir Tam Sayı Giriniz");
                            break;
                    }
                }
            }
        }
    }


package com.sekiluygulamasi;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SekilYonetici yonetici = new SekilYonetici();
        String sembol = "*";

        System.out.println("Şekil Uygulamasına Hoşgeldiniz.");
        System.out.println("Komutlar:");
        System.out.println("test          : Uygulamanın çalıştığını test eder");
        System.out.println("şekilçiz:     : İstediğiniz şekili çizer");
        System.out.println("setSembol     : Çizimde Kullanılan sembolu değiştirir.");
        System.out.println("toplam        : Toplam alan ve çevre gösterilir");
        System.out.println("reset         : Alan ve çevre sıfırlanır");
        System.out.println("yukle         : sekiller.txt dosyasından şekiller yüklenir ve txt dosyasında bulunan şekilleri çizer.");
        System.out.println("kaydet        : sekiller.txt dosyasına kaydedilir");
        System.out.println("temizle       : Listeyi sıfırlar");
        System.out.println("exit          : Çıkış yapar");

        while (true) {



            System.out.println("Lütfen Yapmak İstediğiniz işlemi seçiniz:");
            String islem1 = scanner.nextLine();
            // kullanıcının yapmak istediği işlem yakalanıyor.


            if (islem1.equals("şekilçiz")) {

                System.out.println("Lütfen Çizmek İstediğiniz Şekli Seçiniz");
                System.out.print("(Üçgen için (1)  - Yıldız için (2) - Yuvarlak için (3)  - Dikdörtgen için(4) - Kare için (5)) : ");
                String islem2 = scanner.nextLine().trim();
                // kullanıcının çizmek istediği şekil yakalanıyor.
                switch (islem2) {
                    case "1":
                        System.out.print("Yükseklik Bilginiz Giriniz:");
                        int yukseklik = scanner.nextInt();
                        // yükseklik bilgisi yakalandı.
                        scanner.nextLine(); // enterı temizliyoruz
                        System.out.print("Taban bilginizi giriniz: ");
                        int taban =scanner.nextInt();
                        //taban bilgisi yakalandı.
                        scanner.nextLine(); //
                        Ucgen u = new Ucgen(yukseklik, taban);
                        //yeni bir nesne oluşturuluyor
                       u.setSembol(sembol);
                       // sembol değişiyor.
                        u.ciz();
                        // şekil çiziliyor.
                        System.out.println("Alan: " + u.alanHesapla());
                        System.out.println("Çevre: " + u.cevreHesapla());
                        yonetici.sekilEkle(u);
                        // şeklin alan ve çevre bilgisi
                        break;
                    case "2":
                        System.out.println("Yıldızın boyutu için TEK bir değer giriniz:");
                        int boyut = scanner.nextInt();
                        scanner.nextLine(); // enterı temizliyoruz
                        Yildiz y = new Yildiz(boyut);
                        y.setSembol(sembol);
                        y.ciz();
                        System.out.println("Alan: " + y.alanHesapla());
                        System.out.println("Çevre: " + y.cevreHesapla());
                        yonetici.sekilEkle(y);

                        break;
                    case "3":
                        System.out.println("Yarıçap bilgisini giriniz:");
                        int yaricap =scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Pi sayısı değerini giriniz:.");
                        double piSayisi = scanner.nextDouble();
                        scanner.nextLine();
                        Yuvarlak yuvarlak = new Yuvarlak(yaricap,piSayisi);
                        yuvarlak.setSembol(sembol);
                        yuvarlak.ciz();
                        System.out.println("Alan: " + yuvarlak.alanHesapla());
                        System.out.println("Çevre: " + yuvarlak.cevreHesapla());
                        yonetici.sekilEkle(yuvarlak);

                        break;
                    case "4":
                        System.out.println("En - Boy bilgilerini alt alta giriniz:");
                        int en = scanner.nextInt();
                        int boy = scanner.nextInt();
                        scanner.nextLine();
                        Dikdortgen d = new Dikdortgen(en, boy);
                        d.setSembol(sembol);
                        d.ciz();
                        System.out.println("Alan: " + d.alanHesapla());
                        System.out.println("Çevre: " + d.cevreHesapla());
                        yonetici.sekilEkle(d);

                        break;
                    case "5":
                        System.out.print("Karenin kenarını giriniz.");
                        int kenar = scanner.nextInt();
                        scanner.nextLine();
                        Kare k = new Kare(kenar);
                        k.setSembol(sembol);
                        k.ciz();
                        System.out.println("Alan: " + k.alanHesapla());
                        System.out.println("Çevre: " + k.cevreHesapla());
                        yonetici.sekilEkle(k);
                        break;
                    default:
                        System.out.println("Geçersiz Şekil...");
                        break;

                }
            }

            else{
                    switch (islem1) {
                        case "test":
                            System.out.println("Program Çalışıyor....");
                            break;
                        case "exit":
                            System.out.println("Programdan çıkılıyor...");
                            System.exit(0);
                            break;
                        case "toplam":
                            yonetici.toplamBilgiGoster();
                            break;
                            case "reset":
                            yonetici.resetle();
                            break;
                        case "yukle":
                            DosyaIslemleri.sekilleriYukle("sekiller.txt", yonetici);
                            yonetici.sekilleriCiz();
                            break;
                        case "kaydet":

                            DosyaIslemleri.sekilleriKaydet("sekiller.txt", yonetici.getSekiller());
                            break;
                        case "temizle":
                                DosyaIslemleri.dosyayiTemizle("sekiller.txt");
                                System.out.println("Dosya içeriği temizlendi.");


                            break;
                        case "setSembol":
                            System.out.println("Lütfen yeni sembolü giriniz:");
                            sembol= scanner.nextLine();
                            yonetici.sembolDegistir(sembol);
                            System.out.println("Yeni sembol " + sembol + " olarak ayarlandı.");
                            break;
                      default:
                          System.out.println("Geçersiz İşlem Seçtiniz..");
                            break;


                    }
                }

        }
    }
}


// terminalden çalıştırmak için projenin olduğu dizine gel
//mvn compile
//mvn exec:java
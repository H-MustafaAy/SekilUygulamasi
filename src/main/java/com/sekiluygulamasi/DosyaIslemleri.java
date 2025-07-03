package com.sekiluygulamasi;
import java.io.*;
import java.util.Scanner;
import java.util.List;


public class DosyaIslemleri {

    public static void dosyayiTemizle(String dosyaAdi) {
        try (PrintWriter writer = new PrintWriter(dosyaAdi)) {
            writer.print("");
            //hatatı aşmak için yazdım.
        } catch (IOException e) {
            System.out.println("Dosya temizlenirken hata oluştu: " + e.getMessage());
        }

    }


    public static void sekilleriKaydet(String dosyaAdi, List<Sekil> sekiller) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dosyaAdi))) {
            for (Sekil s : sekiller) {
                writer.write(s.toString());
                writer.newLine();
            }
            System.out.println("Şekiller dosyaya kaydedildi.");
        } catch (IOException e) {
            System.out.println("Hata: " + e.getMessage());
        }
    }

    public static void sekilleriYukle(String dosyaAdi, SekilYonetici yonetici) {
        try (Scanner scanner = new Scanner(new File(dosyaAdi))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println();
                String[] parcalar = line.split(" ");
                String tip = parcalar[0];

                switch (tip) {
                    case "Kare":
                        int kenar = Integer.parseInt(parcalar[3]);
                        // boşluklara göre parçalandığında kenar değeri 3.indekste oluyor.
                        yonetici.sekilEkle(new Kare(kenar));
                        break;
                    case "Ucgen":
                        int yukseklik = Integer.parseInt(parcalar[1]);
                        int taban = Integer.parseInt(parcalar[2]);
                        yonetici.sekilEkle(new Ucgen(yukseklik, taban));
                        break;
                    case "Dikdortgen":
                        int en = Integer.parseInt(parcalar[1]);
                        int boy = Integer.parseInt(parcalar[2]);
                        yonetici.sekilEkle(new Dikdortgen(en, boy));
                        break;
                    case "Yildiz":
                        int boyut = Integer.parseInt(parcalar[1]);
                        yonetici.sekilEkle(new Yildiz(boyut));
                        break;
                    case "Yuvarlak":
                        int yaricap = Integer.parseInt(parcalar[1]);
                        double piSayisi = Double.parseDouble(parcalar[2]);
                        yonetici.sekilEkle(new Yuvarlak(yaricap, piSayisi));
                        break;
                    default:

                        break;
                }

            }
            System.out.println("Şekiller dosyadan yüklendi.");
        } catch (IOException e) {
            System.out.println("Dosya okunamadı: " + e.getMessage());
        }
    }

}

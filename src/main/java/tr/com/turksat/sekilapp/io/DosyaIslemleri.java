package tr.com.turksat.sekilapp.io;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
//import com.sekiluygulamasi.model.*;
import tr.com.turksat.sekilapp.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DosyaIslemleri {

    // JSON kaydet ve yükle metodları (mevcut)
    public static void sekilleriJsonKaydet(String dosyaAdi, List<Sekil> sekiller, Gson gson) throws IOException {
        try (Writer writer = new FileWriter(dosyaAdi)) {
            gson.toJson(sekiller, writer);
        }
    }
    /**
     * Belirtilen JSON dosyasından şekil listesini yükler.
     *
     * @param dosyaAdi JSON formatındaki dosyanın yolu ve adı
     * @param gson     Gson nesnesi, JSON serileştirme ve deserileştirme işlemleri için
     * @return JSON dosyasından okunan Sekil nesnelerinden oluşan liste
     * @throws IOException Dosya okuma sırasında oluşabilecek hatalar
     */
    public static List<Sekil> sekilleriJsonYukle(String dosyaAdi, Gson gson) throws IOException {
        try (Reader reader = new FileReader(dosyaAdi)) {
            return gson.fromJson(reader, new TypeToken<List<Sekil>>(){}.getType());
        }
    }

    /**
     * Belirtilen dosyanın içeriğini tamamen temizler (boşaltır).
     *
     * @param dosyaAdi Temizlenecek dosyanın yolu ve adı
     * @throws IOException Dosya işlemi sırasında oluşabilecek hatalar
     */
    public static void dosyayiTemizle(String dosyaAdi) throws IOException {
        try (PrintWriter writer = new PrintWriter(dosyaAdi)) {
            writer.print("");
        }
    }
    /**
     * Verilen şekil listesini belirtilen dosyaya TXT formatında kaydeder.
     * Her şekil, `toString()` metodu kullanılarak satır satır yazılır.
     *
     * @param dosyaAdi  Kaydedilecek dosyanın yolu ve adı
     * @param sekiller  Kaydedilecek Sekil nesnelerinin listesi
     * @throws IOException  Dosya yazma sırasında oluşabilecek hatalar
     */
    public static void sekilleriTxtKaydet(String dosyaAdi, List<Sekil> sekiller) throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dosyaAdi))) {
            for (Sekil s : sekiller) {
                writer.write(s.toString());
                writer.newLine();
            }
        }
    }

    /**
     * Belirtilen TXT dosyasından şekil bilgilerini okuyarak Sekil nesneleri listesi oluşturur.
     * Dosya satır satır okunur, her satır `parseSekilFromString` metodu ile Sekil nesnesine dönüştürülür.
     * Geçersiz veya parse edilemeyen satırlar atlanır.
     *
     * @param dosyaAdi Okunacak TXT dosyasının yolu ve adı
     * @return Dosyadan başarılı şekilde oluşturulan Sekil nesnelerinden oluşan liste
     * @throws IOException Dosya okuma sırasında oluşabilecek hatalar
     */
    public static List<Sekil> sekilleriTxtYukle(String dosyaAdi) throws IOException {
        List<Sekil> sekiller = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(dosyaAdi))) {
            String satir;
            while ((satir = reader.readLine()) != null) {
                Sekil s = parseSekilFromString(satir);
                if (s != null) {
                    sekiller.add(s);
                }
            }
        }
        return sekiller;
    }

    /**
     * Satırdaki metin bilgisinden uygun Sekil nesnesini oluşturur.
     * Satırın başlangıcına göre şeklin türünü belirler ve ilgili parametreleri
     * metinden çıkararak nesneyi yaratır.
     *
     * @param satir Tek bir satır halinde gelen şekil bilgisi
     * @return Oluşturulan Sekil nesnesi veya parse edilemezse null
     */
    private static Sekil parseSekilFromString(String satir) {
        try {
            if (satir.startsWith("Kare")) {
                int kenar = Integer.parseInt(satir.replaceAll("[^0-9]", ""));
                return new Kare(kenar);
            } else if (satir.startsWith("Dörtgen") || satir.startsWith("Dikdortgen")) {
                String[] parts = satir.split(",");
                int en = Integer.parseInt(parts[0].replaceAll("[^0-9]", ""));
                int boy = Integer.parseInt(parts[1].replaceAll("[^0-9]", ""));
                return new Dikdortgen(en, boy);
            } else if (satir.startsWith("Ucgen")) {
                String[] parts = satir.split(",");
                int yukseklik = Integer.parseInt(parts[0].replaceAll("[^0-9]", ""));
                int taban = Integer.parseInt(parts[1].replaceAll("[^0-9]", ""));
                return new Ucgen(yukseklik, taban);
            } else if (satir.startsWith("Yıldız") || satir.startsWith("Yildiz")) {
                int boyut = Integer.parseInt(satir.replaceAll("[^0-9]", ""));
                return new Yildiz(boyut);
            } else if (satir.startsWith("Yuvarlak")) {
                String[] parts = satir.split(",");
                int yaricap = Integer.parseInt(parts[0].replaceAll("[^0-9]", ""));
                double piSayisi = Double.parseDouble(parts[1].replaceAll("[^0-9.]", ""));
                return new Yuvarlak(yaricap, piSayisi);
            }
        } catch (Exception e) {
            System.out.println("Satır parse edilemedi: " + satir);
        }
        return null;
    }
}

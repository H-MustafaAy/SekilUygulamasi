package com.sekiluygulamasi;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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

    public static List<Sekil> sekilleriJsonYukle(String dosyaAdi, Gson gson) throws IOException {
        try (Reader reader = new FileReader(dosyaAdi)) {
            return gson.fromJson(reader, new TypeToken<List<Sekil>>(){}.getType());
        }
    }

    public static void dosyayiTemizle(String dosyaAdi) throws IOException {
        try (PrintWriter writer = new PrintWriter(dosyaAdi)) {
            writer.print("");
        }
    }

    // TXT formatında şekilleri kaydet
    public static void sekilleriTxtKaydet(String dosyaAdi, List<Sekil> sekiller) throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dosyaAdi))) {
            for (Sekil s : sekiller) {
                writer.write(s.toString());
                writer.newLine();
            }
        }
    }

    // TXT formatından şekilleri yükle
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

    // Satırdan Sekil nesnesi oluşturan yardımcı metot
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

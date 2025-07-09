package com.sekiluygulamasi.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Uygulama yapılandırma dosyasını (config.properties) okumak için kullanılan yardımcı sınıf.
 * <p>
 * Bu sınıf, dosyadan veri kaynağı türü (ör. json, txt) ve dosya adı gibi ayarları okur.
 */
public class ConfigManager {
    private final Properties properties = new Properties();
    /**
     * ConfigManager nesnesi oluşturur ve belirtilen yapılandırma dosyasını yükler.
     *
     * @param dosyaYolu Yapılandırma (config) dosyasının yolu
     */
    public ConfigManager(String dosyaYolu) {
        try (FileInputStream input = new FileInputStream(dosyaYolu)) {
            properties.load(input);
        } catch (IOException e) {
            System.out.println("Config dosyası okunamadı: " + e.getMessage());
        }
    }
    /**
     * Veri kaynağının türünü döner. Örn: "txt", "json".
     *
     * @return veri_kaynagi anahtarına karşılık gelen değer. Bulunamazsa "txt" döner.
     */
    public String getVeriKaynagi() {

        return properties.getProperty("veri_kaynagi", "txt");
    }
    /**
     * Şekillerin yazılacağı veya okunacağı dosya adını döner.
     *
     * @return dosya_adi anahtarına karşılık gelen değer. Bulunamazsa "sekiller.txt" döner.
     */
    public String getDosyaAdi() {
        return properties.getProperty("dosya_adi", "sekiller.txt");
    }
}

package com.sekiluygulamasi;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    private final Properties properties = new Properties();

    public ConfigManager(String dosyaYolu) {
        try (FileInputStream input = new FileInputStream(dosyaYolu)) {
            properties.load(input);
        } catch (IOException e) {
            System.out.println("Config dosyası okunamadı: " + e.getMessage());
        }
    }

    public String getVeriKaynagi() {

        return properties.getProperty("veri_kaynagi", "txt");
    }

    public String getDosyaAdi() {
        return properties.getProperty("dosya_adi", "sekiller.txt");
    }
}

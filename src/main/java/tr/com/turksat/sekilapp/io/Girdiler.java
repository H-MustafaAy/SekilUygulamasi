package tr.com.turksat.sekilapp.io;

import java.util.Scanner;

public class Girdiler {
    private final Scanner scanner;
    /**
     * Girdiler sınıfının yapıcısı.
     * Konsoldan veri almak için Scanner nesnesi oluşturur.
     */
    public Girdiler() {
        scanner = new Scanner(System.in);
    }
    /**
     * Konsoldan bir satır metin okur ve döner.
     *
     * @return Okunan satır metni
     */
    public String satirOku() {

        return scanner.nextLine();
    }
    /**
     * Konsoldan bir satır okur ve bunu tam sayıya dönüştürür.
     *
     * @return Okunan tam sayı değeri
     * @throws NumberFormatException Eğer okunan satır geçerli bir tam sayı değilse fırlatılır
     */
    public int sayiOku() throws NumberFormatException {
        return Integer.parseInt(satirOku());
    }

    /**
     * Konsoldan bir satır okur ve bunu double (ondalıklı sayı) tipine dönüştürür.
     *
     * @return Okunan double değer
     * @throws NumberFormatException Okunan değer geçerli bir double değilse fırlatılır
     */
    public double doubleOku() throws NumberFormatException {
        return Double.parseDouble(satirOku());
    }
}

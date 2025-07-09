package com.sekiluygulamasi;

import java.util.Scanner;

public class Girdiler {
    private final Scanner scanner;

    public Girdiler() {
        scanner = new Scanner(System.in);
    }

    public String satirOku() {
        return scanner.nextLine();
    }

    public int sayiOku() throws NumberFormatException {
        return Integer.parseInt(satirOku());
    }

    public double doubleOku() throws NumberFormatException {
        return Double.parseDouble(satirOku());
    }
}

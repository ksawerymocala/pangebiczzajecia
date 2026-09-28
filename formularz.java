package com.ksawery.gebicz;

import java.util.Scanner;

public class formularz {
    public static void main(String[] args) {
        Scanner skaner = new Scanner(System.in);

        System.out.print("Wpisz imie: ");
        String imie = skaner.nextLine();

        System.out.print("Wpisz nazwisko: ");
        String nazwisko = skaner.nextLine();

        System.out.print("Wpisz email: ");
        String email = skaner.nextLine();

        System.out.print("Wpisz haslo: ");
        String haslo = skaner.nextLine();

        System.out.println("\n--- PODSUMOWANIE ---\n");

        walidujPole(imie);
        walidujPole(nazwisko);
        walidujEmail(email);
        walidujHaslo(haslo);
    }

    public static void walidujPole(String wpis) {
        if (!wpis.isEmpty()) {
            System.out.println("Wpisano: " + wpis);
        } else {
            System.out.println("Brak danych (pole puste)");
        }
    }

    public static void walidujEmail(String wpis) {
        if (wpis.contains("@") && wpis.contains(".")) {
            System.out.println("Email: " + wpis);
        } else {
            System.out.println("Błąd: Email musi posiadać znaki '@' oraz '.'");
        }
    }

    public static void walidujHaslo(String wpis) {
        boolean warunekDlugosc = wpis.length() >= 8;
        boolean warunekMala = wpis.matches(".*[a-z].*");
        boolean warunekDuza = wpis.matches(".*[A-Z].*");
        boolean warunekCyfra = wpis.matches(".*[0-9].*");

        if (warunekDlugosc && warunekMala && warunekDuza && warunekCyfra) {
            System.out.println("Hasło ustawione poprawnie.");
        } else {
            System.out.println("Błąd: Hasło musi mieć min. 8 znaków, małą i dużą literę oraz cyfrę.");
        }
    }
}
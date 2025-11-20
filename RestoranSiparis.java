/**
 * Ad Soyad: [Adiniz Soyadiniz]
 * Numara: [Ogrenci Numaraniz]
 * Proje: Restoran Siparis
 * Tarih: 27 Ekim 2025
 */

import java.util.Scanner;
public class RestoranSiparis {

    public static double getMainDishPrice(int secim) {
        switch (secim) {
            case 1: return 85.0; // Izgara Tavuk
            case 2: return 120.0; // Adana Kebap
            case 3: return 110.0; // Levrek
            case 4: return 65.0; // Manti
            default: return 0.0;
        }
    }

    public static double getAppetizerPrice(int secim) {
        switch (secim) {
            case 1: return 25.0; // Corba
            case 2: return 45.0; // Humus
            case 3: return 55.0; // Sigara Boregi
            default: return 0.0;
        }
    }

    public static double getDrinkPrice(int secim) {
        switch (secim) {
            case 1: return 15.0; // Kola
            case 2: return 12.0; // Ayran
            case 3: return 35.0; // Taze Meyve Suyu
            case 4: return 25.0; // Limonata
            default: return 0.0;
        }
    }

    public static double getDessertPrice(int secim) {
        switch (secim) {
            case 1: return 65.0; // Kunefe
            case 2: return 55.0; // Baklava
            case 3: return 35.0; // Sutlac
            default: return 0.0;
        }
    }

    public static boolean isComboOrder(double ana, double icecek, double tatli) {
        return (ana > 0.0) && (icecek > 0.0) && (tatli > 0.0);
    }

    public static boolean isHappyHour(int hour) {
        return (hour >= 14) && (hour <= 17);
    }

    public static double calculateDiscount(double tutar, boolean combo, boolean ogrenci, int hour) {
        double discount = 0.0;
        if (combo) discount += tutar * 0.15;
        if (tutar > 200.0) discount += tutar * 0.10;
        if (isHappyHour(hour)) {
            // happy hour: drinks %20 discount (handled separately by reducing drink price)
            // Here no extra general discount other than drink handling
        }
        if (ogrenci) {
            // student: weekday %10 extra (we can't ask day per instructions in this simple version)
            discount += tutar * 0.10;
        }
        return discount;
    }

    public static double calculateServiceTip(double tutar) {
        return tutar * 0.10; // suggested 10%
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Secimler icin 0 girerseniz o kategori secilmemis olur.");
        System.out.print("Ana Yemek (1-4): ");
        int ana = sc.nextInt();
        System.out.print("Baslangic (0-3): ");
        int bas = sc.nextInt();
        System.out.print("Icecek (0-4): ");
        int icecek = sc.nextInt();
        System.out.print("Tatli (0-3): ");
        int tatli = sc.nextInt();
        System.out.print("Saat (8-23): ");
        int hour = sc.nextInt();
        System.out.print("Ogrenci misiniz? (E/H): ");
        String ogr = sc.next();
        System.out.print("Hangi gun? (1-7): ");
        int gun = sc.nextInt();

        double anaF = getMainDishPrice(ana);
        double basF = getAppetizerPrice(bas);
        double iceF = getDrinkPrice(icecek);
        double tatF = getDessertPrice(tatli);

        // apply happy hour drink discount
        if (isHappyHour(hour) && iceF > 0.0) {
            iceF = iceF * 0.80; // %20 off drinks
        }

        double subtotal = anaF + basF + iceF + tatF;
        boolean combo = isComboOrder(anaF, iceF, tatF);
        boolean ogrenci = ogr.equalsIgnoreCase("E") || ogr.equalsIgnoreCase("Y") || ogr.equalsIgnoreCase("T");

        double discountAmount = calculateDiscount(subtotal, combo, ogrenci, hour);
        double afterDiscount = subtotal - discountAmount;
        double tip = calculateServiceTip(afterDiscount);
        double total = afterDiscount + tip;

        System.out.println("=== SIPARIS OZETI ===");
        System.out.printf("Ara toplam: %.2f TL%n", subtotal);
        System.out.printf("Combo: %s%n", combo ? "EVET" : "HAYIR");
        System.out.printf("Indirim Tutarı: %.2f TL%n", discountAmount);
        System.out.printf("Toplam (Indirim sonrası): %.2f TL%n", afterDiscount);
        System.out.printf("Bahsis onerisi (10%%): %.2f TL%n", tip);
        System.out.printf("Odenecek Toplam: %.2f TL%n", total);

        sc.close();
    }
}

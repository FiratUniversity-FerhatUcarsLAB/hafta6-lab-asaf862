/**
 * Ad Soyad: [Adiniz Soyadiniz]
 * Numara: [Ogrenci Numaraniz]
 * Proje: Sinema Bileti
 * Tarih: 27 Ekim 2025
 */

import java.util.Scanner;
public class SinemaBileti {

    // Day mapping: 1=Pzt,2=Sal,3=Car,4=Per,5=Cum,6=Cts,7=Paz
    public static boolean isWeekend(int day) {
        return (day == 6) || (day == 7);
    }

    public static boolean isMatinee(int hour) {
        return hour < 12;
    }

    public static double calculateBasePrice(int day, int hour) {
        boolean weekend = isWeekend(day);
        boolean matinee = isMatinee(hour);
        if (!weekend && matinee) return 45.0;
        else if (!weekend) return 65.0;
        else if (weekend && matinee) return 55.0;
        else return 85.0;
    }

    // occupation: 1=Student,2=Teacher,3=Other
    public static double calculateDiscount(int age, int occupation, int day) {
        double discountPercent = 0.0;
        boolean weekend = isWeekend(day);

        if (age >= 65) {
            discountPercent = Math.max(discountPercent, 30.0);
        }
        if (age < 12) {
            discountPercent = Math.max(discountPercent, 25.0);
        }
        if (occupation == 2 && day == 3) { // teacher on Wednesday
            discountPercent = Math.max(discountPercent, 35.0);
        }
        if (occupation == 1) { // student
            // student: %20 (Mon-Thu), %15 (Fri-Sun)
            if (!weekend && (day >= 1 && day <= 4)) discountPercent = Math.max(discountPercent, 20.0);
            else discountPercent = Math.max(discountPercent, 15.0);
        }
        return discountPercent;
    }

    // filmType: 1=2D,2=3D,3=IMAX,4=4DX
    public static double getFormatExtra(int filmType) {
        switch (filmType) {
            case 2: return 25.0; // 3D
            case 3: return 35.0; // IMAX
            case 4: return 50.0; // 4DX
            default: return 0.0; // 2D
        }
    }

    public static double calculateFinalPrice(int day, int hour, int age, int occupation, int filmType) {
        double base = calculateBasePrice(day, hour);
        double extra = getFormatExtra(filmType);
        double discountPercent = calculateDiscount(age, occupation, day);
        double discounted = base * (1.0 - discountPercent / 100.0);
        return discounted + extra;
    }

    public static void generateTicketInfo(int day, int hour, int age, int occupation, int filmType) {
        double base = calculateBasePrice(day, hour);
        double extra = getFormatExtra(filmType);
        double discountPercent = calculateDiscount(age, occupation, day);
        double discounted = base * (1.0 - discountPercent / 100.0);
        double total = discounted + extra;

        System.out.println("=== BILET BILGILERI ===");
        System.out.printf("Gun (1-7): %d%n", day);
        System.out.printf("Saat: %d%n", hour);
        System.out.printf("Temel Fiyat: %.2f TL%n", base);
        System.out.printf("Indirim %%: %.0f%%%n", discountPercent);
        System.out.printf("Indirimli Fiyat: %.2f TL%n", discounted);
        System.out.printf("Format Ekstra: %.2f TL%n", extra);
        System.out.printf("Toplam: %.2f TL%n", total);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Gun (1=Pzt ... 7=Paz): ");
        int day = sc.nextInt();
        System.out.print("Saat (8-23): ");
        int hour = sc.nextInt();
        System.out.print("Yas: ");
        int age = sc.nextInt();
        System.out.print("Meslek (1=Ogrenci,2=Ogretmen,3=Dig er): ");
        int occupation = sc.nextInt();
        System.out.print("Film Turu (1=2D,2=3D,3=IMAX,4=4DX): ");
        int filmType = sc.nextInt();

        generateTicketInfo(day, hour, age, occupation, filmType);
        sc.close();
    }
}

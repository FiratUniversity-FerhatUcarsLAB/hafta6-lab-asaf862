/**
 * Ad Soyad: [Adiniz Soyadiniz]
 * Numara: [Ogrenci Numaraniz]
 * Proje: Not Sistemi
 * Tarih: 27 Ekim 2025
 */

import java.util.Scanner;
public class NotSistemi {

    final static double PASSING_GRADE = 50.0;
    final static double HONOR_THRESHOLD = 85.0;

    public static double calculateAverage(double vize, double finalExam, double odev) {
        return (vize * 0.3) + (finalExam * 0.4) + (odev * 0.3);
    }

    public static boolean isPassingGrade(double ortalama) {
        return ortalama >= PASSING_GRADE;
    }

    public static String getLetterGrade(double ortalama) {
        if (ortalama >= 90) return "A";
        else if (ortalama >= 80) return "B";
        else if (ortalama >= 70) return "C";
        else if (ortalama >= 60) return "D";
        else return "F";
    }

    public static boolean isHonorList(double ortalama, double vize, double finalExam, double odev) {
        return (ortalama >= HONOR_THRESHOLD) && (vize >= 70) && (finalExam >= 70) && (odev >= 70);
    }

    public static boolean hasRetakeRight(double ortalama) {
        return (ortalama >= 40) && (ortalama < PASSING_GRADE);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Vize notu (0-100): ");
        double vize = sc.nextDouble();
        System.out.print("Final notu (0-100): ");
        double finalExam = sc.nextDouble();
        System.out.print("Odev notu (0-100): ");
        double odev = sc.nextDouble();

        double ortalama = calculateAverage(vize, finalExam, odev);
        String harf = getLetterGrade(ortalama);
        boolean gecti = isPassingGrade(ortalama);
        boolean onur = isHonorList(ortalama, vize, finalExam, odev);
        boolean butunleme = hasRetakeRight(ortalama);

        System.out.println("=== OGRENCI NOT RAPORU ===");
        System.out.printf("Vize Notu : %.1f%n", vize);
        System.out.printf("Final Notu : %.1f%n", finalExam);
        System.out.printf("Odev Notu : %.1f%n", odev);
        System.out.println("------------------------------");
        System.out.printf("Ortalama : %.1f%n", ortalama);
        System.out.println("Harf Notu : " + harf);
        System.out.println("Durum : " + (gecti ? "GECTI" : "KALDI"));
        System.out.println("Onur Listesi : " + (onur ? "EVET" : "HAYIR"));
        System.out.println("Butunleme : " + (butunleme ? "VARS" : "YOK"));
        sc.close();
    }
}

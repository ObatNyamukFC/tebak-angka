import java.io.*;
import java.util.Scanner;
import java.util.Random;

public class tebakangka {
    private static final String FILE_NAME = "highscore.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int angkaBenar = random.nextInt(100) + 1;
        int tebakan, percobaan = 0;

        int highScore = bacaHighScore();

        System.out.println("=====================================");
        System.out.println("🎯 SELAMAT DATANG DI GAME TEBAK ANGKA");
        System.out.println("=====================================");
        System.out.println("Saya telah memilih angka antara 1 - 100");
        System.out.println("High Score saat ini: " + (highScore == Integer.MAX_VALUE ? "Belum ada" : highScore + " percobaan"));
        System.out.println("-------------------------------------");

        do {
            System.out.print("Masukkan tebakanmu: ");
            tebakan = scanner.nextInt();
            percobaan++;

            if (tebakan < angkaBenar) {
                System.out.println("🔻 Terlalu kecil!");
            } else if (tebakan > angkaBenar) {
                System.out.println("🔺 Terlalu besar!");
            } else {
                System.out.println("🎉 Benar! Angkanya adalah " + angkaBenar);
                System.out.println("🔁 Kamu menebaknya dalam " + percobaan + " percobaan.");
                if (percobaan < highScore) {
                    System.out.println("🏆 Selamat! Ini adalah rekor baru!");
                    simpanHighScore(percobaan);
                } else {
                    System.out.println("💡 Coba lagi untuk kalahkan rekor!");
                }
            }

        } while (tebakan != angkaBenar);

        scanner.close();
    }

    private static int bacaHighScore() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            return Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            return Integer.MAX_VALUE; // Tidak ada high score
        }
    }

    private static void simpanHighScore(int skorBaru) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(Integer.toString(skorBaru));
        } catch (IOException e) {
            System.out.println("Gagal menyimpan high score.");
        }
    }
}
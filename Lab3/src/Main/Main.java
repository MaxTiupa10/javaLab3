package Main;
import Droids.*;
import File.FileIO;
import Map.Map;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;


public class Main {
    public static void main(String[] args) {
        Menu();
    }
    private static String text = """
                        
            1 - Rectilinear { health : 100 , damage : 30 , treat : 0 }\s
            2 - Doctor { health : 100 , damage : 0 , treat : 15 } "\s
            3 - Bomber { health : 100 , damage : 30 , treat : 0 } "\s
            4 - Vinger { health : 100 , damage : 45 , treat : 0 } "\s
            5 - Crooked { health : 100 , damage : 40 , treat : 0 } "
            6 - Shotgun { health : 100 , damage : 2x20 , treat : 0 } "\s""";
    public static void Stand(Map map, Droid r, int team) {
        map.setDroid(r.getX(), r.getY(), r.getIcon(), r, team);
    }
    public static Droid createDroid() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Виберіть дроїда  :" + text);
            int l = scanner.nextInt();
            switch (l) {
                case 1 -> {
                    Rectilinear d1 = new Rectilinear(0, 0);
                    return d1;
                }
                case 2 -> {
                    Doctor d1 = new Doctor(0, 0);
                    return d1;
                }
                case 3 -> {
                    Bomber d1 = new Bomber(0, 0);
                    return d1;
                }
                case 4 -> {
                    Vinger d1 = new Vinger(0, 0);
                    return d1;
                }
                case 5 -> {
                    Crooked d1 = new Crooked(0, 0);
                    return d1;
                }
                case 6 -> {
                    Shotgun d1 = new Shotgun(0, 0);
                    return d1;
                }
                default -> {
                    System.out.println("Ви ввели невірне значення , спробуйте ще раз!");
                }
            }
        }
    }
    public static int randomPlace(int x, Map map) {
        Random random = new Random();
        int randomNum;
        while (true) {
            randomNum = random.nextInt(map.sizeMap() - 2) + 1; // Генерувати від 1 до 10 (включно)
            if (map.place(x, randomNum) == ' ')
                break;
        }
        return randomNum;
    }
    public static void setToTeam(ArrayList<Droid> Droids, Map map, int team) {
        String t;
        if (team == 1)
            t = " червоної";
        else
            t = " синьої";
        for (int j = 0; j < 1; j++) {
            System.out.println("Виберіть дроїда для" + t + " команди :");
            Scanner scanner = new Scanner(System.in);
            for (int a = 0; a < Droids.size(); a++) {
                System.out.println((a + 1) + " - " + Droids.get(a));
            }
            int b = scanner.nextInt();
            if (b > Droids.size()) {
                System.out.println("Невірне значення , введіть ще раз !");
                j--;
                continue;
            }
            int x = 1;
            if (team == 2)
                x = map.sizeMap() - 2;
            Droids.get(b - 1).setX(x);
            Droids.get(b - 1).setY(randomPlace(x, map));
            Stand(map, Droids.get(b - 1), team);
            Droids.remove(b - 1);
        }
    }
    public static void Menu() {
        FileIO file = new FileIO();
        StringBuilder sb = new StringBuilder();
        ArrayList<Droid> Droids = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    \u001B[32m\t Меню :\s
                    +------------------------------------------------------+
                    |  1 - Інформація про дроїдів                          |
                    |  2 - Створити дроїда                                 |
                    |  3 - Показати список створених дроїдів               |
                    |  4 - Запустити бій 1 на 1                            |
                    |  5 - Запустити бій команда на команду                |
                    |  6 - Записати проведений бій у файл                  |
                    |  7 - Відтворити проведений бій зі збереженого файлу  |
                    |  0 - Вийти з програми                                |
                    +------------------------------------------------------+""");
            System.out.println("Вибір : " + "\u001B[0m");
            int k = scanner.nextInt();
            switch (k) {
                case 1 -> {
                    System.out.println("""

                            1. Rectilinear - Це звичайний дроїд який стріляє прямо. Крок переміщення 1-3.\s
                            2. Doctor - Дроїд, який лікує своїх напарників, якщо вони знаходяться на відстані до 3 клітинок. Крок переміщення 1-2. "\s
                            3. Bomber - Дроїд, який кидає бомбу на ворожі клітинки , якщо Bomber попадає бомбою в суперника він зносить 30 здоров'я, а якщо дроїд наступає на клітинку на якій лежить ворожа бомба то його здоров'я зменшується на 10. Крок переміщення 1-3.  "\s
                            4. Vinger - Дроїд пулі якого мають властивість відбиватись від стіни. Крок переміщення 1-2. "\s
                            5. Crooked - Дроїд пуля якого перемішується випадковим чином завжди міняючи траєкторію руху. Крок переміщення 1-4. "
                            6. Shotgun - Дроїд який стріляє одночасно двома пулями. Крок переміщення 1.  "\s""");

                }
                case 2 -> {
                    Droids.add(createDroid());
                }
                case 3 -> {
                    System.out.println("\n Список створених дроїдів : ");
                    for (Droid droid : Droids) {
                        System.out.println(droid);
                    }
                }
                case 4 -> {
                    Map map = new Map(1);
                    setToTeam(Droids, map, 1);
                    setToTeam(Droids, map, 2);
                    map.startGame();
                    sb = map.getSb();
                }
                case 5 -> {
                    if (Droids.size() < 6) {
                        System.out.println("У вас замало дроїдів, мінімальна кількість 6!");
                        break;
                    }
                    Map map = new Map(2);
                    setToTeam(Droids, map, 1);
                    setToTeam(Droids, map, 1);
                    setToTeam(Droids, map, 1);
                    setToTeam(Droids, map, 2);
                    setToTeam(Droids, map, 2);
                    setToTeam(Droids, map, 2);
                    map.startGame();
                    sb = map.getSb();
                }
                case 6 ->
                    file.writeToFile("D:\\Прикладне програмування\\Lab3.txt", sb);
                case 7 ->
                    System.out.println(file.readFromFile("D:\\Прикладне програмування\\Lab3.txt"));
                case 0 -> {
                    return;
                }
                default ->
                    System.out.println("Ви ввели невірне значення , спробуйте ще раз!");
            }
        }
    }
}


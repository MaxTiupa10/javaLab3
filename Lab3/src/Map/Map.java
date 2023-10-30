package Map;

import Droids.Doctor;
import Droids.Droid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Map {

    // Код для червоного тексту
    private String redText = "\u001B[31m";
    private String resetColor = "\u001B[0m";
    private String blueText = "\u001B[34m";
    private ArrayList<ArrayList<Character>> map;
    private ArrayList<Droid> droidsRed;
    private ArrayList<Droid> droidsBlue;
    private StringBuilder sb = new StringBuilder();
    private int smallSize = 7;
    private int bigSize = 12;

    public Map(int regime) {
        if (regime == 1)
            createMap(smallSize);
        else
            createMap(bigSize);
    }

    public void createMap(int size) {
        droidsRed = new ArrayList<>();
        droidsBlue = new ArrayList<>();
        map = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            map.add(new ArrayList<>(size));
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map.get(i).add(' ');
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (j == 0 || j == size - 1)
                    map.get(i).set(j, '|');
                if (i == 0 || i == size - 1)
                    map.get(i).set(j, '-');
            }
        }
    }


    public void setObstacle(char p) {
        if (this.sizeMap() == 12) {
            this.setObstacle(3, 5, p);
            this.setObstacle(7, 5, p);
        } else this.setObstacle(3, 3, p);
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                Додати перешкоди\s
                 1 - Так
                 2 - Ні\s""");
        int i = scanner.nextInt();
        if (i == 1)
            this.setObstacle('|');
        this.teams();
        for (i = 0; ; i++) {
            if (i > 100)
                this.setObstacle(' ');
            if (this.gameOver())
                break;
            this.Draw();
            this.PrintRedTeam();
            this.PrintBlueTeam();
            this.fight(i + 1);
            this.moveAll();
        }
        this.teams();
    }


    public StringBuilder getSb() {
        return sb;
    }

    public void teams() {
        sb.append(redText + "Red Team : \n" + resetColor);
        for (Droid droid : droidsRed)
            sb.append(redText + droid.printName() + resetColor + "\n");
        sb.append(blueText + "Blue Team : \n" + resetColor);
        for (Droid droid : droidsBlue)
            sb.append(blueText + droid.printName() + resetColor + "\n");
    }

    public void setDroid(int x, int y, char name, Droid d, int team) {
        if (team == 1)
            droidsRed.add(d);
        else droidsBlue.add(d);
        map.get(y).set(x, name);
    }

    public void setPlace(int x, int y, char p) {
        if (x >= 0 && x < map.size() && y >= 0 && y < map.size() && place(x, y) == ' ') {
            map.get(y).set(x, p);
        }
    }

    public char place(int x, int y) {
        if (x >= 0 && x < map.size() && y >= 0 && y < map.size())
            return map.get(y).get(x);
        return '0';
    }

    public int sizeMap() {
        return this.map.size();
    }

    public void setObstacle(int y, int x, char p) {
        this.map.get(y).set(x, p);
    }

    public void PrintRedTeam() {
        System.out.println(redText + "Red Team : ");
        for (Droid droid : droidsRed)
            System.out.println(redText + droid.printName() + resetColor);
    }

    public void PrintBlueTeam() {
        System.out.println(blueText + "Blue Team : ");
        for (Droid droid : droidsBlue) System.out.println(blueText + droid.printName() + resetColor);
    }

    public void moveAll() {
        moveTeam(droidsRed);
        moveTeam(droidsBlue);
    }

    public void moveTeam(ArrayList droidsTeam) {
        // Для Droids_Red
        Iterator<Droid> Iterator = droidsTeam.iterator();
        // for (Droid droid : droidsRed){}
        while (Iterator.hasNext()) {
            Droid droid = Iterator.next();
            if (droid.getHealth() == 0) {
                map.get(droid.getY()).set(droid.getX(), ' ');
                Iterator.remove(); // Видалення без помилки
            } else {
                map.get(droid.getY()).set(droid.getX(), ' ');
                droid.step(this);
                map.get(droid.getY()).set(droid.getX(), droid.getIcon());
            }
        }

    }

    public void addToSB(String str) {
        sb.append(str + "\n");
    }

    public boolean gameOver() {
        if(droidDead("Blue", droidsRed))
            return true;
        return droidDead("Red", droidsBlue);
    }

    public boolean droidDead(String Team,ArrayList<Droid> droids) {
        String Text;
        if (Team == "Blue")
            Text = "\u001B[34m";
        else
            Text = "\u001B[31m";
        int k = 0;
        for (Droid droid : droids) {
            if (droid.getHealth() == 0)
                k++;
        }
        if (k == droids.size()) {
            this.Draw();
            this.PrintRedTeam();
            this.PrintBlueTeam();
            System.out.println(Text + "\n\t"+Team+" Team WIN!" + resetColor);
            sb.append("\n\t"+Team+" Team WIN!\n");
            return true;
        }
        return false;
    }

    public void fight(int n) {
        System.out.println("\n\t\tFight Round " + n + "  :  ");
        sb.append("\n\t\tFight Round " + n + "  :  ");
        fightTeam(droidsRed,droidsBlue);
        fightTeam(droidsBlue,droidsRed);
        System.out.println();
    }

    public void fightTeam(ArrayList<Droid> droids,ArrayList<Droid> droids1){
        Droid droid, droid1;
        for (int i = 0; i < droids.size(); i++) {
            droid = droids.get(i);
            if (droid.getHealth() == 0)
                continue;
            int len = (droid instanceof Doctor) ? droids.size() : droids1.size();
            if (droid.getIcon() == 'B')
                len = 1;
            for (int j = 0; j < len; j++) {
                if (droid instanceof Doctor) {
                    droid1 = droids.get(j);
                    ((Doctor) droid).treat(droid1, this);
                } else {
                    droid1 = droids1.get(j);
                    if (droid1.getHealth() == 0)
                        continue;
                    droid.shot(droid1, this);

                }
            }
        }
    }

    public void Draw() {
        for (int rowIndex = 0; rowIndex < map.size(); rowIndex++) {
            ArrayList<Character> row = map.get(rowIndex);
            for (int columnIndex = 0; columnIndex < row.size(); columnIndex++) {
                char value = row.get(columnIndex);
                if (value != ' ' && columnIndex == 1 && rowIndex != 0 && rowIndex != 11) {
                    if (value == 'b')
                        System.out.print(blueText + value + resetColor + "  ");
                    else System.out.print(redText + value + resetColor + "  ");

                } else if (value != ' ' && columnIndex == row.size() - 2 && rowIndex != 0 && rowIndex != 11) {
                    if (value == 'b')
                        System.out.print(redText + value + resetColor + "  ");
                    else System.out.print(blueText + value + resetColor + "  ");
                } else {
                    System.out.print(value + "  ");
                }
            }
            System.out.println();
        }
    }
}
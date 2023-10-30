package Droids;

import Map.Map;
import Droids.Droid;

import java.util.Random;

public class Vinger extends Droid {
    public Vinger(int x, int y) {
        super("Vinger", 'V', 45, 0, x, y);
    }


    public void shot(Droid droid, Map map) {
        String team;
        int x;
        if (this.getX() == 1) {
            team = "Red";
            x = 1;
        } else {
            team = "Blue";
            x = -1;
        }
        int k = 0, l;
        Random random = new Random();
        int a = 2;
        int y1 = 0;

        int randomNumber = random.nextInt(2);
        if (randomNumber == 0)
            l = 1;
        else l = -1;
        for (int i = 0; i < map.sizeMap() - 2; i++) {
            if (map.place(this.getX() + (i * x), this.getY() + i*l) == ' ')
                k++;
            else if (i != 0) {
                y1 = this.getY() + (i*l) - a*l;
                if (map.place(this.getX() + (i * x), y1) == ' ') {
                    k++;
                    a += 2;
                }
            }
        }
        if (k == map.sizeMap() - 4 && y1 == droid.getY()) {
            if (droid.getHealth() != 0)
                System.out.println("Red Vinger strike   " + droid.getName());
            map.addToSB("Red Vinger strike   " + droid.getName());
            int newHealth = droid.getHealth() - this.getDamage();
            droid.setHealth(Math.max(newHealth, 0));
        }
    }



    public void step(Map map) {
        super.Move(map, 2);
    }
}

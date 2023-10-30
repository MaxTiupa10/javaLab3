package Droids;

import Map.Map;
import Droids.Droid;

import java.util.Random;

public class Shotgun extends Droid {
    public Shotgun(int x, int y) {
        super("Shotgun", 'S', 20, 0, x, y);
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
        if (droid.getY() == this.getY() + 1 || droid.getY() == this.getY() - 1) {
            int u = 0, d = 0, i;
            for (i = 1; i < map.sizeMap() - 2; i++) {
                if (map.place(this.getX() + (i * x), this.getY() - 1) == ' ')
                    u++;
                if (map.place(this.getX() + (i * x), this.getY() + 1) == ' ')
                    d++;
            }
            if (u == map.sizeMap() - 4 || map.place(this.getX() + (i * x), this.getY() - 1) == droid.getIcon() || map.place(this.getX() + (i * x), this.getY() + 1) == droid.getIcon() || d == map.sizeMap() - 4) {
                System.out.println(team + " Shotgun strike   " + droid.getName());
                map.addToSB(team + " Shotgun strike   " + droid.getName());
                int newHealth = droid.getHealth() - this.getDamage();
                droid.setHealth(Math.max(newHealth, 0));
            }
        }
    }


    public void step(Map map) {
        super.Move(map, 0);
    }
}


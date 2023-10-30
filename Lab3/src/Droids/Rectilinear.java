package Droids;

import Map.Map;
import Droids.Droid;

import java.util.Random;

public class Rectilinear extends Droid {

    public Rectilinear(int x, int y) {
        super("Rectilinear", 'R', 30, 0, x, y);
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
        if (droid.getY() == this.getY()) {
            int k = 0;
            for (int i = 0; i < map.sizeMap() - 2; i++) {
                if (map.place(this.getX() + (i * x), this.getY()) == ' ')
                    k++;
            }
            if (k == map.sizeMap() - 4) {
                if (droid.getHealth() != 0)
                    System.out.println(team + " Rectilinear strike   " + droid.getName());
                map.addToSB(team + " Rectilinear strike   " + droid.getName());
                int newHealth = droid.getHealth() - this.getDamage();
                droid.setHealth(Math.max(newHealth, 0));
            }
        }
    }




    public void step(Map map) {
        super.Move(map, 3);
    }


}

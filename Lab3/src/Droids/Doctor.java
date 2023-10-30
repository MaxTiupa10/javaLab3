package Droids;

import Map.Map;
import Droids.Droid;

import java.util.Random;

import static java.lang.Math.abs;

public class Doctor extends Droid {

    public Doctor(int x, int y) {
        super("Doctor", 'D', 0, 20, x, y);
    }

    public void treat(Droid droid, Map map) {
        if (this.getHealth() == 0)
            return;
        if (droid.getX() == this.getX() && abs(droid.getY() - this.getY()) < 3 && this != droid) {
            droid.setHealth(Math.min(droid.getHealth() + this.getTreat(), 100));
            System.out.println("Doctor treat " + droid.getName());
            map.addToSB("Doctor treat " + droid.getName());
        }
    }

    public void step(Map map) {
        super.Move(map, 2);
    }

}

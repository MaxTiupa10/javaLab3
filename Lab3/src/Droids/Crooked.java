package Droids;
import Map.Map;
import Droids.Droid;

import java.util.Random;

public class Crooked extends Droid {
    public Crooked(int x, int y) {
        super("Crooked", 'C', 40, 0, x, y);
    }

    public void shot(Droid droid, Map map) {
crookedshot(droid,map);
    }

    public void crookedshot(Droid droid, Map map){
        String team;
        int x ;
        if(this.getX() == 1){
            team = "Red";
            x = 1;
        }
        else {
            team = "Blue";
            x = -1;
        }

        int k = 0;
        Random random = new Random();
        int i;
            for (i = 0; i < map.sizeMap() - 2; i++) {
                int randomNumber = random.nextInt(2);
                if (randomNumber == 0)
                    k++;
                else k--;
                if (map.place(this.getX() + i*x, this.getY() + k) != ' ')
                    break;

            }
            if (this.getX() + i == map.sizeMap() - 2 && (this.getY() + k) == droid.getY()) {
                if(droid.getHealth() != 0)
                    System.out.println(team +" Crooked strike   " + droid.getName());
                map.addToSB(team +" Crooked strike   " + droid.getName());
                int newHealth = droid.getHealth() - this.getDamage();
                droid.setHealth(Math.max(newHealth, 0));
            }




    }



    public void step(Map map) {
        super.Move(map,4);
    }
}

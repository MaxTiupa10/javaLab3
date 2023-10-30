package Droids;
import Map.Map;

import java.util.Random;

public class Bomber extends Droid {
    public Bomber(int x, int y) {
        super("Bomber", 'B', 40, 0, x, y);
    }

    public void shot(Droid droid, Map map) {
        bombershot(droid,map);
    }

    public void bombershot(Droid droid, Map map){
        String team;
        int x ;
        if(this.getX()== 1){
            team = "Red";
            x = 10;
        }
        else {
            team = "Blue";
            x = 1;
        }
        Random random = new Random();
        int place = random.nextInt(map.sizeMap()) + 1;
        if(droid.getY() == place) {
            if(droid.getHealth() != 0) {
                System.out.println(team +" Bomber strike   " + droid.getName());
                map.addToSB(team +" Bomber strike   " + droid.getName());
                int newHealth = droid.getHealth() - this.getDamage();
                droid.setHealth(Math.max(newHealth, 0));
            }
        }
        else map.setPlace(x,place,'b');
    }

    public void step(Map map) {
        super.Move(map,3);
    }

}

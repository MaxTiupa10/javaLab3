package Droids;

import Map.Map;

import java.util.Random;

public class Droid {

    private String name;

    private char icon;
    private int health;
    private int Damage;


    private int treat;
    private int x;
    private int y;

    public Droid(String name, char icon, int damage, int treat, int x, int y) {
        this.name = name;
        this.icon = icon;
        this.health = 100;
        this.Damage = damage;
        this.treat = treat;
        this.x = x;
        this.y = y;
    }

    public char getIcon() {
        return icon;
    }

    public int getX() {
        return x;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return Damage;
    }

    public void shot(Droid droid, Map map) {
    }

    public int getY() {
        return y;
    }

    public String getName() {
        return name;
    }

    public String printName() {
        String str = this.name +
                " | Health : " + this.health +
                " | x : " + this.x +
                " | y : " + this.y;
        return str;
    }

    public int getTreat() {
        return treat;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void step(Map map){}
    public void Move(Map map, int max) {
        Random rand = new Random();
        int step = max == 0 ? 1 : (rand.nextInt(max) + 1);
        if (rand.nextBoolean()) { // Якщо true, то віднімаємо
            step = -step;
        }
        if (map.place(this.getX(), this.getY() + step) == ' ')
            this.setY(this.getY() + step);
        else if (map.place(this.getX(), this.getY() + step) == 'b') {
            this.setHealth(Math.max(this.getHealth() - 5, 0));
        }
    }

    @Override
    public String toString() {
        return "Droid{" +
                "name='" + name + '\'' +
                ", icon=" + icon +
                ", health=" + health +
                ", Damage=" + Damage +
                ", treat=" + treat +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}

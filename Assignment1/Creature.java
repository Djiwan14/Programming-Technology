/*
 * Name : Shokhrukh Nigmatillaev
 * Neptun : APVAVZ
 * Task : 1
 * */
package Assignment1;

public abstract class Creature {
    protected String name;
    protected int waterLevel;
    protected int maxWaterLevel;
    protected boolean isAlive;
    protected int distance;

    public Creature(String name, int waterLevel) {
        this.name = name;
        this.waterLevel = waterLevel;
        this.isAlive = this.waterLevel > 0;
        this.distance = 0;
    }
    public boolean isAlive(){
        return this.isAlive;
    }
    public String getName(){
        return this.name;
    }
    public int getDistance(){
        return this.distance;
    }
    public abstract void sunnyDay();
    public abstract void cloudyDay();
    public abstract void rainyDay();
}
// inheritance from abstract parent class |
class Sandrunner extends Creature {

    public Sandrunner(String name, int waterLevel) {
        super(name, waterLevel);
        this.maxWaterLevel = 8;
    }
    @Override
    public void sunnyDay(){
        if(this.waterLevel > 0) {
            this.waterLevel -= 1;
            if(this.waterLevel > 0){ this.distance += 3; }
            else{ this.isAlive = false; }
        }
    }
    @Override
    public void cloudyDay(){
        if(this.waterLevel > 0)
            { this.distance += 1; }
        else
            { this.isAlive = false; }
    }
    @Override
    public void rainyDay(){
        if(this.waterLevel > 0) {
            this.waterLevel = Math.min(this.maxWaterLevel, this.waterLevel + 3);
        }
    }
}

class Sponge extends Creature {

    public Sponge(String name, int waterLevel) {
        super(name, waterLevel);
        this.maxWaterLevel = 20;
    }
    @Override
    public void sunnyDay(){
        if(this.waterLevel > 0) {
            this.waterLevel -= 4;
            if(this.waterLevel <= 0){ this.isAlive = false; }
        }
    }
    @Override
    public void cloudyDay(){
        if(this.waterLevel > 0) {
            this.waterLevel -= 1;
            if(this.waterLevel > 0){ this.distance += 1; }
            else{ this.isAlive = false; }
        }
    }
    @Override
    public void rainyDay(){
        if(this.waterLevel > 0) {
            this.waterLevel = Math.min(this.maxWaterLevel, this.waterLevel + 6);
            this.distance += 3;
        }
    }
}

class Walker extends Creature {

    public Walker(String name, int waterLevel) {
        super(name, waterLevel);
        this.maxWaterLevel = 12;
    }
    @Override
    public void sunnyDay(){
        if(this.waterLevel > 0) {
            this.waterLevel -= 2;
            if(this.waterLevel > 0){ this.distance += 1; }
            else { this.isAlive = false; }
        }
    }
    @Override
    public void cloudyDay(){
        if(this.waterLevel > 0) {
            this.waterLevel -= 1;
            if(this.waterLevel > 0){ this.distance += 2; }
            else{ this.isAlive = false; }
        }
    }
    @Override
    public void rainyDay(){
        if(this.waterLevel > 0) {
            this.waterLevel = Math.min(this.maxWaterLevel, this.waterLevel + 3);
            this.distance += 1;
        }
    }
}
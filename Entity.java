import java.io.*;

public abstract class Entity implements Serializable
{
    private String name;
    private int speed;
    private int mobility;
    
    //Getters and Setters
    public Entity(String name){
        this.name=name;
    }
    
    public String getName(){
        return this.name;
    }
    
    public int getSpeed(){
        return speed;
    }
    
    public void setSpeed(int speed){
        this.speed=speed;
    }  
    
    public int getMobility(){
        return mobility;
    }
    
    public void setMobility(int mobility){
        this.mobility=mobility;
    }
    
    
    //Creation of method that must be overriden in the sub classes
    public abstract void BoostNerf(int boost);
    public abstract void reset();
    public abstract int getUsage();
    public abstract int getRoundLoss();
    
    
    public abstract int tireOvertake(boolean choice);
    public abstract int motorOvertake(boolean choice);
    public abstract int nitroOvertake(boolean choice);
}

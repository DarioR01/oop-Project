import java.util.Random;
import java.io.*;

public class AI extends Entity implements Serializable{
    private final int resetSpeed;
    private final int resetMobility;
    private int usage;
    private int roundLoss;
    
    //Constructor for the AI class
    public AI(String name){
        super(name);
        super.setSpeed(new Random().nextInt(100));
        super.setMobility(new Random().nextInt(100));
        this.resetSpeed=super.getSpeed();
        this.resetMobility=super.getMobility();
        this.usage=new Random().nextInt(100);
        this.roundLoss=new Random().nextInt(5);
    }
    
    //reset the value of the AI class to initial values
    public void reset(){
        super.setSpeed(this.resetSpeed);
        super.setMobility(this.resetMobility);
    }
    
    //Getters and setters
    public int getUsage(){
        return usage;
    }
    
    public void setUsage(int reduce){
        this.usage-=reduce;
    }
    
    public int getRoundLoss(){
        return roundLoss;
    }
    
    //Nerf the AI statistics
    public void BoostNerf(int boost){
        setSpeed(getSpeed()-boost);
        setMobility(getMobility()-boost);
    }
    
    //return the power for a straightway round
    public int tireOvertake(boolean choice){
        return 100*(new Random()).nextInt(6);
    }
    
    //return the power for a curve round
    public int motorOvertake(boolean choice){
        return 100*(new Random()).nextInt(6);
    }
   
    //return the power for a boost round
    public int nitroOvertake(boolean choice){
        return 0;
    }
}
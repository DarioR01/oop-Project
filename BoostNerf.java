import java.util.ArrayList;
import java.io.*;
public class BoostNerf extends Item implements Serializable{
    boolean throwable;
    
    //Constructor for the BoostNerf class
    public BoostNerf(String name,String description,int power,boolean throwable){
        super(throwable?name+" (throw)":name, description,power);
        this.throwable=throwable;
    }
   
    //Ovverride of the method use in the superclass, if the item is throwable it effects the car ahead else it effects the player
    public String use(ArrayList<Entity> Positions,Entity car){
        int enemyPos;
        if(throwable){
            Positions.get(Positions.indexOf(car)+1).BoostNerf(super.getPower());
            return "Item Used successfully, you speed down the car ahead for this round";
        }
        else{
            car.BoostNerf(super.getPower());
            return "Item Used successfully, you boosted yourself in this round";
        }
    }
    
    //Override of the getter in the superclass, the description of the item changes depending if it is throwable or not
    public String getDescription(){
        if(throwable){
            return "nerf the statistic of the enemy in front of you by "+super.getPower();
        }
        else{
            return "Boost you by "+super.getPower();
        }
    }
}

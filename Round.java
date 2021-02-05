import java.util.ArrayList;
import java.io.*;

public class Round implements Serializable
{
    private String name;
    private String description;
    
    //Constructor for the game class
    public Round(String name, String description){
        this.name=name;
        this.description=description;
    }
    
    
    //Overtake logic between two cars
    public void action(boolean action,int round,Entity ahead,Entity behind,ArrayList<Entity> Positions){
        int posBehind=Positions.indexOf(behind);
        int posAhead=Positions.indexOf(ahead);
        
        if(round==1){
            if(ahead.motorOvertake(action)<behind.motorOvertake(action)){
                Positions.set(posBehind,ahead);
                Positions.set(posAhead,behind);
            }
        }
        else if(round==0){
            if(ahead.tireOvertake(action)<behind.tireOvertake(action)){
                Positions.set(posBehind,ahead);
                Positions.set(posAhead,behind);
            }
        }
        else{
            if(ahead.nitroOvertake(action)<behind.nitroOvertake(action)){
                Positions.set(posBehind,ahead);
                Positions.set(posAhead,behind);
            }
        }
    }
    
    
    //Getters and Setters
    public String getName(){
        return name;
    }
    
    public String getDescription(){
        return description;
    }
    
    public String getOption1(){
        return "No Option";
    }
    
    public String getOption2(){
        return "No Option";
    }
}

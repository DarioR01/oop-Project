import java.util.ArrayList;
import java.io.*;


public class straightway extends Round implements Serializable
{
    private int length;
    
    //Constructore for straightway class
    public straightway(String name, String description, int length){
        super(name,description);
        this.length=length;
    }
    
    //overrides the action performed in this particular round, the car behind have increased overtake chance depending on the length of the round
    public void action(boolean action,int round,Entity ahead,Entity behind,ArrayList<Entity> Positions){
        int posBehind=Positions.indexOf(behind);
        int posAhead=Positions.indexOf(ahead);
        if(ahead.motorOvertake(action)<(behind.motorOvertake(action)*this.length)){
            Positions.set(posBehind,ahead);
            Positions.set(posAhead,behind);
        }
    }
    
    
    //Getters anjd Setters
    public String getDescription(){
        return super.getDescription()+" Length of straightway: "+this.length*1000+" m";
    }
    
    //get the variable text that should be displayed in the buttons;
    public String getOption1(){
        return "Speed Up";
    }
    
    public String getOption2(){
        return "Keep Speed";
    }
}

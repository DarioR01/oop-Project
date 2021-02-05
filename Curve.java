import java.util.ArrayList;
import java.io.*;
import java.util.Random;

public class Curve extends Round implements Serializable
{  
    private String type;
    private int typeCurve;
    
    //Constructore fot he Curve class
    public Curve(String name, String description,String type){
        super(name,description);
        this.type=type;
        typeCurve=super.getName().equals("Casio Triangle")?3:super.getName().equals("Hairpin")?2:super.getName().equals("Spoon Curve")?1:4;
    }   
    
    //overrides the action performed in this particular round by inserting the superclass action in a for loop controlled by the type of curve variable
    public void action(boolean action,int round,Entity ahead,Entity behind,ArrayList<Entity> Positions){
        for(int i=0;i<typeCurve;i++){
            super.action(action,0,ahead,behind,Positions);
        }
    }
    
    
    //Getters and Setters
    public String getDescription(){
        return super.getDescription()+" "+this.type;
    }
    
    //get the variable text that should be displayed in the buttons;
    public String getOption1(){
        return "Overtake";
    }
    
    public String getOption2(){
        return "Do not Overtake";
    }
}

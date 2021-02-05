import java.util.ArrayList;
import java.io.*;

public class Item implements Serializable
{
    private String name;
    private String description;
    private int power;
    
    //Construcotr fot the Item class
    public Item(String name, String description, int power){
        this.name=name;
        this.description=description;
        this.power=power;
    }
    
    //use method that return a malfunction of the item
    public String use(ArrayList<Entity> Positions,Entity entity){
        return "The item "+this.name+" could not have been used in this round";
    }
    
    //Getters and setters
    public String getName(){
        return name;
    }
    
    public int getPower(){
        return power;
    }
    
    public String getDescription(){
        return this.description;
    }
}

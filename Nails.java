import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.io.*;
public class Nails extends Item implements Serializable
{
    int quantity;
    
    //Construtor for the Nails class
    public Nails(String name,String description,int power,int quantity){
        super(name, description,power);
        this.quantity=quantity;
    }
    
    //method that set the usage of the a value(quantity) of cars behind the player and reduce it by the power value of the item;  
    public String use(ArrayList<Entity> Positions,Entity car){
        int playerPos=Positions.indexOf(car);
        String temp="You Effected:\n";
        String tempAdd="";
        try{
            for(int i=0;i<quantity;i++){
                ((AI)Positions.get(playerPos+i)).setUsage(((AI)Positions.get(playerPos+i)).getUsage()-super.getPower());
                tempAdd+=Positions.get(playerPos+i+1).getName()+"\n";
            }
        }
        catch(IndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(null, "There's not enough cars behind you to fully utilize this item", "InfoBox: " + "Nice try", JOptionPane.INFORMATION_MESSAGE);
            if(tempAdd.equals("")){
                return "You wasted your item";
            }
            else{
                return temp+tempAdd;
            }
        }
        return temp+tempAdd;
    }  
    
    
    //Getters and setters
    public String getDescription(){
        return super.getDescription()+"\n this item will effect "+this.quantity+" opponents behind you";
    }
}
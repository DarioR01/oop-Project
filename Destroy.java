import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.io.*;
public class Destroy extends Item implements Serializable
{
    int attenuation;
    //Constructor for the destroy class
    public Destroy(String name,String description,int power,int attenuation){
        super(name, description,power);
        this.attenuation=attenuation;
    }
    
    //method that delete positions ahead the player depending on the power of the Item/ however if the total racing cars are less than the attenuation then the item will not be used
    public String use(ArrayList<Entity> Positions,Entity car){
        if(attenuation<Positions.size()){
            int playerPos;
            String temp="You destroyed:\n";
            String tempAdd="";
            try{
                for(int i=super.getPower();i>0;i--){
                    playerPos=Positions.indexOf(car);
                    tempAdd+=Positions.get(playerPos-1).getName()+"\n";
                    Positions.remove(playerPos-1);
                }
            }
            catch(IndexOutOfBoundsException e){
                JOptionPane.showMessageDialog(null, "There are not enough cars in front of you to fully utilize the item you selected", "InfoBox: " + "Nice try", JOptionPane.INFORMATION_MESSAGE);
                if(tempAdd.equals("")){
                    return "You wasted your item";
                }
                else{
                    return temp+tempAdd;
                }
            }
            return temp+tempAdd;
        }
        return super.use(Positions,car);
    }   
    
    
    //Getters and Setters
    public String getDescription(){
        return super.getDescription()+"\n This item can not be used if racers are less than "+this.attenuation+"\nThis item will destroy "+super.getPower()+" cars ahead of you";
    }
}

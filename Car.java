import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import java.io.*;

public class Car extends Entity implements Serializable,canBeSaved
{
    ArrayList<Component> Components=new ArrayList();
    
    //Constructor for the Car class
    public Car(String name,Structure motor,Structure tire,Nitro nitro){
        super(name);
        Components.add(motor);
        Components.add(tire);
        Components.add(nitro);
        this.reset();
    }
    
    //reset the original value of the car
    public void reset(){
        super.setSpeed(((Structure)(Components.get(0))).getPower());
        super.setMobility(((Structure)(Components.get(1))).getPower());
    }
    
    //Create a method that constuct a string to show the components of the car and returns it
    public String viewCar(){
        String temp="This car is made of:\n";
        for(int i=0;i<Components.size();i++){
            temp+=Components.get(i).viewDescription()+"\n";
        }
        return temp;
    }
    
    //Calculate the overtake power in the curve round and returns it
    public int tireOvertake(boolean choice){
        try{
            if(choice){
                Components.get(1).reduceUsage(10);
                return super.getMobility()*new Random().nextInt(10);
            }
            else{
                Components.get(1).increaseUsage(10);
            }
            return 0;
        }
        catch(ComponentException e){
            JOptionPane.showMessageDialog(null, "Not enough usage for the "+Components.get(1).getName()+" to perform this action", "InfoBox: " + "Usage Problem", JOptionPane.INFORMATION_MESSAGE);
        }
        return 0;
    }
    
    //Calculate the overtake power in the straightway round and returns it
    public int motorOvertake(boolean choice){
        try{
            if(choice){
                Components.get(0).reduceUsage(10);
                return super.getSpeed()*new Random().nextInt(10);
            }
            else{
                Components.get(0).increaseUsage(10);
            }
            return 0;
        }
        catch(ComponentException e){
            JOptionPane.showMessageDialog(null, "Not enough usage for the component"+Components.get(0).getName()+" to perform this action", "InfoBox: " + "Usage Problem", JOptionPane.INFORMATION_MESSAGE);
        }
        return 0;
    }
    
    //Calculate the overtake power in the boost round and returns it
    public int nitroOvertake(boolean choice){
        try{
            if(choice){
                Components.get(2).reduceUsage(1);
                return Integer.MAX_VALUE;
            }
            else{
                Components.get(2).increaseUsage(0);
                return 0;
            }
        }
        catch(ComponentException e){
            JOptionPane.showMessageDialog(null, "Not enough usage for the "+Components.get(2).getName()+" to perform this action", "InfoBox: " + "Usage Problem", JOptionPane.INFORMATION_MESSAGE);
        }
        return 0;
    }
    
    //Boost the speed of the car by a boost value
    public void BoostNerf(int boost){
        setSpeed(getSpeed()+boost);
        setMobility(getMobility()+boost);
    }
    
    //Getters and Setters
    public int getUsage(){
        return Components.get(0).getUsage()+Components.get(1).getUsage();
    }
    
    public int getMotorUsage(){
        return Components.get(0).getUsage();
    }
    
    public int getTireUsage(){
        return Components.get(1).getUsage();
    }
    
    public int getNitroUsage(){
        return Components.get(2).getUsage();
    }
    
    public int getRoundLoss(){
        int temp=0;
        for(int i=0;i<Components.size();i++){
            temp+=Components.get(i).getRoundLoss();
        }
        return temp;
    }
    
    
    //Creats a way to store the state of the object in a txt file
    public void outputObject(String file)throws IOException{
        FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream thingOut = new ObjectOutputStream(fileOut);
        thingOut.writeObject(this);
        thingOut.close();
    }
}

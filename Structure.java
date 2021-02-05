import java.io.*;

public class Structure extends Component implements Serializable
{
    private String type;
    private int power;
    private int roundLoss;
    
    //Constructor for Structure class
    public Structure(String name,int usage,String type,int power,int roundLoss){
        super(name,usage);
        this.type=type;
        this.power=power;
        this.roundLoss=roundLoss;
    }
    
    //Increase the usage of the component by a value increase
    public void increaseUsage(int increase){
        super.setUsage(super.getUsage()+increase);
    }
    
    //reduce the usage of the component by a value, if the usage is below 0 it throws a components exception
    public void reduceUsage(int reduce)throws ComponentException{
        if(super.getUsage()-reduce<0){
            throw new ComponentException("The Component has not more usage");
        }
        else{
            super.setUsage(super.getUsage()-reduce);
        }
    }    
    
    
    
    //Give a description of the structural piece
    public String viewDescription(){
        return ((type=="Tire")?"The set of tires ":"The motor ")+super.getName()+" has a usage of "+super.getUsage()+" they can be repaired in "+this.roundLoss+" and will make your car obtain a "+((type=="Tire")?"mobility of ":"speed of ")+this.power;
    }
    
    //getters and setters 
    
    public int getRoundLoss(){
        return roundLoss;
    }  
    
    public int getPower(){
        return power;
    }
}

import java.io.*;

public class Nitro extends Component implements Serializable
{
    int restoreNitro;
    
    //Constructor for the nitro class
    public Nitro(String name,int usage, int newRestore){
        super(name,usage);
        restoreNitro=newRestore;
    }
    
    //give a description of the nitro component
    public String viewDescription(){
        return "The nitro "+super.getName()+" can be used up to "+super.getUsage()+" times";
    }
    
    //Increase the usage of the nitro depending on the restoreNitro value
    public void increaseUsage(int increase){
        super.setUsage(super.getUsage()+this.restoreNitro);
    }
    
    //reduce the item usage by 1, if the item usage is less than 0 it throws an exception and set the usage to 0;
    public void reduceUsage(int reduce)throws ComponentException{
        super.setUsage(super.getUsage()-1);
        if(super.getUsage()<0){
            super.setUsage(0);
            throw new ComponentException("Not enough boosts");
        }
    }
    
    
    //Getters and Setters
    
    public int getRoundLoss(){
        return 0;
    }
}

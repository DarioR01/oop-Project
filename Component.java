import java.io.*;

public abstract class Component implements Serializable
{
    private String name;
    private int usage;
    
    //Constructor for the Component class
    public Component(String name,int usage){
        this.name=name;
        this.usage=usage;
    }
    
    //abstract metohds for viewing the component and modify the usage properly
    public abstract void increaseUsage(int increase);
    public abstract void reduceUsage(int reduce)throws ComponentException;
    public abstract String viewDescription();
    public abstract int getRoundLoss();
    
    //Getters and Setters for Component class
    public String getName(){
        return this.name;
    }
    
    public int getUsage(){
        return this.usage;
    }
    
    public void setUsage(int usage){
        this.usage=usage;
    }
    
    
}

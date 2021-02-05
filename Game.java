import java.io.*;
import java.util.ArrayList;
import java.util.Random;
public class Game implements Serializable, canBeSaved
{
    String name;
    private int lapCount=0;
    private int laps;
    private int racers;
    private Car car;
    private ArrayList<Entity> Positions=new ArrayList();
    private ArrayList<Item> items=new ArrayList();
    private ArrayList<Round> Events=new ArrayList();
    
    private String ItemOutcome;
    private String overtakeOutcome;
    
    //Constructor for Game class
    public Game(String name,int laps,int racers, Car car)
    {
       this.name=name;
       this.racers=racers;
       this.laps=laps;
       this.car=car;
       for(int i=0;i<this.racers-1;i++){
           Positions.add(new AI("Bot"+(i+1)));
       }
       Positions.add(car);
    }
    
    //Gets the current round and returns it
    public Round PreRound(){
        for(int i=0;i<this.laps;i++){
           Events.add(new Random().nextInt(100)<10?new Boost("Nitro Round","The car behind you is using his NOS; FIGHT FOR YOUR PLACE!!",new Random().nextInt(100)<80?true:false):new Random().nextInt(100)<50?new Curve("Tire round","You are approacing a",new Random().nextInt(100)<25?"Casio Triangle":new Random().nextInt(100)<50?"Hairpin":new Random().nextInt(100)<75?"Spoon Curve":"Snake"):new straightway("Motor Round","You are in a straighway SPEED UP IF YOU WANT TO OVERPASS SOMEONE!!!",new Random().nextInt(4)+1));
       }
        return Events.get(lapCount);
    } 
    
    //Method to use an item and store the usage outcome in a String
    public void itemUsage(Item item){
        this.ItemOutcome="";
        try{
            this.ItemOutcome=item.use(Positions,car);
           }
           catch(NullPointerException e){
               this.ItemOutcome="No item selected for this round";
           }
    }
    
    //method that Checks if any entity has ended it's usage for the round and put them in the last position if that's the case
    public void staminaCheck(){
        int lastPosition;
        if(Positions.indexOf(car)%2==0){
            for(int i=0; i<Positions.size()-1;i++){
                if(Positions.get(i).getUsage()<0){
                    lastPosition=Positions.size();
                    if(i+Positions.get(i).getRoundLoss()<lastPosition){
                        Positions.add(i+Positions.get(i).getRoundLoss(),Positions.get(i));
                        Positions.remove(i);
                        Positions.get(i+Positions.get(i).getRoundLoss()-1).reset();
                    }
                    else{
                        Positions.add(lastPosition,Positions.get(i));
                        Positions.remove(i);
                        Positions.get(lastPosition-1).reset();
                    }
                }
            }
        }
    }
    
    //Logic behind a round
    public void race(boolean choice,Item item){
        this.overtakeOutcome="";
        itemUsage(item);
        staminaCheck();
        
        //match overtakes depending on the player position, such that the player always overtakes once
        if(!(Positions.indexOf(car)%2==0)){
            for(int i=0; i<Positions.size()/2;i++){
                Events.get(lapCount).action(choice,0,Positions.get(i*2),Positions.get((i*2)+1),Positions);
            }
        }
        else{
            for(int i=0; i<Positions.size()/2;i++){
                Events.get(lapCount).action(choice,0,Positions.get((i*2)+1),Positions.get((i*2)+2),Positions);
            }
        }
        
        //Create a String holding the positions after a round;
        for(int i=0;i<Positions.size();i++){
            this.overtakeOutcome+=Positions.get(i).getName()+"\n";
        }
        
        //goes to the next round
        lapCount++;
        
        //removes the current round from the list;
        Events.remove(lapCount);
    }
    
    //Create a method that allow me to store the state of the game in a .txt file
    public void outputObject(String file)throws IOException{
       FileOutputStream fileOut = new FileOutputStream(file);
       ObjectOutputStream thingOut = new ObjectOutputStream(fileOut);
       thingOut.writeObject(this);
       thingOut.close();
    }
    
    
    //Getters and Setters
    public String getName(){
      return this.name;
    }
    
    public String getItemOutcome(){
        return this.ItemOutcome;
    }
    
    public String getOvertakeOutcome(){
        return this.overtakeOutcome;
    }
    
    public int getLap(){
        return this.laps;
    }
    
    public int getLapCount(){
        return this.lapCount;
    }   
    
    public void setCar(Car car){
        this.Positions.set(Positions.indexOf(this.car),car);
        this.car=car;
    }
    
    public Car getCar(){
        return this.car;
    }
    
    public ArrayList<Item> getItems(){
        return this.items;
    }
    
    public int getRacers(){
        return this.racers;
    }
}

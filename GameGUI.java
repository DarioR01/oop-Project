import javax.swing.JOptionPane;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;  
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;

public class GameGUI implements ActionListener, ListSelectionListener
{
    //GUI
    private JFrame frame;    
    
    //gameMenu GUI
    JLabel imageLabel;
    private ImageIcon image;
    private TextArea itemDescription=new TextArea("No item selected");
    
    private JButton Option1=new JButton();
    private JButton Option2=new JButton();
    private JButton Go=new JButton("Go!");
  
    private JList itemList=new JList();
    
    //RoundResult GUI
    private JButton next=new JButton("next");
    
    //Game Variables
    private Round round;
    private Game game;
    private Item item;
    
    //General Components
    JButton Exit=new JButton("Exit");
    
    //GUI helpers
    private int choice;
    
    
    //Constructor for the GameGUI class
     public GameGUI(String title,int width,int height, Game game){
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        this.game=game;
        this.run();
    }
    
    //detects changes in the item list and change the item description depending on the item selected
    public void valueChanged(ListSelectionEvent event){
        if (!event.getValueIsAdjusting()) {
            this.itemDescription.setText(game.getItems().get(itemList.getSelectedIndex()).getDescription());
        }
    }
    
    //Action for buttons
    public void actionPerformed(ActionEvent evt){
        Object command = evt.getSource();  
        //Initiate and run the game in the current Round
        if(command.equals(Go)){
            if(choice==0){
                JOptionPane.showMessageDialog(null, "You must Select an action before proceeding", "InfoBox: " + "Action", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                try{
                    item=game.getItems().get(itemList.getSelectedIndex());
                    game.getItems().remove(itemList.getSelectedIndex());
                }
                catch(IndexOutOfBoundsException e){
                }
                if(this.choice==1){
                    game.race(true,item);
                }
                else{
                    game.race(false,item);
                }
                item=null;
                roundResult();
            }
        }
        
        //change the car image that shows the movement of the car and set the player choice to 1
        if(command.equals(Option1)){
            ImageIcon image = new ImageIcon("saves//Image//SpeedCar.png");
            imageLabel.setIcon(image);
            this.choice=1;
        }
        
        
        //change the car image that shows the movement of the car and set the player choice to -1
        if(command.equals(Option2)){
            ImageIcon image = new ImageIcon("saves//Image//BreakCar.png");
            imageLabel.setIcon(image);
            this.choice=-1;
        }
        
        //Moves to the next round
        if(command.equals(next)){
            if(game.getLap()==game.getLapCount()){
                frame.setVisible(false);
                File file = new File("saves//Games//"+game.getName()+".txt");
                file.delete();
                Menu menu=new Menu("Game",1000,1000);
            }
            else{
                gameMenu();
            }
        }
        
        
        //Sace the state of the game and quit the game
        if(command.equals(Exit)){
            try{
                game.outputObject("saves\\Games\\"+game.getName()+".txt");
            }
            catch(IOException e){
                  JOptionPane.showMessageDialog(null, "Could not save the game", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
            System.exit(0);
        }
    }
    
    
    //Run the main method of the game to initialize a game menu
     public void run(){
        this._init_();
        this.gameMenu();
    }
    
    
    //Initialize useful variables
    public void _init_(){
        Option1.addActionListener(this);
        Option2.addActionListener(this);
        Go.addActionListener(this);
        next.addActionListener(this);
        Exit.addActionListener(this);
    }
    
    public void gameMenu(){
        reset();
        
        this.choice=0;
        //Randomly Give items to the player;
        if(new Random().nextInt(100)<100){
            game.getItems().add(new Random().nextInt(100)<33?new BoostNerf("Potion","Boost you or Nerf the enemy statistics for 1 round only ",100,new Random().nextBoolean()):new Random().nextInt(100)<66?new Destroy("Bazooka","Remove from the game cars in front of you",2,game.getRacers()/2):new Nails("Nails","Reduce to 0 the stamina of any hitted cars",3,new Random().nextInt(2)+1));
        }
        
        
        //Create Panel and Layouts
        JPanel topPanel= new JPanel();
        JPanel roundDetailsPanel=new JPanel();
        JPanel ActionPanel=new JPanel();
        JPanel middlePanel= new JPanel();
        JPanel rightPanel= new JPanel();
        JPanel leftPanel= new JPanel();
        JPanel bottomPanel= new JPanel();
        JPanel statsPanel=new JPanel();
        ActionPanel.setLayout(new FlowLayout());
        bottomPanel.setLayout(new FlowLayout());
        middlePanel.setLayout(new BorderLayout());
        rightPanel.setLayout(new BorderLayout());
        leftPanel.setLayout(new BorderLayout());
        statsPanel.setLayout(new BorderLayout());
        roundDetailsPanel.setLayout(new BorderLayout());
      
        
        //Car Image
        ImageIcon image = new ImageIcon("saves//Image//NormalCar.png");
        imageLabel= new JLabel(image);
        imageLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        //Get Round
        round=game.PreRound();
        
        //Get round Specifics and set it to GUI outputs
        JLabel roundName= new JLabel(round.getName());
        roundName.setFont(new Font("Serif", Font.PLAIN, 24));
        roundName.setAlignmentX(SwingConstants.CENTER);
        JLabel roundDescription=new JLabel(round.getDescription());
        roundDescription.setFont(new Font("Serif", Font.PLAIN, 24));
        roundDescription.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        JLabel motorStamina= new JLabel("Motor usage: "+((Car)game.getCar()).getMotorUsage());
        motorStamina.setFont(new Font("Serif", Font.PLAIN, 24));
        motorStamina.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        JLabel tireStamina= new JLabel("Tire usage: "+((Car)game.getCar()).getTireUsage());
        tireStamina.setFont(new Font("Serif", Font.PLAIN, 24));
        tireStamina.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        JLabel nitroStamina= new JLabel("Nitro usage: "+((Car)game.getCar()).getNitroUsage());
        nitroStamina.setFont(new Font("Serif", Font.PLAIN, 24));
        nitroStamina.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        //set Buttons
        this.Go.setFont(new Font("Serif", Font.PLAIN, 24));
        this.Exit.setFont(new Font("Serif", Font.PLAIN, 24));
        this.Option1.setFont(new Font("Serif", Font.PLAIN, 24));
        this.Option2.setFont(new Font("Serif", Font.PLAIN, 24));
        
        //Set Option button text
        Option1.setText(round.getOption1());
        Option2.setText(round.getOption2());
        
        //Set Item List
        DefaultListModel itemsDisplay=new DefaultListModel();
        for(int i=0;i<game.getItems().size();i++){
            itemsDisplay.addElement(game.getItems().get(i).getName());
        }
        JLabel Items=new JLabel((itemsDisplay.size()==0)?"No item to be selected":"Select an Item");
        Items.setFont(new Font("Serif", Font.PLAIN, 24));
        itemList=new JList(itemsDisplay);
        
        
        
        //insert to components to frame and panels
        roundDetailsPanel.add(roundName,BorderLayout.PAGE_START);
        roundDetailsPanel.add(roundDescription,BorderLayout.CENTER);
        topPanel.add(roundDetailsPanel,BorderLayout.PAGE_START);
        ActionPanel.add(this.Option1);
        ActionPanel.add(this.Option2);
        middlePanel.add(imageLabel,BorderLayout.PAGE_START);
        middlePanel.add(ActionPanel,BorderLayout.CENTER);
        rightPanel.add(Items,BorderLayout.PAGE_START);
        rightPanel.add(itemList,BorderLayout.CENTER);
        rightPanel.add(this.itemDescription,BorderLayout.PAGE_END);
        statsPanel.add(motorStamina,BorderLayout.PAGE_START);
        statsPanel.add(tireStamina,BorderLayout.CENTER);
        statsPanel.add(nitroStamina,BorderLayout.PAGE_END);
        leftPanel.add(statsPanel,BorderLayout.PAGE_START);
        bottomPanel.add(this.Go);
        bottomPanel.add(this.Exit);
        
        frame.add("North",topPanel);
        frame.add("Center",middlePanel);
        frame.add("South",bottomPanel);
        frame.add("East",rightPanel);
        frame.add("West",leftPanel);
        
        itemList.addListSelectionListener(this);
        refresh();
    }
    
    public void roundResult(){
        reset();
        JPanel topPanel=new JPanel();
        JPanel middlePanel=new JPanel();
        JPanel bottomPanel=new JPanel();
        
        //Label showing round number
        JLabel round=new JLabel("Round number "+game.getLapCount()+" of "+game.getLap());
        
        //set Text area for results of the round output;
        TextArea itemOutcome=new TextArea();
        TextArea newPosition= new TextArea();
        itemOutcome.setText(game.getItemOutcome());
        newPosition.setText(game.getOvertakeOutcome());
        itemOutcome.setEditable(false);
        newPosition.setEditable(false);
        
        round.setFont(new Font("Serif", Font.PLAIN, 24));
        Exit.setFont(new Font("Serif", Font.PLAIN, 24));
        next.setFont(new Font("Serif", Font.PLAIN, 24));

        //Add to the panels and frame
        topPanel.add(round);
        middlePanel.add(itemOutcome);
        middlePanel.add(newPosition);
        bottomPanel.add(next);
        bottomPanel.add(this.Exit);
        frame.add("North",topPanel);
        frame.add("Center",middlePanel);
        frame.add("South",bottomPanel);
        
        refresh();
    }
    
    
    //Reset the frame and the main panel by taking off what they are currently holding
    public void reset(){
       frame.getContentPane().removeAll(); 
    }
    
    //Refresh the frame to display the updates
    public void refresh(){
        frame.revalidate();
        frame.repaint();
    }
    
    //setters fro frame visibility
    public void setFrameVisibility(boolean visible){
       frame.setVisible(visible);
    }
}

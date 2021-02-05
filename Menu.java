import javax.swing.*;
import java.awt.*;  
import java.awt.event.*;
import javax.swing.event.*;
import java.util.Random;
import java.io.*;

public class Menu implements ActionListener, ChangeListener
{
    //GUI
    JFrame frame;
    String title;
    int width,height;
    //Main Menu Buttons
    private JButton raceButton=new JButton("Play");
    private JButton createCarButton=new JButton("Create Car");
    private JButton selectCarButton=new JButton("View Cars");
    private JButton exitButton=new JButton("Exit");
    private Car Slot1;
    private Car Slot2;
    
    //Car Creation Selection Buttons
    private JButton randomButton=new JButton("Create Random Car");
    private JButton CarButton=new JButton("Create Component Car");
    
    //Car Creation Menu
    private JButton SubmitButton= new JButton("Submit");
    private JTextField inputName=new JTextField(20);
    private JList motorList;
    private JList tireList;
    private JList nitroList;
    
    //View Car Menu
    private JButton Slot1ViewButton= new JButton("Random Slot");
    private JButton Slot2ViewButton= new JButton("Component Slot");
    private JLabel CarNameLabel=new JLabel("No Slot selected");
    private JTextArea Componenttext=new JTextArea();
    
    //Create a game GUI
    private JButton SubmitGame=new JButton("Submit");
    private JList savedGames;
    private JSlider maxLaps=new JSlider(0,50);
    private JSlider maxOpponents=new JSlider(0,50);
    private JLabel lapsCount=new JLabel();
    private JLabel opponentsCount=new JLabel();
    private TextField gameName=new TextField();
    
    //Game Slot Selection
    private JButton Slot1RaceButton= new JButton("Random Slot");
    private JButton Slot2RaceButton= new JButton("Component Slot");
    
    //General Components
    private JButton backButton=new JButton("Back");
    
    private Structure[] motors={new Structure("3.0L TFSI Supercharged DOHC V-6",50,"Motor",50,5),new Structure("3.0L N55 Turbocharged DOHC I-6",100,"Motor",100,10),new Structure("5.0L DOHC V-8",150,"Motor",150,15),new Structure("5.0L Tau DOHC V-8",200,"Motor",200,20),new Structure("3.0L Turbocharged DOHC I-6 ",250,"Motor",250,25)};
    private Structure[] tires={new  Structure("CONTINENTAL",50,"Tire",50,5),new Structure("GOODYEAR",100,"Tire",100,10),new Structure("COOPER",150,"Tire",150,15),new Structure("PIRELLI",200,"Tire",200,20),new Structure("MICHELIN",250,"Tire",250,25)};
    private Nitro[] nitros={new Nitro("Scorpion",1,1),new Nitro("Fox",2,2)};
    Component[][] AllCompoenents={motors,tires,nitros};   
    Game game;
    
    //Constructor for the menu class
    public Menu(String title,int width,int height){
        this.title=title;
        this.width=width;
        this.height=height;
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        this.run();
    }
    
    //Run the main function of this class to 
    public void run(){
        this._init_();
        this.mainMenu();
    }
    
    //Import the data for the cars
    public Car getData(String file){
        try {
             File folder=new File(file);
             FileInputStream fileIn = new FileInputStream(file);
             ObjectInputStream thingIn = new ObjectInputStream(fileIn);
             Object obj=thingIn.readObject();
             thingIn.close();
             return (Car) obj;
            }
             catch(ClassNotFoundException e) {
             }
             catch(IOException e) {
            }
        return null;
    }
    
    //Initialize all usefull variables
    public void _init_(){ 
        Slot1=getData("saves//Car//Slot1.txt");
        Slot2=getData("saves//Car//Slot2.txt");
        Slot1RaceButton.addActionListener(this);
        Slot2RaceButton.addActionListener(this);
        backButton.addActionListener(this);
        raceButton.addActionListener(this);
        createCarButton.addActionListener(this);
        selectCarButton.addActionListener(this);
        exitButton.addActionListener(this);
        randomButton.addActionListener(this);
        CarButton.addActionListener(this);
        backButton.addActionListener(this);
        Slot1ViewButton.addActionListener(this);
        Slot2ViewButton.addActionListener(this);
        backButton.addActionListener(this);
        backButton.addActionListener(this);
        SubmitButton.addActionListener(this);
        SubmitGame.addActionListener(this);
        
        maxLaps.addChangeListener(this);
        maxOpponents.addChangeListener(this);
    }
    
    //Detect the change in state of a slider and perform action accordingly
    public void stateChanged(ChangeEvent event) {
        lapsCount.setText(""+maxLaps.getValue());
        opponentsCount.setText(""+maxOpponents.getValue());
    }
    
    //Detects the press of a button and perform an action accordngly
    public void actionPerformed(ActionEvent evt){
        Object command = evt.getSource();
        
        //if any car exist in Slot1 or Slot2 then goes to the createGame menu; 
        if(command.equals(raceButton)){
           if(Slot1==null && Slot2==null){
               JOptionPane.showMessageDialog(null, "You have no cars in any slots, create a car before racing", "Error", JOptionPane.INFORMATION_MESSAGE);
           }
           else{
               createGame();
           }
        }
        
        //Change display of menu
        if(command.equals(createCarButton)){
             carCreateMenu();
        }
        
        //Change display of menu
        if(command.equals(selectCarButton)){
            carViewMenu();
        }
        
        //stores the cars in slot 1 and 2 and exit the program
        if(command.equals(exitButton)){
            try{
                Slot1.outputObject("saves\\Car\\Slot1.txt");
                Slot2.outputObject("saves\\Car\\Slot2.txt");
            }
            catch(IOException e){
                JOptionPane.showMessageDialog(null, "An Error Occured and the cars where not saved", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
            finally{
                    System.exit(0);
                }
        }        
        
        //Create a random car in the slot 1
        if(command.equals(randomButton)){
            Slot1=new Car("Random Car",motors[new Random().nextInt(5)],tires[new Random().nextInt(5)],nitros[new Random().nextInt(2)]);
            try{
                Slot1.outputObject("saves\\Car\\Slot1.txt");
            }
            catch(IOException e){
                JOptionPane.showMessageDialog(null, "Impossible to permanently save this car", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        
        //Go to che createCar menu
        if(command.equals(CarButton)){
            createCar();
        }       
        
        //Select the car store in slot 1 for the race
        if(command.equals(Slot1RaceButton)){
            if(Slot1==null){
                JOptionPane.showMessageDialog(null, "This Slot is Empty", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                this.game.setCar(Slot1);
                GameGUI gameStart = new GameGUI(this.title,this.width,this.height,game);
                frame.setVisible(false);
            }
        }
        
        //Select the car store in slot 2 for the race
        if(command.equals(Slot2RaceButton)){
            if(Slot2==null){
                JOptionPane.showMessageDialog(null, "This Slot is Empty", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                this.game.setCar(Slot2);
                GameGUI gameStart = new GameGUI(this.title,this.width,this.height,game);
                frame.setVisible(false);
            }
        }
        
        //change JComponents to show the components of the car stored in slot 1
        if(command.equals(Slot1ViewButton)){
            if(Slot1==null){
                CarNameLabel.setText("Select a Slot");
                Componenttext.setText("No Car in this slot");
            }
            else{
                CarNameLabel.setText(Slot1.getName());
                Componenttext.setText(Slot1.viewCar());
            }
        }
        
        //change JComponents to show the components of the car stored in slot 2
        if(command.equals(Slot2ViewButton)){
            if(Slot2==null){
                CarNameLabel.setText("Select a Slot");
                Componenttext.setText("No Car in this slot");
            }
            else{
                CarNameLabel.setText(Slot2.getName());
                Componenttext.setText(Slot2.viewCar());
            }
        }
        
        //Submit the creation of a car and create it
        if(command.equals(SubmitButton)){
            try{
                Slot2=new Car(inputName.getText(),motors[motorList.getSelectedIndex()],tires[tireList.getSelectedIndex()],nitros[nitroList.getSelectedIndex()]);
                try{
                    Slot2.outputObject("saves\\Car\\Slot2.txt");
                }
                catch(IOException e){
                    JOptionPane.showMessageDialog(null, "Impossible to permanently save this car", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
                mainMenu();
            }
            catch(ArrayIndexOutOfBoundsException e){
                JOptionPane.showMessageDialog(null, "You must select at least 1 of each component to create a car", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
           }
        
           
        //Submit the created game, if a saved game is selected it loads the saved game
        if(command.equals(SubmitGame)){
            if(savedGames.getSelectedIndex()==0||savedGames.getSelectedIndex()==-1){
                game=new Game(gameName.getText(),maxLaps.getValue(),maxOpponents.getValue(),null);
                selectCar();
            }
            else{
                try {
                    File folder=new File("saves\\Games");
                    File[] files=folder.listFiles();
                    FileInputStream fileIn = new FileInputStream(files[savedGames.getSelectedIndex()-1]);
                    ObjectInputStream thingIn = new ObjectInputStream(fileIn);
                    Object obj=thingIn.readObject();
                    game = (Game) obj;
                    thingIn.close();
                    GameGUI gameStart = new GameGUI(this.title,this.width,this.height,game);
                    frame.setVisible(false);
                }
                catch(ClassNotFoundException e) {
                    JOptionPane.showMessageDialog(null, "Impossible to create game", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
                catch(IOException e) {
                    JOptionPane.showMessageDialog(null, "Impossible to create game", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        
        //Goes back to the main menu
        if(command.equals(backButton)){
           mainMenu();
        }
    }
    
    
    //Initialization and set frame for different menu displayed in the frame
    public void mainMenu(){
        reset();
        //Creating Panels
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.PAGE_AXIS));
        bottomPanel.setLayout(new GridLayout(4,1,10,10));
        
        //Creating Labels
        JLabel title=new JLabel("Main Menu");
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        title.setFont(new Font("Serif", Font.PLAIN, 24));
        
        //Adjust Buttons
        raceButton.setFont(new Font("Serif", Font.PLAIN, 24));
        createCarButton.setFont(new Font("Serif", Font.PLAIN, 24));
        selectCarButton.setFont(new Font("Serif", Font.PLAIN, 24));
        exitButton.setFont(new Font("Serif", Font.PLAIN, 24));
        
        //Add to Panels and Frame
        topPanel.add(title);
        bottomPanel.add(raceButton);
        bottomPanel.add(createCarButton);
        bottomPanel.add(selectCarButton);
        bottomPanel.add(exitButton);
        frame.add("North",topPanel);
        frame.add("Center",bottomPanel);
        refresh();
    }
    
    
    public void carCreateMenu(){
        reset();
        //Creating Panels
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.PAGE_AXIS));
        bottomPanel.setLayout(new GridLayout(3,1,10,10));
        
        //Creating Labels
        JLabel title=new JLabel("Select the type of car you want to create");
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        title.setFont(new Font("Serif", Font.PLAIN, 24));
        
        
        //Adjust Buttons
        randomButton.setFont(new Font("Serif", Font.PLAIN, 24));
        CarButton.setFont(new Font("Serif", Font.PLAIN, 24));
        backButton.setFont(new Font("Serif", Font.PLAIN, 24));
        //Add to Panels and Frame
        topPanel.add(title);
        bottomPanel.add(randomButton);
        bottomPanel.add(CarButton);
        bottomPanel.add(backButton);
        frame.add("North",topPanel);
        frame.add("Center",bottomPanel);
        refresh();
    }
    
    public void createCar(){
        reset();
        //Creating Panels
        frame.setLayout(new BorderLayout(0,100));
        JPanel topPanel = new JPanel(new GridLayout(0,1,0,0));
        JPanel middlePanel = new JPanel();
        JPanel NameInputPanel=new JPanel();
        JPanel ChoiceListPanel=new JPanel();
        JPanel bottomPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.PAGE_AXIS));
        NameInputPanel.setLayout(new FlowLayout());
        ChoiceListPanel.setLayout(new GridLayout(2,3,10,-80));
        bottomPanel.setLayout(new GridLayout(1,2,10,10));
        
        //Creating Labels
        JLabel title=new JLabel("Create Car");
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        title.setFont(new Font("Serif", Font.PLAIN, 24));
        JLabel nameLabel=new JLabel("Car Name: ");
        JLabel motorLabel=new JLabel("Select a Motor");
        JLabel tireLabel=new JLabel("Select a set of Tires");
        JLabel nitroLabel=new JLabel("Select a Nitro");
        
        inputName.setMaximumSize(new Dimension(50, 10));
        inputName.setPreferredSize(new Dimension(50, 20));
        
        //Fill The lists with components array
        DefaultListModel motorData = new DefaultListModel();
        DefaultListModel tireData = new DefaultListModel();
        DefaultListModel nitroData = new DefaultListModel();
        
        for(int i=0;i<motors.length;i++){
            motorData.addElement(motors[i].getName());
        }
        for(int i=0;i<tires.length;i++){
            tireData.addElement(tires[i].getName());
        }
        for(int i=0;i<nitros.length;i++){
            nitroData.addElement(nitros[i].getName());
        }
        motorList=new JList(motorData);
        motorList.setPreferredSize(new Dimension(200, 200));
        tireList=new JList(tireData);
        tireList.setPreferredSize(new Dimension(200, 200));
        nitroList=new JList(nitroData);
        nitroList.setPreferredSize(new Dimension(200, 200));
        
        //Set the List Property
        motorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        motorList.setLayoutOrientation(JList.VERTICAL);
        tireList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tireList.setLayoutOrientation(JList.VERTICAL);
        nitroList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        nitroList.setLayoutOrientation(JList.VERTICAL);
        
        //Add to Panels and Frame
        topPanel.add(title);
        NameInputPanel.add(nameLabel,BorderLayout.CENTER);
        NameInputPanel.add(inputName,BorderLayout.CENTER);
        topPanel.add(NameInputPanel);
        ChoiceListPanel.add(motorLabel);
        ChoiceListPanel.add(tireLabel);
        ChoiceListPanel.add(nitroLabel);
        ChoiceListPanel.add(motorList);
        ChoiceListPanel.add(tireList);
        ChoiceListPanel.add(nitroList);
        bottomPanel.add(SubmitButton);
        bottomPanel.add(backButton);
        middlePanel.add(ChoiceListPanel,BorderLayout.LINE_END);
        frame.add(topPanel,BorderLayout.PAGE_START);
        frame.add(middlePanel,BorderLayout.CENTER);
        frame.add(bottomPanel,BorderLayout.PAGE_END);
        refresh();
    }
    
    
    public void carViewMenu(){
        reset();
        //Creating Panels
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        JPanel middlePanel=new JPanel();
        JPanel SlotPanel=new JPanel();
        
        SlotPanel.setLayout(new FlowLayout());
        middlePanel.setLayout(new BorderLayout());
        
        //Creating Labels
        CarNameLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        CarNameLabel.setFont(new Font("Serif", Font.PLAIN, 64));
        
        //Adjust Buttons
        Slot1ViewButton.setFont(new Font("Serif", Font.PLAIN, 24));
        Slot2ViewButton.setFont(new Font("Serif", Font.PLAIN, 24));
        backButton.setFont(new Font("Serif", Font.PLAIN, 24));
        //Adjust Text Field
        Componenttext.setEditable(false);
    
        //Add to Panels and Frame
        topPanel.add(CarNameLabel);
        SlotPanel.add(Slot1ViewButton);
        SlotPanel.add(Slot2ViewButton);
        middlePanel.add(SlotPanel,BorderLayout.PAGE_START);
        middlePanel.add(Componenttext,BorderLayout.CENTER);
        bottomPanel.add(backButton);
        
        frame.add(middlePanel,BorderLayout.CENTER);
        frame.add(topPanel,BorderLayout.PAGE_START);
        frame.add(bottomPanel,BorderLayout.PAGE_END);
        refresh();
    }
    
    public void selectCar(){
        reset();
        
        //Creating Panels
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.PAGE_AXIS));
        bottomPanel.setLayout(new GridLayout(3,1,10,10));
        
        //Creating Labels
        JLabel title=new JLabel("Slots");
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        title.setFont(new Font("Serif", Font.PLAIN, 24));
        
        
        //Adjust Buttons
        Slot1RaceButton.setFont(new Font("Serif", Font.PLAIN, 24));
        Slot2RaceButton.setFont(new Font("Serif", Font.PLAIN, 24));
        backButton.setFont(new Font("Serif", Font.PLAIN, 24));
        //Add to Panels and Frame
        topPanel.add(title);
        bottomPanel.add(Slot1RaceButton);
        bottomPanel.add(Slot2RaceButton);
        bottomPanel.add(backButton);
        frame.add("North",topPanel);
        frame.add("Center",bottomPanel);
       
        
        refresh();
    }
    
    public void createGame(){
        reset();
        //Create pannels
        JPanel topPanel=new JPanel();
        JPanel namePanel=new JPanel();
        JPanel middlePanel=new JPanel();
        JPanel lapsPanel=new JPanel();
        JPanel racersPanel=new JPanel();
        JPanel leftPanel=new JPanel();
        JPanel bottomPanel=new JPanel();        
        JPanel leftmiddlePanel=new JPanel();
        JPanel rightmiddlePanel=new JPanel();
        
        topPanel.setLayout(new BorderLayout());
        namePanel.setLayout(new FlowLayout());
        middlePanel.setLayout(new FlowLayout());
        lapsPanel.setLayout(new FlowLayout());
        racersPanel.setLayout(new FlowLayout());
        leftPanel.setLayout(new BorderLayout());
        bottomPanel.setLayout(new FlowLayout());
        
        //Create instuction labels
        JLabel title=new JLabel("Create your Game");
        title.setFont(new Font("Serif", Font.PLAIN, 24));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        SubmitGame.setFont(new Font("Serif", Font.PLAIN, 24));
        backButton.setFont(new Font("Serif", Font.PLAIN, 24));
        
        gameName.setPreferredSize(new Dimension(200,20));
        lapsCount.setText(""+maxLaps.getValue());
        opponentsCount.setText(""+maxLaps.getValue());
        JLabel name=new JLabel("Name of the game:");
        JLabel laps=new JLabel("How many laps:");
        JLabel racers=new JLabel("How many opponents:");
        JLabel existingGames=new JLabel("Saved Games");
        
        //Create a list of existing games
        DefaultListModel savedList = new DefaultListModel();
        File folder=new File("saves\\Games");
        File[] listOfFiles=folder.listFiles();
        savedList.addElement("None");
        for(int i=0;i<listOfFiles.length;i++){
            savedList.addElement(listOfFiles[i].getName());
        }
        savedGames=new JList(savedList);
        
        
        //add to frame and Panels
        topPanel.add(title,BorderLayout.PAGE_START);
        namePanel.add(name);
        namePanel.add(gameName);
        topPanel.add(namePanel,BorderLayout.CENTER);
        
        lapsPanel.add(laps);
        lapsPanel.add(lapsCount);
        racersPanel.add(racers);
        racersPanel.add(opponentsCount);
        middlePanel.add(leftmiddlePanel);
        middlePanel.add(rightmiddlePanel);
        leftmiddlePanel.add(lapsPanel);
        rightmiddlePanel.add(racersPanel);
        leftmiddlePanel.add(this.maxLaps);
        rightmiddlePanel.add(this.maxOpponents);
        leftPanel.add(existingGames,BorderLayout.PAGE_START);
        leftPanel.add(savedGames,BorderLayout.CENTER);
        bottomPanel.add(this.SubmitGame);
        bottomPanel.add(this.backButton);
        
        frame.add(topPanel,BorderLayout.PAGE_START);
        frame.add(middlePanel,BorderLayout.CENTER);
        frame.add(bottomPanel,BorderLayout.PAGE_END);
        frame.add(leftPanel,BorderLayout.LINE_START);
        
        
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
    
    //Setters for the frame visibility
    public void setFrameVisibility(boolean visible){
        frame.setVisible(visible);
    }
}
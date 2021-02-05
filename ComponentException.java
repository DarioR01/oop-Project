//class to throw any type of Component Exception that I will later define through a message
public class ComponentException extends Exception
{
    //Constructor for the ComponentException
    public ComponentException(String message){
        super(message);
    }
}
